package org.icet.crm.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.icet.crm.dto.Employee;
import org.icet.crm.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> errors =  new HashMap<>();
            bindingResult.getFieldErrors().forEach( err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Employee savedEmployee = employeeService.createEmployee(employee);
            return savedEmployee != null ? ResponseEntity.ok(savedEmployee) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
           Map<String, String> error = new HashMap<>();
           error.put("Email Already Exist", e.getMessage());
           return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(allEmployees);
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody Employee employee){
        return null;
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long empId){
        return null;
    }
}
