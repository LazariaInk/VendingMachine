package md.lazari.vendingmachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.lazari.vendingmachine.model.BankAccount;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.repository.BankAccountRepository;
import md.lazari.vendingmachine.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class VendingmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ItemService itemService, BankAccountRepository bankAccountRepository){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Item>> typeReference = new TypeReference<List<Item>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/item.json");
			try {
				List<Item> items = mapper.readValue(inputStream,typeReference);
				itemService.saveAll(items);
				System.out.println("Items Saved!");
				// TODO: new method
				Optional<BankAccount> backAccountOptional = bankAccountRepository.findById(1);
				if (!backAccountOptional.isPresent()) {
					BankAccount bankAccount = new BankAccount();
					bankAccount.money = 0.0;
					bankAccountRepository.save(bankAccount);
				}
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}

}
