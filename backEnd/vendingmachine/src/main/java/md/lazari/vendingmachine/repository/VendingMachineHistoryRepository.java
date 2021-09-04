package md.lazari.vendingmachine.repository;

import md.lazari.vendingmachine.model.VendingMachineHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingMachineHistoryRepository extends JpaRepository<VendingMachineHistory, Integer> {
}
