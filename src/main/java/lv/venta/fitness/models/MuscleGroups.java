package lv.venta.fitness.models;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.fitness.enums.Intensity;

@Table(name = "muscle_groups_table")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MuscleGroups {
	
	@Column(name = "idmg")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idmg;
	
	@Column(name = "chest")
	@NotNull
	private Intensity chest;
	
	@Column(name = "back")
	@NotNull
	private Intensity back;
	
	@Column(name = "biceps")
	@NotNull
	private Intensity biceps;
	
	@Column(name = "triceps")
	@NotNull
	private Intensity triceps;
	
	@Column(name = "forarms")
	@NotNull
	private Intensity forarms;
	
	@Column(name = "abdomen")
	@NotNull
	private Intensity abdomen;
	
	@Column(name = "calves")
	@NotNull
	private Intensity calves;
	
	@Column(name = "hamstrings")
	@NotNull
	private Intensity hamstrings;
	
	@Column(name = "quadriceps")
	@NotNull
	private Intensity quadriceps;
	
	@Column(name = "glutes")
	@NotNull
	private Intensity glutes;
	
	@Column(name = "trapezius")
	@NotNull
	private Intensity trapezius;
	
	@Column(name = "latissimus_dorsi")
	@NotNull
	private Intensity latissimus_dorsi;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "muscleGroups")
	private HealthData healthData;
	
}
