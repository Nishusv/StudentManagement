package com.example.students;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository){this.repository=repository;}
    public List<Student> findAll(){return repository.findAll();}
    public Student findById(Long id){return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));}
    @Transactional public Student create(Student student){
        repository.findByEmailIgnoreCase(student.getEmail()).ifPresent(s -> { throw new IllegalArgumentException("Email is already registered"); });
        student.setEmail(student.getEmail().trim().toLowerCase()); return repository.save(student);
    }
    @Transactional public Student update(Long id, Student input){
        Student current=findById(id);
        repository.findByEmailIgnoreCase(input.getEmail()).filter(other -> !other.getId().equals(id)).ifPresent(s -> { throw new IllegalArgumentException("Email is already registered"); });
        current.setName(input.getName()); current.setEmail(input.getEmail().trim().toLowerCase()); current.setCourse(input.getCourse()); current.setMarks(input.getMarks());
        return repository.save(current);
    }
    public void delete(Long id){ if(!repository.existsById(id)) throw new StudentNotFoundException(id); repository.deleteById(id); }
}
