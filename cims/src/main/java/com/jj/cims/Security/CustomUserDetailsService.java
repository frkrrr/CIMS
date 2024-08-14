package com.jj.cims.Security;

import com.jj.cims.entity.Employee;
import com.jj.cims.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByMatricule(Integer.parseInt(matricule));
        if (employee == null) {
            throw new UsernameNotFoundException("User not found with matricule: " + matricule);
        }
        return new CustomUserDetails(employee);
    }
}
