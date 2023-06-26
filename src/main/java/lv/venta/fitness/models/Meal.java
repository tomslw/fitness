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

//    @OneToMany(mappedBy="Ingredient")
    @OneToMany(mappedBy = "meal")
    @ToString.Exclude
    private Collection<Ingredient> ingredientCollection = new ArrayList<>();

    public Meal(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
