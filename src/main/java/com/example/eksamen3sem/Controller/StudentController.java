package com.example.eksamen3sem.Controller;

import com.example.eksamen3sem.Model.Student;
import com.example.eksamen3sem.Repository.StudentRepo;
import com.example.eksamen3sem.Repository.SupervisorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudentController {

    private StudentRepo studentRepo;
    private SupervisorRepo supervisorRepo;

    public StudentController(StudentRepo studentRepo, SupervisorRepo supervisorRepo) {
        this.studentRepo = studentRepo;
        this.supervisorRepo = supervisorRepo;
    }

    @GetMapping("/index")
    public String readAllStudent(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("supervisors", supervisorRepo.findAll());
        model.addAttribute("student",new Student());
        return "student";
    }

    @PostMapping("createStudent")
    public String createStudent (@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/index";
    }

    @GetMapping("/editStudent/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepo.findById(id).orElse(null);
        model.addAttribute("supervisors", supervisorRepo.findAll());
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/updateStudent/{id}")
    public String updateStudent (@PathVariable("id") Long id, @ModelAttribute Student student, Model model) {
        student.setId(id);
        studentRepo.save(student);
        model.addAttribute("student", studentRepo.findAll());

        return "redirect:/index";
    }

    @GetMapping("deleteStudent/{id}")
    public String deleteStudent (@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/index";
    }
}

