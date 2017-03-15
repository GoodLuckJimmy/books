//package jpabook.jpashop.web;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.domain.item.Item;
//import jpabook.jpashop.service.ItemService;
//import jpabook.jpashop.service.MemberService;
//import jpabook.jpashop.service.OrderService;
//
//@Controller
//public class OrderController {
//    
//    @Autowired OrderService orderService;
//    @Autowired MemberService memberService;
//    @Autowired ItemService itemService;
//    
//    @RequestMapping(value="/order", method=RequestMethod.GET)
//    public String createForm(Model model) {
//       
//        List<Member> members = memberService.findMembers();
//        List<Item> items = itemService.findItems();
//        
//        model.addAttribute("members", members);
//        model.addAttribute("items", items);
//        
//        return "order/orderForm";
//    }
//
//    @RequestMapping(value="/order", method=RequestMethod.POST)
//    public String order(@RequestParam("memberId") Long memberId,
//            @RequestParam("itemId") Long itemId,
//            @RequestParam("count") int count) {
//        orderService.order(memberId, itemId, count);
//        return "redirect:/orders";
//    }
//}
