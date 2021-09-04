package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.ItemRepository;
import md.lazari.vendingmachine.repository.VendingMachineHistoryRepository;
import md.lazari.vendingmachine.service.BankAccountService;
import md.lazari.vendingmachine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private VendingMachineHistoryRepository vendingMachineHistoryRepository;


    @GetMapping("/items")
    public String findAll(Model model){
        itemService.findAllItems(model);
        return "item-list";
    }

    @GetMapping("item-delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id){
     String deleteItemPath = itemService.ItemFunctionality(id);
        return deleteItemPath;
    }

    @GetMapping("/deleteMoney")
    public String deleteMoney(){
                bankAccountService.takeMoney();
        return "redirect:/items";
    }

    @GetMapping("/add-item")
    public String createUserForm(Item item  ){
        return "add-item";
    }

    @PostMapping("/add-item")
    public String createUser(Item item){
        itemService.saveOne(item);
        return "redirect:/items";
    }

}