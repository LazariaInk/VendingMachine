package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BankAccountController {
    // TODO: schimba cu service
    @Autowired
    BankAccountRepository bankAccountRepository;

    @PostMapping("/accounts")
    public String addMoney(BankAccount bankAccount){
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(1);
        if (optionalBankAccount.isPresent()) {
            BankAccount existingBankAccount = optionalBankAccount.get();
            existingBankAccount.money += bankAccount.getMoney();
            bankAccountRepository.save(existingBankAccount);
        }

        return "redirect:/items";
    }

}