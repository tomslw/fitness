package lv.venta.fitness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "meal_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meal {
    @Column(name = "idme")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long idme;

    @Column(name = "title")
    @NotNull
    @Size(min = 3, max = 20)
    private String title;

    @Column(name = "description")
    @NotNull
    @Size(min = 3, max = 30)
    private String description;

	@Column(name = "calories")
	@Min(0)
	@Max(100000)
	private int calories;

	@Column(name = "fat")
	@Min(0)
	@Max(100000)
	private int fat;

	@Column(name = "carbohydrates")
	@Min(0)
	@Max(100000)
	private int carbohydrates;

	@Column(name = "protein")
	@Min(0)
	@Max(100000)
	private int protein;

    @ManyToOne
    @JsonIgnore
	@JoinColumn(name="idhe")
	private HealthData healthData;

    @OneToMany(mappedBy = "meal")
    @ToString.Exclude
    private Collection<Meal> meals = new ArrayList<>();

	public Meal(@NotNull @Size(min = 3, max = 20) String title,
			@NotNull @Size(min = 3, max = 30) String description) {
		this.title = title;
		this.description = description;
	}

	public int getCalories() {
		int calorieSum = 0;
		for (Meal meal : meals) {
			calorieSum += meal.getCalories();
		}
		return calorieSum;
	}
	
	public int getFat() {
		int fatSum = 0;
		for (Meal meal : meals) {
			fatSum += meal.getFat();
		}
		return fatSum;
	}
	
	public int getCarbs() {
		int carbSum = 0;
		for (Meal meal : meals) {
			carbSum += meal.getCarbohydrates();
		}
		return carbSum;
	}
	
	public int getProtein() {
		int proteinSum = 0;
		for (Meal meal : meals) {
			proteinSum += meal.getProtein();
		}
		return proteinSum;
	}  
	
}
