package md.lazari.vendingmachine.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
//TODO: Sa schimb din Back in Bank si repository
@Data
@Entity
@Table(name = "backAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "money")
    public double money;
}
