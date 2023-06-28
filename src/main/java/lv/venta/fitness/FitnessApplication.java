package lv.venta.fitness;

import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
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
			
				Excersise excersise = new Excersise("Bicep curls", "Barbell bicep curl", 2, 10, new ArrayList<>(Arrays.asList("biceps")), 20);
				excersiseRepo.save(excersise);
			}
		};
	}														
}
