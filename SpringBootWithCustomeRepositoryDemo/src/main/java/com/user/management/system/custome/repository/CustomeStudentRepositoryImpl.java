package com.user.management.system.custome.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.user.management.system.entity.Student;

@Transactional
public class CustomeStudentRepositoryImpl implements CustomeStudentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Student> getAllStudents() {
		return (List<Student>)entityManager.createQuery("FROM Student").getResultList();
	}

	@Override
	public void persist(Student student) {
		this.entityManager.persist(student);
	}

}
