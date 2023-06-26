package lv.venta.fitness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ingredient_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {
    @Column(name = "idin")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long idin;

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
	@JoinColumn(name="idme")
	private Meal meal;
    
    public Ingredient(String title, String description, int calories, int fat, int carbohydrates, int protein) {
        this.title = title;
        this.description = description;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }
}
