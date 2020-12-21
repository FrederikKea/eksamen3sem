package com.example.eksamen3sem.Controller;

import com.example.eksamen3sem.Model.Student;
import com.example.eksamen3sem.Model.Supervisor;
import com.example.eksamen3sem.Repository.StudentRepo;
import com.example.eksamen3sem.Repository.SupervisorRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class RestStudentController {

    StudentRepo studentRepo;
    SupervisorRepo supervisorRepo;

    public RestStudentController(StudentRepo studentRepo, SupervisorRepo supervisorRepo) {
        this.studentRepo = studentRepo;
        this.supervisorRepo = supervisorRepo;
    }

    //HTTP Get List of students
    @GetMapping("/student")
    public Iterable<Student> findAll(){
        System.out.println(studentRepo.findAll());
        return studentRepo.findAll();
    }

    //HTTP Get By ID
    @GetMapping("student/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id) {
        Optional<Student> student = studentRepo.findById(id);
        if (!student.isPresent()){
            return ResponseEntity.status(200).body(student); //ok
        } else {
            return ResponseEntity.status(404).body(student); // Not found
        }
    }

    // HTTP Post create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/student", consumes = {"application/json"})
    public ResponseEntity<String> create(@RequestBody Student s){
        Student student = new Student(s.getName(),s.getEmail());
        studentRepo.save(student);

        /*
        Supervisor supervisor = s.getSupervisor();
        supervisor.setStudents(supervisor.getStudents());
        supervisorRepo.save(supervisor);
*/
        //Set<Supervisor> supervisors = s.getSupervisor();



        return ResponseEntity.status(201).header("Location","/student/" + s.getId()).body("{'Msg': 'post created'}");
    }

    // HTTP Put update
    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student s) {
        //get studentById
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (!optionalStudent.isPresent()){
            //Student id findes ikke
            return ResponseEntity.status(404).body("{'msg' : 'Not found'}");
        }
        //opdater Supervisor automatisk - nu da relationen er oprettet
        studentRepo.save(s);
        return ResponseEntity.status(204).body("{msg' : 'Updated}'");
    }

    // HTTP delete delete
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Student> optionalStudent = studentRepo.findById(id);
        //Check at student findes
        if(!optionalStudent.isPresent()){
            return ResponseEntity.status(404).body("{'msg' : 'Not found'}");
        }
        studentRepo.deleteById(id);
        return ResponseEntity.status(200).body("{msg' : 'Deleted}'");
    }
}
