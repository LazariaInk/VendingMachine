package md.lazari.vendingmachine.repository;

import md.lazari.vendingmachine.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {
}
