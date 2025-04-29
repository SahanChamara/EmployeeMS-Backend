package org.icet.crm.controller;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.icet.crm.dto.Employee;
import org.icet.crm.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
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
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {

        // This is handle the email validation
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
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

    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId){
        return ResponseEntity.ok(employeeService.getEmployeeById(empId));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(allEmployees);
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long empId, @Valid @RequestBody Employee employee, BindingResult bindingResult) {

        // This is handle the email validation
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Employee savedEmployee = employeeService.updateEmployee(empId, employee);
            return savedEmployee != null ? ResponseEntity.ok(savedEmployee) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Email Already Exist", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @GetMapping(value = "/export", produces = "text/csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=employees.csv");

        List<Employee> employees = employeeService.getAllEmployees(); // Calls your existing service method
        PrintWriter writer = response.getWriter();

        // CSV header
        writer.println("ID,Name,Email,Department,Created At,Updated At");

        // CSV rows
        for (Employee emp : employees) {
            writer.println(String.format("%d,%s,%s,%s,%s,%s",
                    emp.getId(),
                    emp.getName(),
                    emp.getEmail(),
                    emp.getDepartment(),
                    emp.getCreateAt(),
                    emp.getUpdatedAt()
            ));
        }

        writer.flush();
        writer.close();
}

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long empId) {
        Boolean existEmployee = employeeService.deleteEmployee(empId);
        if(Boolean.TRUE.equals(existEmployee)) return ResponseEntity.status(HttpStatus.OK).body(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}
