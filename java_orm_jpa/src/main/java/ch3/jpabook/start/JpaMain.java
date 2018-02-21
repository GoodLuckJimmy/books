package ch3.jpabook.start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close(); // 어플리케이션 종료시 close호출
    }
    
    private static void logic(EntityManager em) {
       
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("hong");
        member.setAge(2);
        
        // 등록
        em.persist(member);
        
        // 수정
        member.setAge(20);
        
        // 한건조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        
        // 목록조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("member.size=" + members.size());
        
        // 삭제
        em.remove(member);
    }

}
