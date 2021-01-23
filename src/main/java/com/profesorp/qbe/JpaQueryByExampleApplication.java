package com.profesorp.qbe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.profesorp.qbe.entities.Alumno;
import com.profesorp.qbe.entities.Profesor;
import com.profesorp.qbe.repositories.AlumnoRepository;
import com.profesorp.qbe.repositories.ProfesorRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@Log4j2
public class JpaQueryByExampleApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(JpaQueryByExampleApplication.class, args);
	}

	@Autowired 	AlumnoRepository alumnRepository;
	@Autowired 	ProfesorRepository teacherRepository;
	
	@PostConstruct
	void iniciar() { 
		log.info("\n\nINSERTANDO PROFESORES .... \n\n");
		var teacher = Profesor.builder().nombre("Nombre1").colegio("Colegio1").materia("Matematicas").numeroClase(55)
				.build();

		guardar(teacherRepository, teacher);

		teacher = Profesor.builder().nombre("Nombre2").colegio("Colegio2").materia("Lengua").numeroClase(55).build();

		guardar(teacherRepository, teacher);
		// Buscando registros cuyo nombre sea "Nombre1" sin importar los demas campos.
		var buscaNombre = Profesor.builder().nombre("Nombre1").build();
		guardar(teacherRepository, buscaNombre);
		
		log.info("\n\nINSERTANDO ALUMNOS .... \n\n");
		var alumno = Alumno.builder().clase("1A").edad(12).nombre("Luis").build();
		guardar(alumnRepository, alumno);
		guardar(alumnRepository, alumno);
		
		alumno = Alumno.builder().clase("1A").edad(13).nombre("Luis").build();
		guardar(alumnRepository, alumno);
		log.info("\n\n");
	}

	void guardar(JpaRepository jpaRepository, Object entidad) {
		Example qbe = Example.of(entidad);
		var registros = jpaRepository.findAll(qbe);
		if (registros.size() > 0)
			log.warn("Ya existe Registro: " + entidad.toString());
		else {
			jpaRepository.save(entidad);
			log.info("Insertado registro: " + entidad.toString());
		}
	}
}
