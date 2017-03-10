package jpabook.jpashop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
    
    @Autowired
    MemberRepository memberRepository;
    
    /*
     * 회원가입
     */
    public Long join(Member member) {
       validateDuplicateMember(member); // 중복회원검증
       memberRepository.save(member);
       return member.getId();
    }

    private void validateDuplicateMember(Member member) {
       List<Member> findMembers =
               memberRepository.findByName(member.getName());
       if (!findMembers.isEmpty()) {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       }
    }
    
    /*
     * 전체회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
