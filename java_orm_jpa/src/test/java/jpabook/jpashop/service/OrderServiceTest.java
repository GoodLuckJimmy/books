//package jpabook.jpashop.service;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import jpabook.jpashop.domain.Address;
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.domain.Order;
//import jpabook.jpashop.domain.OrderStatus;
//import jpabook.jpashop.domain.item.Book;
//import jpabook.jpashop.domain.item.Item;
//import jpabook.jpashop.exception.NotEnoughStockException;
//import jpabook.jpashop.repository.OrderRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={
//        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
//})
//@Transactional
//public class OrderServiceTest {
//    
//    @PersistenceContext
//    EntityManager em;
//    
//    @Autowired OrderService orderService;
//    @Autowired OrderRepository orderRepository;
//    
//    @Test
//    public void 상품주문() throws Exception {
//       // given
//        Member member = createMember();
//        Item item = createBook("시골JPA", 10000, 10);
//        int orderCount = 2;
//        
//        // when
//        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
//        
//        // then
//        Order getOrder = orderRepository.fineOne(orderId);
//        
//        assertThat("상품 주문시 상태는 ORDER", OrderStatus.ORDER, is(getOrder.getStatus()));
//        assertThat("주문한 상품 종류 수가 정확해야 한다", 1, is(getOrder.getOrderItems().size()));
//        assertThat("주문 가격은 가격 X 수량이다", 10000 * 2, is(getOrder.getTotalPrice()));
//        assertThat("주문 수량만큼 재고가 줄어야 한다.", 8, is(item.getStockQuantity()));
//    }
//    
//    @Test(expected = NotEnoughStockException.class)
//    public void 상품주문_재고수량초과() {
//       // given
//        Member member = createMember();
//        Item item = createBook("시골JPA", 10000, 10);
//        int orderCount = 11;
//        
//        // when
//        orderService.order(member.getId(), item.getId(), orderCount);
//        
//        // then
//        fail("재고 수량 부족 예외가 발생해야 한다");
//    }
//    
//    @Test
//    public void 주문취소() {
//        // given
//        Member member = createMember();
//        Item item = createBook("시골JPA", 10000, 10);
//        int orderCount = 2;
//        
//        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
//        
//        // when
//        orderService.cancelOrder(orderId);
//        
//        // then
//        Order getOrder = orderRepository.fineOne(orderId);
//        
//        assertThat("주문 취소시 상태는 CANCEL이다", OrderStatus.CANCEL, is(getOrder.getStatus()));
//        assertThat("주문 취소시 재고가 증가해야 한다", 10, is(item.getStockQuantity()));
//    }
//
//    private Member createMember() {
//        Member member = new Member();
//        member.setName("회원1");
//        member.setAddress(new Address("서울", "강가", "123-123"));
//        em.persist(member);
//
//        return member;
//    }
//    
//    private Book createBook(String name, int price, int stockQuantity) {
//        Book book = new Book();
//        book.setName(name);
//        book.setStockQuantity(stockQuantity);
//        book.setPrice(price);
//        em.persist(book);
//        
//        return book;
//    }
//
//}
