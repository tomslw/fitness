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
				
				MuscleGroups triceps2 = new MuscleGroups(Intensity.none, Intensity.none, Intensity.low, Intensity.high, Intensity.medium, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.none, null);
				MuscleGroups shoulders2 = new MuscleGroups(Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.medium, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.high, null);
				MuscleGroups biceps2 = new MuscleGroups(Intensity.medium, Intensity.low, Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, null);
				MuscleGroups squat2 = new MuscleGroups(Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.low, Intensity.high, Intensity.high, Intensity.high, Intensity.low, Intensity.none, null);
				MuscleGroups chest2 = new MuscleGroups(Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				MuscleGroups deadlift2 = new MuscleGroups(Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);

				HealthData healthData1 = new HealthData(80, 180, triceps2, 2500, LocalDate.now().plusDays(-1));
				HealthData healthData2 = new HealthData(80, 180, shoulders2, 1500, LocalDate.now().plusDays(-2));
				HealthData healthData3 = new HealthData(79, 180, biceps2, 1200, LocalDate.now().plusDays(-3));
				HealthData healthData4 = new HealthData(79, 180, chest2, 1500, LocalDate.now().plusDays(-4));
				HealthData healthData5 = new HealthData(77, 180, squat2, 2500, LocalDate.now().plusDays(-5));
				HealthData healthData6 = new HealthData(77, 180, deadlift2, 2500, LocalDate.now().plusDays(-6));

				healthRepo.save(healthData1);
				healthRepo.save(healthData2);
				healthRepo.save(healthData3);
				healthRepo.save(healthData4);
				healthRepo.save(healthData5);
				healthRepo.save(healthData6);

			}
		};
	}														
}
