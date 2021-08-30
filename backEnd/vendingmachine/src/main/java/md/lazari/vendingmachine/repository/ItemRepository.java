package md.lazari.vendingmachine.repository;

import md.lazari.vendingmachine.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Integer> {

}

