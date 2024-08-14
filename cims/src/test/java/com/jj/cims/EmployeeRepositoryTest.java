package com.jj.cims;

import com.jj.cims.entity.Employee;
import com.jj.cims.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByMatricule() {
        // Setup test data
        Employee employee = new Employee();
        employee.setNom("John");
        employee.setPrenom("Doe");
        employee.setNbreenfant(2);
        employee.setMatricule(1256); // Ensure matricule is set
        employeeRepository.save(employee);

        // Test repository method
        Employee foundEmployee = employeeRepository.findByMatricule(employee.getMatricule());
        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getNom()).isEqualTo("John");
        assertThat(foundEmployee.getPrenom()).isEqualTo("Doe");
        assertThat(foundEmployee.getNbreenfant()).isEqualTo(2);
        assertThat(foundEmployee.getMatricule()).isEqualTo(123456);
    }
}
