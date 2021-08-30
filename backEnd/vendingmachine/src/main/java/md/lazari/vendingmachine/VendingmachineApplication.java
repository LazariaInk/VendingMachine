package md.lazari.vendingmachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.lazari.vendingmachine.model.Item;
import md.lazari.vendingmachine.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class VendingmachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(ItemService itemService){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Item>> typeReference = new TypeReference<List<Item>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/config.json");
			try {
				List<Item> users = mapper.readValue(inputStream,typeReference);
				itemService.saveAll(users);
				System.out.println("Items Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}

}
