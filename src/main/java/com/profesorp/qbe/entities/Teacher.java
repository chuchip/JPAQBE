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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Id
	Integer id;
	
	String nombre;
	String materia;
	String colegio;
		
	Integer numeroClase;
}
