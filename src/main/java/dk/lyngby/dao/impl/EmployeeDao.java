package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDao;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Employee;

import java.util.List;

public class EmployeeDao implements IDao<Employee, Integer> {
    @Override
    public Employee read(Integer id) throws ApiException {
        return null;
    }

    @Override
    public List<Employee> readAll() throws ApiException {
        return null;
    }

    @Override
    public Employee create(Employee employee) throws ApiException {
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setTitle(employee.getTitle());
        return newEmployee;
    }

    @Override
    public Employee update(Integer id, Employee employee) throws ApiException {
        return null;
    }

    @Override
    public void delete(Integer id) throws ApiException {

    }
}
