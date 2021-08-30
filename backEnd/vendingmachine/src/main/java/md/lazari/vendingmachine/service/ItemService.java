package md.lazari.vendingmachine.service;

import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> showAll() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item itemModel) {
        return itemRepository.save(itemModel);
    }

    @Transactional
    public List<Item> saveAllItems(List<Item> itemList) {
        List<Item> response = (List<Item>) itemRepository.saveAll(itemList);
        return response;
    }


    public void deleteById(Integer id){
        itemRepository.deleteById(id);
    }

    public Iterable<Item> list() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

}