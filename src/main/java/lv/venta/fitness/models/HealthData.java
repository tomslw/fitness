package lv.venta.fitness.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "Health_Data_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HealthData {
	
	@Column(name = "idhe")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idhe;
	
	@Column(name = "weight")
	@Min(0)
	@Max(500)
	private float weight;
	
	@Column(name = "height")
	@Min(0)
	@Max(300)
	private float height;
	
	@OneToOne(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "idmg")
	private MuscleGroups muscleGroups;
	
//	@OneToMany(mappedBy="Meal")
	@OneToMany(mappedBy = "healthData")
	@NotNull
	private Collection<Meal> diet;
	
	@OneToMany(mappedBy="healthData")
	@ToString.Exclude
	@NotNull
	private Collection<Excersise> workout;
	
	@Column(name = "calories_spent")
	@Min(0)
	@Max(10000)
	private int caloriesSpent;
	
	@Column(name = "date")
	@NotNull
	private LocalDateTime date;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="idus")
	private User user;

	public HealthData(@Min(0) @Max(500) float weight, @Min(0) @Max(300) float height, MuscleGroups muscleGroups,
			@NotNull Collection<Meal> diet, @NotNull Collection<Excersise> workout,
			@Min(0) @Max(10000) int caloriesSpent, @NotNull LocalDateTime date, User user) {
		super();
		this.weight = weight;
		this.height = height;
		this.muscleGroups = muscleGroups;
		this.diet = diet;
		this.workout = workout;
		this.caloriesSpent = caloriesSpent;
		this.date = date;
		this.user = user;
	}
	
	
	
}
