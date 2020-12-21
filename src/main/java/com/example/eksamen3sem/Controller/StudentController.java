package com.example.eksamen3sem.Controller;

import com.example.eksamen3sem.Model.Student;
import com.example.eksamen3sem.Repository.StudentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    @GetMapping("/index")
    public String readAllStudent(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        //model.addAttribute("supervisor", supervisorRepo.findAll();
        model.addAttribute("student",new Student());
        return "student";
    }

    /* //har lagt den op i den første GetMapping så behøver ikke den her
    @GetMapping("/opretStudent")
    public String createFormProduct(Model model) {
        model.addAttribute("student",new Student());
        return "student";
    } */

    @PostMapping("createStudent")
    public String createStudent (@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/index";
    }




}
