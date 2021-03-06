package md.lazari.vendingmachine.service;

import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Optional<BankAccount> getBankAccount(){
       return bankAccountRepository.findById(1);
    }

    public boolean spendMoney(double itemPrice) {
        System.out.println("spend +++++");
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(1);
        BankAccount bankAccount = bankAccountOptional.get();
        System.out.println(bankAccount.money);
        System.out.println(itemPrice);
        if (bankAccount.money < itemPrice) return false;
        bankAccount.money -= (itemPrice/2);
        bankAccountRepository.save(bankAccount);
        return true;
    }

    public void takeMoney() {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(1);
        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.money = 0;
        bankAccountRepository.save(bankAccount);
    }

    public void addMoney(BankAccount bankAccount) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(1);
        if (optionalBankAccount.isPresent()) {
            BankAccount existingBankAccount = optionalBankAccount.get();
            existingBankAccount.money += bankAccount.getMoney();
            bankAccountRepository.save(existingBankAccount);
        }
    }

}
