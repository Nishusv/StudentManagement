package com.example.students;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service){this.service=service;}
    @GetMapping public List<Student> all(){return service.findAll();}
    @GetMapping("/{id}") public Student one(@PathVariable Long id){return service.findById(id);}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student create(@Valid @RequestBody Student student){return service.create(student);}
    @PutMapping("/{id}") public Student update(@PathVariable Long id,@Valid @RequestBody Student student){return service.update(id,student);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}
