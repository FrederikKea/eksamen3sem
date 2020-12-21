package com.example.eksamen3sem.Repository;


import com.example.eksamen3sem.Model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository <Student, Long> {
}
