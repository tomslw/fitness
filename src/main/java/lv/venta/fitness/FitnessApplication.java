package lv.venta.fitness;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.services.IExcersiseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
	}

	@Bean
	public CommandLineRunner testModel(ExcersiseRepo excersiseRepo){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Excersise excersise = new Excersise("Wanking", "Wanking with hand hard", 4, 4, new ArrayList<>(Arrays.asList("bicpes", "triceps")), 200);
				excersiseRepo.save(excersise);
			}
		};
	}
}
