package org.icet.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.icet.crm.dto.Employee;
import org.icet.crm.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee createEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Boolean deleteEmployee(Long empId) {
        return null;
    }
}
