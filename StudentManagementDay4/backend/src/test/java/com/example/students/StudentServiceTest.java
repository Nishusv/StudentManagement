package com.example.students;
import org.junit.jupiter.api.Test;import org.junit.jupiter.api.extension.ExtendWith;import org.mockito.InjectMocks;import org.mockito.Mock;import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class) class StudentServiceTest {
 @Mock StudentRepository repository; @InjectMocks StudentService service;
 @Test void findByIdReturnsStudentWhenPresent(){Student student=new Student("Asha","asha@example.com","Java",85.0);when(repository.findById(7L)).thenReturn(Optional.of(student));assertEquals("Asha",service.findById(7L).getName());}
 @Test void findByIdThrowsWhenMissing(){when(repository.findById(99L)).thenReturn(Optional.empty());assertThrows(StudentNotFoundException.class,()->service.findById(99L));}
 @Test void createRejectsDuplicateEmail(){Student student=new Student("Asha","asha@example.com","Java",85.0);when(repository.findByEmailIgnoreCase("asha@example.com")).thenReturn(Optional.of(student));assertThrows(IllegalArgumentException.class,()->service.create(student));verify(repository,never()).save(any());}
}
