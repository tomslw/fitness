package lv.venta.fitness.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Column(name = "idus")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idus;
	
	@Column(name = "Name")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String name;
	
	@Column(name = "Surname")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String surname;
	
	@Column(name = "Username")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z,a-z,0-9,_\\ ]+", message = "Only latin letters")
	@NotNull
	private String username;
	
	private String password;
	
	@Column(name = "Birthday")
	@NotNull
	private LocalDateTime birthday;
	
	@Column(name = "weight_goal")
	@Min(0)
	@Max(200)
	private float weightGoal;
	
	@OneToMany(mappedBy="user")
	@ToString.Exclude
	@NotNull
	private Collection<HealthData> allHealthData;

	public User(
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String name,
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String surname,
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z,a-z,0-9,_\\ ]+", message = "Only latin letters") @NotNull String username,
			String password, @NotNull LocalDateTime birthday, @Min(0) @Max(200) float weightGoal) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.weightGoal = weightGoal;
	}
	
	
}
