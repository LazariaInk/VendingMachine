package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.model.VendingMachineHistory;
import md.lazari.vendingmachine.repository.ItemRepository;
import md.lazari.vendingmachine.repository.VendingMachineHistoryRepository;
import md.lazari.vendingmachine.service.BankAccountService;
import md.lazari.vendingmachine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private VendingMachineHistoryRepository vendingMachineHistoryRepository;


    @GetMapping("/items")
    public String findAll(Model model){
        Iterable<Item> items = itemService.showAll();
        model.addAttribute("items", items);

        Optional<BankAccount> bankAccountOptional = bankAccountService.getBankAccount();
        BankAccount bankAccount = bankAccountOptional.get();
        model.addAttribute("bankAccount", bankAccount);
        return "item-list";
    }

    @GetMapping("item-delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id){
        Optional<Item> optionalItem = itemRepository.findById(id);
        String stringPrice = itemRepository.findById(id).get().price;
        Double price =Double.valueOf(stringPrice.replaceAll("\\$", ""));
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (item.amount != 0) {
                item.amount--;
                String historyInfo = "Sell item: " + item.getName() + ", with price: " + item.price;
                VendingMachineHistory vendingMachineHistory = new VendingMachineHistory(LocalDate.now(),historyInfo);
                vendingMachineHistoryRepository.save(vendingMachineHistory);
                bankAccountService.spendMoney(price);
                if (bankAccountService.spendMoney(price) == false) {
                    return "redirect:/items";
                }
                itemRepository.save(item);
            } else {
                itemRepository.deleteById(id);
            }
        }
        return "redirect:/items";
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