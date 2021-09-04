package md.lazari.vendingmachine.controller;

import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.model.VendingMachineHistory;
import md.lazari.vendingmachine.repository.VendingMachineHistoryRepository;
import md.lazari.vendingmachine.service.VendingMachineHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VendingMachineHistoryController {

    private final VendingMachineHistoryService vendingMachineHistoryService;

    @Autowired
    public VendingMachineHistoryController(VendingMachineHistoryService vendingMachineHistoryService) {
        this.vendingMachineHistoryService = vendingMachineHistoryService;
    }


    @GetMapping("/vending-history")
    public String findAll(Model model){
        List<VendingMachineHistory> historyList = vendingMachineHistoryService.findAll();
        model.addAttribute("historyList", historyList);
        return "vending-history";
    }


}
