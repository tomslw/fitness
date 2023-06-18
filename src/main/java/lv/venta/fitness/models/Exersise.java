package lv.venta.fitness.models;

import java.util.ArrayList;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Exersise {
	
	private String title;
	
	private String description;
	
	private float restInterval;
	
	private int repetitions;
	
	private ArrayList<String> target_muscles;
	
	private float addedWeight;
}
