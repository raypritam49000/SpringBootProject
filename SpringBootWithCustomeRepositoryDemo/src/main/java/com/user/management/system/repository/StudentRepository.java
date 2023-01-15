package com.user.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.system.custome.repository.CustomeStudentRepository;
import com.user.management.system.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, CustomeStudentRepository{

}
