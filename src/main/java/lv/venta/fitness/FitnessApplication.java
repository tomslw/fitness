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
				
				MuscleGroups triceps = new MuscleGroups(Intensity.none, Intensity.none, Intensity.low, Intensity.high, Intensity.medium, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.none, null);
				MuscleGroups shoulders = new MuscleGroups(Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.medium, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.high, null);
				MuscleGroups biceps = new MuscleGroups(Intensity.medium, Intensity.low, Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, null);
				MuscleGroups squat = new MuscleGroups(Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.none, Intensity.low, Intensity.low, Intensity.high, Intensity.high, Intensity.high, Intensity.low, Intensity.none, null);
				MuscleGroups chest = new MuscleGroups(Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);
				MuscleGroups deadlift = new MuscleGroups(Intensity.high, Intensity.medium, Intensity.medium, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, Intensity.low, null);

				Excersise excersise = new Excersise("Bicep curls", "Barbell bicep curl", 2, 12, biceps, 20);
				Excersise excersise2 = new Excersise("Squats", "Squats with bar", 4, 12, squat, 80);
				Excersise excersise3 = new Excersise("Dead-lift", "Picking up bar with heavy weight", 5, 6, deadlift, 120);
				Excersise excersise4 = new Excersise("Bench press", "Pressing a bar from your chest", 3, 12, chest, 60);
				Excersise excersise5 = new Excersise("Triceps pulldowns", "Pulling down weight with triceps", 2, 10, triceps, 20);
				Excersise excersise6 = new Excersise("Shoulder press", "Lifting bar with weights, military press", 2, 10, shoulders, 10);

				excersiseRepo.save(excersise);
				excersiseRepo.save(excersise2);
				excersiseRepo.save(excersise3);
				excersiseRepo.save(excersise4);
				excersiseRepo.save(excersise5);
				excersiseRepo.save(excersise6);

				HealthData healthData1 = new HealthData(80, 180, triceps, 2500, LocalDate.now().plusDays(-1));
				HealthData healthData2 = new HealthData(80, 180, shoulders, 1500, LocalDate.now().plusDays(-2));
				HealthData healthData3 = new HealthData(79, 180, biceps, 1200, LocalDate.now().plusDays(-3));
				HealthData healthData4 = new HealthData(79, 180, chest, 1500, LocalDate.now().plusDays(-4));
				HealthData healthData5 = new HealthData(77, 180, squat, 2500, LocalDate.now().plusDays(-5));
				HealthData healthData6 = new HealthData(77, 180, deadlift, 2500, LocalDate.now().plusDays(-5));

				healthData1.addExercise(excersise5);
				healthData2.addExercise(excersise6);
				healthData3.addExercise(excersise);
				healthData4.addExercise(excersise4);
				healthData5.addExercise(excersise2);
				healthData6.addExercise(excersise3);

				healthRepo.save(healthData1);
				healthRepo.save(healthData2);
				healthRepo.save(healthData3);
				healthRepo.save(healthData4);
				healthRepo.save(healthData5);
				healthRepo.save(healthData6);

				muscleRepo.save(triceps);
				muscleRepo.save(biceps);
				muscleRepo.save(squat);
				muscleRepo.save(chest);
				muscleRepo.save(shoulders);
				muscleRepo.save(deadlift);


			}
		};
	}														
}
