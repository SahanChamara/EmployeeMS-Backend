package org.icet.crm.repository;

import org.icet.crm.dto.Employee;
import org.icet.crm.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Boolean existsByEmail(String email);
}
