package com.example.students;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank @Size(max=80) private String name;
    @NotBlank @Email @Column(unique=true) private String email;
    @NotBlank @Size(max=80) private String course;
    @NotNull @DecimalMin("0.0") @DecimalMax("100.0") private Double marks;

    protected Student() {}
    public Student(String name, String email, String course, Double marks) { this.name=name; this.email=email; this.course=course; this.marks=marks; }
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
    public String getCourse(){return course;} public Double getMarks(){return marks;}
    public void setName(String name){this.name=name;} public void setEmail(String email){this.email=email;}
    public void setCourse(String course){this.course=course;} public void setMarks(Double marks){this.marks=marks;}
}
