package com.jj.cims.repository;

import com.jj.cims.entity.Employee;
import com.jj.cims.entity.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {
    List<Enfant> findByEmployee(Employee employee);
}