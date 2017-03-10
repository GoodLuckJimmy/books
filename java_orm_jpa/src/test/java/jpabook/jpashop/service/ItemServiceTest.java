package jpabook.jpashop.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Transactional
public class ItemServiceTest {
    
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;
    
    @Test
    public void 상품추가() throws Exception {
        // given
        Item item = new Item();
        item.setName("item1");
        item.setPrice(1000);
        item.setStockQuantity(100);
        
        // when
        itemService.saveItem(item);
        
        // then
        assertThat(item, is(itemRepository.findOne(item.getId())));
    }
}
