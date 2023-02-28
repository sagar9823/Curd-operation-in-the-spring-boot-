package com.example.Curd.controller;


import com.example.Curd.exception.ResorceNotFoundException;
import com.example.Curd.model.Employee;
import com.example.Curd.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);

    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeByid(@PathVariable long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResorceNotFoundException("Employee not exist by id" + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee>updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepo.findById(id).orElseThrow(()-> new ResorceNotFoundException("Employee not exist with id"+id));
//        updateEmployee.setId(employeeDetails.getId());
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmail(employeeDetails.getEmail());


        employeeRepo.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);





    }

  @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>deleteEmployee(@PathVariable long id){
             Employee employee =employeeRepo.findById(id).orElseThrow(()-> new ResorceNotFoundException("The id is not exist"+id));

             employeeRepo.delete(employee);

             return new  ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}