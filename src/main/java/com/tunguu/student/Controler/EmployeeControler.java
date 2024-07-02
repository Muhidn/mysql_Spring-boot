package com.tunguu.student.Controler;

import com.tunguu.student.Service.EmployeeService;
import com.tunguu.student.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




import java.util.Optional;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeControler {
    @Autowired
    private EmployeeService employeeServices;

    // read
    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        List<Employee> emp = employeeServices.getAllEmployee();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

 @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
 {
     Optional<Employee> emp = employeeServices.getEmployeeById(id);
     return emp.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
 }

 // create
    @PostMapping(path = "/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeServices.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

  //update
  @PutMapping(path ="/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Optional<Employee> existingEmployee = employeeServices.getEmployeeById(id);
        if(existingEmployee.isPresent()){
            employee.setId(id);
            Employee updateEmployee = employeeServices.updateEmployee(employee);
            return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  }

  // delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        Optional<Employee> presentEmployee = employeeServices.getEmployeeById(id);
        if(presentEmployee.isPresent()){
            employeeServices.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


