package jpabook.jpashop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;

@Controller
public class ItemController {
    
    @Autowired ItemService itemService;
    
    @RequestMapping(value="/items/new", method = RequestMethod.GET)
    public String createForm() {
       return "items/createItemForm"; 
    }

    @RequestMapping(value="/items/new", method = RequestMethod.POST)
    public String create(Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @RequestMapping(value="/items", method = RequestMethod.GET)
    public String list(Model model) {
       List<Item> items = itemService.findItems(); 
       model.addAttribute("items", items);
       return "item/itemList";
    }

    @RequestMapping(value="/items/{itemId}/edit", method = RequestMethod.GET)
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "item/updateItemForm";
    }

    @RequestMapping(value="/items/{itemId}/edit", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute("item") Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
