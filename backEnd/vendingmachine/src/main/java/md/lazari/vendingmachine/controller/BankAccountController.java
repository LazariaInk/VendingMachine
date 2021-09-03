package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.repository.BankAccountRepository;
import md.lazari.vendingmachine.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BankAccountController {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    BankAccountService bankAccountService;

    @PostMapping("/accounts")
    public String addMoney(BankAccount bankAccount){
        bankAccountService.addMoney(bankAccount);

        return "redirect:/items";
    }

    @RequestMapping("/bankAccount")
    public String showMoney(Model model){
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(1);
        model.addAttribute("bankAccount", bankAccount);
        return "item-list";
    }
}
