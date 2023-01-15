package ar.com.morenofernando.d4t.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 50, message = "no cumple con la longitud")
	private String name;
	
	@NotNull
	@Size(min = 3, max = 50, message = "no cumple con la longitud")
	private String surname;
	
	@Size(min = 3, max = 50, message = "no cumple con la longitud")
	private String img;
	
	
}
