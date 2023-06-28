package lv.venta.fitness.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
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
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Excersise {
	
	@Column(name = "Idex")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idex;
	
	@Column(name = "title")
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
	
	@OneToOne(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "idmg")
	private MuscleGroups targetMuscles;
	
	@Column(name = "AddedWeight")
	@Min(1)
	@Max(1000)
	private float addedWeight;
	
	@ManyToMany
	@ToString.Exclude
    @JsonIgnore
	@JoinTable(
			name="exercise_healthData_table",
			joinColumns = @JoinColumn(name = "idex"),
			inverseJoinColumns = @JoinColumn(name="idhe"))
	private Collection<HealthData> healthDataCollection = new ArrayList<>();

	public Excersise(
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") String title,
			@Size(min = 3, max = 100) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") String description,
			@Min(0) @Max(10) float restInterval, @Min(1) @Max(100) int repetitions,
			@NotNull MuscleGroups targetMuscles, @Min(1) @Max(1000) float addedWeight) {
		this.title = title;
		this.description = description;
		this.restInterval = restInterval;
		this.repetitions = repetitions;
		this.targetMuscles = targetMuscles;
		this.addedWeight = addedWeight;
	}

	public void addHealthData(HealthData healthData) {
		if(healthData!=null && !healthDataCollection.contains(healthData)) {
			healthDataCollection.add(healthData);
		}
	}
	
	public void removeHealthData(HealthData healthData) {
		if(healthDataCollection.contains(healthData)) {
			healthDataCollection.remove(healthData);
		}
	}
}
