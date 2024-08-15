package com.jj.cims.controllers;

import com.jj.cims.entity.Enfant;
import com.jj.cims.entity.Employee;
import com.jj.cims.repository.EmployeeRepository;
import com.jj.cims.service.enfantservice;
import com.jj.cims.service.emplyeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfants")
public class EnfantController {

    @Autowired
    private enfantservice enfantService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private emplyeeservice employeeService;

    // Endpoint to get all enfants by employee's matricule
    @GetMapping("/by-employee")
    public ResponseEntity<List<Enfant>> findByEmployee(@RequestParam int matricule) {
        try {
            Employee employee = employeeRepository.findByMatricule(matricule);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            List<Enfant> enfants = enfantService.findByEmployee(employee);
            return ResponseEntity.ok(enfants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to add an enfant to an employee
    @PostMapping("/employees/{matricule}/enfants")
    public ResponseEntity<Enfant> addEnfantToEmployee(
            @PathVariable int matricule,
            @RequestBody Enfant enfant
    ) {
        try {
            Enfant addedEnfant = employeeService.addEnfantToEmployee(matricule, enfant);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedEnfant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
