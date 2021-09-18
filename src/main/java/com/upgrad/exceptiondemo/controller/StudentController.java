package com.upgrad.exceptiondemo.controller;

import com.upgrad.exceptiondemo.exception.DBConnectivityException;
import com.upgrad.exceptiondemo.exception.RequestedResourceNotFoundException;
import com.upgrad.exceptiondemo.model.ErrorModel;
import com.upgrad.exceptiondemo.model.Student;
import com.upgrad.exceptiondemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService service;

    @GetMapping("/v1/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId){
        return ResponseEntity.ok(service.getStudent(studentId));
    }

    @PostMapping("/v1/students")
    public ResponseEntity<Student> getStudent(@Valid @RequestBody Student student){
        return ResponseEntity.ok(service.save(student));
    }

    @GetMapping("/v1/students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = null;
            try {
                students = service.getAllStudents();
            }catch (DBConnectivityException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Student List is empty");
            }
        return ResponseEntity.ok(students);
    }

    @ExceptionHandler(RequestedResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorModel handleRequestedResourceNotFoundException(){
        return ErrorModel.builder().errorCode("ERR_STUDENT_001").errorMessage("Student Not Found").build();
    }
}
