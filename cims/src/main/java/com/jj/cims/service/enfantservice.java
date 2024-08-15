package com.jj.cims.service;

import com.jj.cims.entity.Employee;
import com.jj.cims.entity.Enfant;
import com.jj.cims.repository.EnfantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class enfantservice {

    private final EnfantRepository enfantRepository;
    public List<Enfant> findByEmployee(Employee employee) {
        return enfantRepository.findByEmployee(employee);
    }
    public enfantservice(EnfantRepository enfantRepository) {
        this.enfantRepository = enfantRepository;
    }

    public Enfant addEnfant(Enfant enfant) {
        return enfantRepository.save(enfant);
    }

    public List<Enfant> getAllEnfants() {
        return enfantRepository.findAll();
    }

    /*public Enfant getEnfantById(Long id) {
        Optional<Enfant> enfant = enfantRepository.findByEmployee(id);
        return enfant.orElse(null);
    }*/
}
