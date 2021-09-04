package md.lazari.vendingmachine.service;

import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.model.VendingMachineHistory;
import md.lazari.vendingmachine.repository.BankAccountRepository;
import md.lazari.vendingmachine.repository.ItemRepository;
import md.lazari.vendingmachine.repository.VendingMachineHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private VendingMachineHistoryRepository vendingMachineHistoryRepository;
    @Autowired
    private BankAccountService bankAccountService;



    public void saveOne(Item item) {
        item.price = "$" + (item.price);
        itemRepository.save(item);
    }

    public void findAllItems(Model model) {

        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        Optional<BankAccount> bankAccountOptional = bankAccountService.getBankAccount();
        BankAccount bankAccount = bankAccountOptional.get();
        model.addAttribute("bankAccount", bankAccount);
    }

    public String ItemFunctionality(Integer id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        String stringPrice = itemRepository.findById(id).get().price;
        Double price =Double.valueOf(stringPrice.replaceAll("\\$", ""));
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (item.amount != 0) {
                if (!bankAccountService.spendMoney(price)) {
                    return "redirect:/items";
                }
                item.amount--;
                String historyInfo = "Sell item: " + item.getName() + ", with price: " + item.price;
                VendingMachineHistory vendingMachineHistory = new VendingMachineHistory(LocalDate.now(),historyInfo);
                vendingMachineHistoryRepository.save(vendingMachineHistory);
                bankAccountService.spendMoney(price);
                itemRepository.save(item);
            } else {
                itemRepository.deleteById(id);
            }
        }
        return "redirect:/items";
    }
}