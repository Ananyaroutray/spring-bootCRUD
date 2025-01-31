package com.example.employeedemo.service;

import com.example.employeedemo.entity.Employee;
import com.example.employeedemo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
   private EmployeeRepo repo;

    public List<Employee> getAllEmployee(){
        return repo.findAll();

    }

    public Optional<Employee> getEmployById(Long id){
        return repo.findById(id);
    }

    public Employee createEmployee(Employee employee){
        return repo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee newEmp){
        Employee emp = repo.findById(id).orElse(null);

        if(emp!=null){
            return repo.save(newEmp);
        }else{
            throw new RuntimeException("User not found");
        }
    }

    public boolean deleteEmployee(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
