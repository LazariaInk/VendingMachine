package md.lazari.vendingmachine.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vendingHistory")
public class VendingMachineHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    public LocalDate date;

    @Column(name = "info")
    public String info;

    public VendingMachineHistory() {

    }

    public VendingMachineHistory(LocalDate date, String info) {
        this.date = date;
        this.info = info;
    }
}