package org.icet.crm.controller;


import lombok.RequiredArgsConstructor;
import org.icet.crm.dto.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class EmployeeController {

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return null;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return  null;
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
