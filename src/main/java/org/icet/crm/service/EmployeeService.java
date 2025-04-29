package org.icet.crm.service;

import org.icet.crm.dto.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long empId, Employee employee);
    List<Employee> getAllEmployees();
    Boolean deleteEmployee(Long empId);
    Employee getEmployeeById(Long id);
}
