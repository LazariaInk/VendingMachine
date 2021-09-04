package md.lazari.vendingmachine.service;

import md.lazari.vendingmachine.model.VendingMachineHistory;
import md.lazari.vendingmachine.repository.VendingMachineHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VendingMachineHistoryService {

    private final VendingMachineHistoryRepository vendingMachineHistoryRepository;

    @Autowired
    public VendingMachineHistoryService(VendingMachineHistoryRepository vendingMachineHistoryRepository) {
        this.vendingMachineHistoryRepository = vendingMachineHistoryRepository;
    }

    public List<VendingMachineHistory> findAll(){
        return vendingMachineHistoryRepository.findAll();
    }

}

