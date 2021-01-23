package com.profesorp.qbe.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Alumn {
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Id	
	int id;
	
	String nombre;
	Integer edad;
	String clase;
	
}
