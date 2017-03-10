package jpabook.jpashop.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Transactional
public class MemberServiceTest {
    
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    
    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        
        // when
        Long saveId = memberService.join(member);
        
        // then
        assertThat(member, is(memberRepository.findOne(saveId)));
    }
    
    @Test(expected=IllegalStateException.class)
    public void 중복_회원_예외() {
       // given
        Member member1 = new Member();
        member1.setName("kim");
        
        Member member2 = new Member();
        member2.setName("kim");
        
        // when
        memberService.join(member1);
        memberService.join(member2);
        
        // then
        fail("예외가 발생햐야 한다");
    }

}
