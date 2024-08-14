package com.jj.cims.controllers;
import java.util.HashMap;
import java.util.Map;
import com.jj.cims.repository.EmployeeRepository;
import com.jj.cims.entity.Employee;
import com.jj.cims.entity.Enfant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.jj.cims.service.emplyeeservice;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/employees")
public class employeecontroller {

    @Autowired
    private emplyeeservice employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    // Endpoint to add an enfant to an employee
    @PostMapping("/employees/{matricule}/enfants")
    public ResponseEntity<Enfant> addEnfantToEmployee(
            @PathVariable Integer matricule,
            @RequestBody Enfant enfant
    ) {
        try {
            Enfant addedEnfant = employeeService.addEnfantToEmployee(matricule, enfant);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedEnfant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/validate-matricule")
    public ResponseEntity<Map<String, Object>> validateMatricule(@RequestParam int matricule) {
        Employee employee = employeeRepository.findByMatricule(matricule);
        Map<String, Object> response = new HashMap<>();
        if (employee != null) {
            // Log the retrieved employee details
            System.out.println("Employee found: " + employee.getNom() + " " + employee.getPrenom() + ", Matricule: " + employee.getMatricule());
            response.put("success", true);
            response.put("user", employee);
            return ResponseEntity.ok(response);
        } else {
            System.out.println("Employee not found for matricule: " + matricule);
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/test-employee")
    public ResponseEntity<Employee> getEmployee(@RequestParam int matricule) {
        Employee employee = employeeRepository.findByMatricule(matricule);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee newEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}