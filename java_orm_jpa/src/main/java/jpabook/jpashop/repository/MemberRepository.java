package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;

// spring jpa 안사용
//@Repository // <context:component-scan>에 의해 검색 + jpa 전용 예외발생시 spring추상 에러로 변환
//public class MemberRepository {
//
//    @PersistenceContext // 컨테이너가 제공하는 엔티티 매니저 사용. 컨테이너 트랜잭션과 연계
//    EntityManager em;
//    
//    public void save(Member member) {
//        em.persist(member);
//    }
//    
//    public Member findOne(Long id) {
//        return em.find(Member.class, id);
//    }
//    
//    public List<Member> findAll() {
//       return em.createQuery("select m from Member m", Member.class)
//               .getResultList(); 
//    }
//    
//    public List<Member> findByName(String name) {
//        return em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
//}

// spring jpa 사용
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
