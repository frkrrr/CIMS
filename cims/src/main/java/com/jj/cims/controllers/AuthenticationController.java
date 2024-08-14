package com.jj.cims.controllers;

import com.jj.cims.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/validate-matricule")
    public ResponseEntity<Map<String, Object>> validateMatricule(@RequestParam("matricule") Integer matricule) {
        boolean isValid = employeeRepository.findByMatricule(matricule) != null;
        Map<String, Object> response = new HashMap<>();
        response.put("success", isValid);
        return ResponseEntity.ok(response);
    }
}

