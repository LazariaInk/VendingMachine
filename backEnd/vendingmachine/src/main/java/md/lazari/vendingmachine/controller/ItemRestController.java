package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemRestController {

    private ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public Iterable<Item> list() {
        return itemService.list();
    }

}