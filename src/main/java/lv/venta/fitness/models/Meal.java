package lv.venta.fitness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

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
    
    @ManyToOne
	@JoinColumn(name="idhe")
	private HealthData healthData;

    @OneToMany(mappedBy = "meal")
    @ToString.Exclude
    private Collection<Ingredient> ingredients = new ArrayList<>();

	public Meal(@NotNull @Size(min = 3, max = 20) String title,
			@NotNull @Size(min = 3, max = 30) String description, Collection<Ingredient> ingredients) {
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
	}
	
	public int getCalories() {
		int calorieSum = 0;
		for (Ingredient ingredient : ingredients) {
			calorieSum += ingredient.getCalories();
		}
		return calorieSum;
	}
	
	public int getFat() {
		int fatSum = 0;
		for (Ingredient ingredient : ingredients) {
			fatSum += ingredient.getFat();
		}
		return fatSum;
	}
	
	public int getCarbs() {
		int carbSum = 0;
		for (Ingredient ingredient : ingredients) {
			carbSum += ingredient.getCarbohydrates();
		}
		return carbSum;
	}
	
	public int getProtein() {
		int proteinSum = 0;
		for (Ingredient ingredient : ingredients) {
			proteinSum += ingredient.getProtein();
		}
		return proteinSum;
	}  
	
}
