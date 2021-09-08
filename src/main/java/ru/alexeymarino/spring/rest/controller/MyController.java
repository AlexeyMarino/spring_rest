package ru.alexeymarino.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexeymarino.spring.rest.entity.Employee;
import ru.alexeymarino.spring.rest.exception_handling.EmployeeIncorrectData;
import ru.alexeymarino.spring.rest.exception_handling.NoSuchEmployeeException;
import ru.alexeymarino.spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            throw new NoSuchEmployeeException("Работника с ID = " + id + " в базеданных не найдено.");
        }
        return employee;
    }
}
