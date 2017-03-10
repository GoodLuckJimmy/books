package jpabook.jpashop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
    
    @Autowired
    ItemRepository itemRepository;
    
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    
    public List<Item> findItems() {
       return itemRepository.findAll(); 
    }
    
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
