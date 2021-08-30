package md.lazari.vendingmachine.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.ItemRepository;
import md.lazari.vendingmachine.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Controller
public class ItemController {

    private final static Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/items")
    public String findAll(Model model){
        Iterable<Item> items = itemService.showAll();
        model.addAttribute("items", items);
        return "item-list";
    }




    @GetMapping("item-delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id){
        itemService.deleteById(id);
        return "redirect:/items";
    }




}