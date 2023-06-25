package lv.venta.fitness.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Health_Data_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HealthData {
	
	@Column(name = "weight")
	@Min(0)
	@Max(500)
	float weight;
	
	@Column(name = "height")
	@Min(0)
	@Max(300)
	float height;
	
	@Column(name = "fatiguedMuscles")
	@NotNull
	ArrayList<String> fatiguedMuscles;
	
	@Column(name = "diet")
	@NotNull
	ArrayList<Meal> diet;
	
	@Column(name = "workout")
	@NotNull
	ArrayList<Excersise> workout;
	
	@Column(name = "calories_spent")
	@Min(0)
	@Max(10000)
	int caloriesSpent;
	
	@Column(name = "date")
	@NotNull
	LocalDateTime date;
	
}
