package md.lazari.vendingmachine.repository;

import md.lazari.vendingmachine.model.VendingMachineHistory;
import org.springframework.data.repository.CrudRepository;

public interface VendingMachineHistoryRepository extends CrudRepository<VendingMachineHistory,Integer> {
}
