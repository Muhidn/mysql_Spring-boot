package com.tunguu.student.Service;

import com.tunguu.student.Repository.EmployeeRepository;
import com.tunguu.student.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
    // VIEW
    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return repository.findById(id);
    }
    // INSERT
    public Employee saveEmployee(Employee employee){
        return repository.save(employee);
    }
// UPDATE
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    // DElete
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }



}
