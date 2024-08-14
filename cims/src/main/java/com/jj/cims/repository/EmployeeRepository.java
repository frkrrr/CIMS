package com.jj.cims.repository;

import com.jj.cims.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   /* static boolean findBymatricule(int matricule) {
        return false;
    }*/

   Employee findByMatricule(Integer matricule);
}
