package lv.venta.fitness;

import lv.venta.fitness.enums.Intensity;
import lv.venta.fitness.models.Exercise;
import lv.venta.fitness.models.HealthData;
import lv.venta.fitness.models.Meal;
import lv.venta.fitness.models.MuscleGroups;
import lv.venta.fitness.repos.ExerciseRepo;
import lv.venta.fitness.repos.HealthDataRepo;
import lv.venta.fitness.repos.MuscleGroupsRepo;
import lv.venta.fitness.services.IExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
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
	public CommandLineRunner testModel(ExerciseRepo ExerciseRepo, HealthDataRepo healthRepo, MuscleGroupsRepo muscleRepo){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				
				MuscleGroups musGroup1 = new MuscleGroups(Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				MuscleGroups musGroup2 = new MuscleGroups(Intensity.medium, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				MuscleGroups musGroup3 = new MuscleGroups(Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				
				Exercise Exercise = new Exercise("Bicep curls", "Barbell bicep curl", 2, 10, musGroup3, 20);
				ExerciseRepo.save(Exercise);
				
				HealthData healthData1 = new HealthData(80, 180, musGroup1, 2500, LocalDate.now().plusDays(-1));

				healthData1.addExercise(Exercise);

				HealthData healthData2 = new HealthData(80, 180, musGroup2, 2500, LocalDate.now().plusDays(-2));
				
				healthRepo.save(healthData1);
				healthRepo.save(healthData2);
				
				muscleRepo.save(musGroup1);
				muscleRepo.save(musGroup2);
			}
		};
	}														
}
