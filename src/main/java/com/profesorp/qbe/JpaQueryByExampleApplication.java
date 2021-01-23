package com.profesorp.qbe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.profesorp.qbe.entities.Alumn;
import com.profesorp.qbe.entities.Teacher;
import com.profesorp.qbe.repositories.AlumnRepository;
import com.profesorp.qbe.repositories.TeacherRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@SpringBootApplication
@Log
public class JpaQueryByExampleApplication {

	@Autowired
	AlumnRepository alumnRepository;
	@Autowired
	TeacherRepository teacherRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaQueryByExampleApplication.class, args);
	}

	@PostConstruct
	void iniciar() {
		log.info("\n\nINSERTANDO PROFESORES .... \n\n");
		var teacher = Teacher.builder().nombre("Nombre1").colegio("Colegio1").materia("Matematicas").numeroClase(55)
				.build();

		guardar(teacherRepository, teacher);

		teacher = Teacher.builder().nombre("Nombre2").colegio("Colegio2").materia("Lengua").numeroClase(55).build();

		guardar(teacherRepository, teacher);
		teacher = Teacher.builder().nombre("Nombre1").build();
		guardar(teacherRepository, teacher);
		
		log.info("\n\nINSERTANDO ALUMNOS .... \n\n");
		var alumno = Alumn.builder().clase("1A").edad(12).nombre("Luis").build();
		guardar(alumnRepository, alumno);
		guardar(alumnRepository, alumno);
		
		alumno = Alumn.builder().clase("1A").edad(13).nombre("Luis").build();
		guardar(alumnRepository, alumno);
	}

	void guardar(JpaRepository jpaRepository, Object entidad) {
		Example qbe = Example.of(entidad);
		var registros = jpaRepository.findAll(qbe);
		if (registros.size() > 0)
			log.warning("Ya existe Registro: " + qbe.toString());
		else {
			jpaRepository.save(entidad);
			log.info("Insertado registro: " + qbe.toString());
		}
	}
}
