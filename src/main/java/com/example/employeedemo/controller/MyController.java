package com.example.employeedemo.controller;

import com.example.employeedemo.entity.Employee;
import com.example.employeedemo.repo.EmployeeRepo;
import com.example.employeedemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

   @Autowired
   private EmployeeService service;

   @GetMapping("/show")
   public List<Employee> showEmployee(){
      return service.getAllEmployee();
   }

   public ResponseEntity<Employee> showStudentById(@PathVariable Long id){
      Employee employee = service.getEmployById(id).orElse(null);

      if(employee!=null){
         return ResponseEntity.ok().body(employee);
      }else{
         return ResponseEntity.notFound().build();
      }
   }


   @PostMapping("/add")
   public Employee addEmployee(@RequestBody Employee employee){
      return service.createEmployee(employee);
   }

   @PutMapping("/update/{id}")
   public ResponseEntity<Employee>updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
      Employee emp = service.updateEmployee(id, employee);
      if(emp!=null){
         return ResponseEntity.ok().body(emp);
      }else {
         return ResponseEntity.notFound().build();
      }
   }

   public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
      boolean isDeleted = service.deleteEmployee(id);

      if(isDeleted){
         return ResponseEntity.noContent().build();
      }else{
         return ResponseEntity.notFound().build();
      }
   }

}
