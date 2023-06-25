package lv.venta.fitness.models;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "exercise_table")
@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Excersise {
	
	@Column(name = "Idex")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long ide;
	
	@Column(name = "Title")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	private String title;
	
	@Column(name = "Description")
	@Size(min = 3, max = 100)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	private String description;
	
	@Column(name = "RestInterval")
	@Min(0)
	@Max(10)
	private float restInterval;
	
	@Column(name = "Repetitions")
	@Min(1)
	@Max(100)
	private int repetitions;
	
	@Column(name = "TargetMuscles")
	@NotNull
	private ArrayList<String> targetMuscles;
	
	@Column(name = "AddedWeight")
	@Min(1)
	@Max(1000)
	private float addedWeight;
	
	@ManyToOne
	@JoinColumn(name="idhe")
	private HealthData healthData;

	public Excersise(
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") String title,
			@Size(min = 3, max = 100) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") String description,
			@Min(0) @Max(10) float restInterval, @Min(1) @Max(100) int repetitions,
			@NotNull ArrayList<String> targetMuscles, @Min(1) @Max(1000) float addedWeight) {
		this.title = title;
		this.description = description;
		this.restInterval = restInterval;
		this.repetitions = repetitions;
		this.targetMuscles = targetMuscles;
		this.addedWeight = addedWeight;
	}
	
	
}
