package web.app.pavelk.message4.base1.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.app.pavelk.message4.base1.model.Student;
import web.app.pavelk.message4.base1.model.repo.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class MainController {

    private final StudentRepository studentRepository;

    @GetMapping("/")
    public Student getG() {
        return studentRepository.findById("Eng2015001").get();
    }

    @GetMapping("/s")
    public Student getSave() {
        Student student = Student.builder()
                .id("Eng2015001").name("John Doe").gender(Student.Gender.MALE).grade(1)
                .build();
        return studentRepository.save(student);
    }

    @GetMapping("/u")
    public Student getUpdate() {
        Student retrievedStudent = studentRepository.findById("Eng2015001").get();
        retrievedStudent.setName("Richard Watson");
        return studentRepository.save(retrievedStudent);
    }

    @GetMapping("/d")
    public void getDelete() {
        studentRepository.deleteById("Eng2015001");
    }

    @GetMapping("/ss")
    public Iterable<Student> getSave2() {
        Student student1 = Student.builder()
                .id("Eng2015001").name("John Doe").gender(Student.Gender.MALE).grade(1)
                .build();
        Student student2 = Student.builder()
                .id("Med2015001").name("Gareth Houston").gender(Student.Gender.MALE).grade(2)
                .build();
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        return studentRepository.saveAll(list);
    }

    @GetMapping("/l")
    public Iterable<Student> getList() {
        return studentRepository.findAll();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(NoSuchElementException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
