package org.icet.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.icet.crm.dto.Employee;
import org.icet.crm.entity.EmployeeEntity;
import org.icet.crm.repository.EmployeeRepository;
import org.icet.crm.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee != null) {
            if (Boolean.TRUE.equals(employeeRepository.existsByEmail(employee.getEmail()))) {
                throw new IllegalArgumentException("User Already Exist");
            }
            return mapper.map(employeeRepository.save(mapper.map(employee, EmployeeEntity.class)), Employee.class);
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee) {
        Optional<EmployeeEntity> existEmployee = employeeRepository.findById(empId);
        if(existEmployee.isPresent() && !existEmployee.get().getId().equals(empId)){
            throw new IllegalArgumentException("Email Already in use by Another User");
        }
        return mapper.map(employeeRepository.save(mapper.map(employee, EmployeeEntity.class)), Employee.class);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, Employee.class))
                .toList();
    }

    @Override
    public Boolean deleteEmployee(Long empId) {
        Optional<EmployeeEntity> existEmployee = employeeRepository.findById(empId);
        if(existEmployee.isPresent()){
            employeeRepository.deleteById(empId);
            return true;
        }
        return false;
    }
}
