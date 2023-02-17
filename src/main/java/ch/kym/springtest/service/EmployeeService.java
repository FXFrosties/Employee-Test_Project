package ch.kym.springtest.service;

import java.util.List;

import ch.kym.springtest.model.Employee;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployee(long id);
    void deleteEmployee(long id);
    Employee updateEmployee(Employee employee);
}
