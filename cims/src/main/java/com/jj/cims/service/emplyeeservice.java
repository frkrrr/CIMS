package com.jj.cims.service;

import com.jj.cims.entity.Employee;
import com.jj.cims.entity.Enfant;
import org.springframework.stereotype.Service;
import com.jj.cims.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class emplyeeservice {

    private final EmployeeRepository employeeRepository;

    public emplyeeservice(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

@Transactional
    public Enfant addEnfantToEmployee(int matricule, Enfant enfant) {
        Employee employee = employeeRepository.findByMatricule(matricule);
        if (employee == null) {
            throw new RuntimeException("Employee not found with matricule: " + matricule);
        }

        enfant.setEmployee(employee); // Set the employee for the enfant
        employee.getEnfants().add(enfant); // Add enfant to employee's list of enfants
        employeeRepository.save(employee); // Save the updated employee with new enfant

        return enfant;

    }
    public Employee addEmployee(Employee employee) {
        // Vous pouvez ajouter une logique supplémentaire si nécessaire
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

