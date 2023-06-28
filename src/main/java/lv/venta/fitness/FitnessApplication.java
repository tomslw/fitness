package lv.venta.fitness;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.Excersise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.ExcersiseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IExcersiseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class FitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
	}

	@Bean
	public CommandLineRunner testModel(ExcersiseRepo excersiseRepo, HealthDataRepo healthRepo, MuscleGroupsRepo muscleRepo){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Excersise excersise = new Excersise("Lifting", "Lifting with hand hard", 4, 4, new ArrayList<>(Arrays.asList("bicpes", "triceps")), 200);
				excersiseRepo.save(excersise);
				
				MuscleGroups musGroup1 = new MuscleGroups(Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				MuscleGroups musGroup2 = new MuscleGroups(Intensity.medium, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				
				HealthData healthData1 = new HealthData(80, 180, musGroup1, new ArrayList<Meal>(), new ArrayList<Excersise>(Arrays.asList(excersise)), 2500, LocalDateTime.now().plusDays(-1), null);
				HealthData healthData2 = new HealthData(80, 180, musGroup2, new ArrayList<Meal>(), new ArrayList<Excersise>(), 2500, LocalDateTime.now().plusDays(-2), null);
				
				healthRepo.save(healthData1);
				healthRepo.save(healthData2);
				
				muscleRepo.save(musGroup1);
				muscleRepo.save(musGroup2);
			}
		};
	}
}
