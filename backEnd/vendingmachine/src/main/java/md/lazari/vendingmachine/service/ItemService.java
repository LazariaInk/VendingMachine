package md.lazari.vendingmachine.service;

import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Iterable<Item> list() {
        return itemRepository.findAll();
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

    public void saveOne(Item item) {
        item.price = "$" + String.valueOf(item.price);
        itemRepository.save(item);
    }

}