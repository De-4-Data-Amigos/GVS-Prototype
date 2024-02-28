package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDao;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Employee;
import dk.lyngby.model.Manager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeeDAO implements IDao<Employee, Integer> {

    private static EmployeeDAO instance;

    private static EntityManagerFactory emf;

    public static EmployeeDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeDAO();
        }
        return instance;
    }

    @Override
    public Employee read(Integer id) throws ApiException {
        try (var em = emf.createEntityManager()) {
            return em.find(Employee.class, id);
        }
    }

    @Override
    public List<Employee> readAll() throws ApiException {
        try (var em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        }
    }

    @Override
    public Employee create(Employee employee) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Get the manager from the database
            Manager manager = em.find(Manager.class, 1); // assuming manager ID is 1

            if (manager != null) {
                // Associate the employee with the manager
                manager.addEmployee(employee);
                // Persist the manager to update the association in the database
                em.merge(manager);
            } else {
                // Handle the case where the manager doesn't exist
                throw new ApiException(404, "Manager not found");
            }

            // Persist the employee
            em.persist(employee);
            em.getTransaction().commit();

            return employee;
        }





     /*   Employee newEmployee = new Employee();
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setTitle(employee.getTitle());
        return newEmployee;*/
    }

    @Override
    public Employee update(Integer id, Employee employee) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee employeeToUpdate = em.find(Employee.class, id);
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setTitle(employee.getTitle());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeToUpdate.setPhone(employee.getPhone());
            Employee employeeMerge= em.merge(employeeToUpdate);
            em.getTransaction().commit();
            return employeeMerge;
        }
    }

    @Override
    public boolean delete(Integer id) throws ApiException {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.remove(employee);
            em.getTransaction().commit();
        }

        return false;
    }

    public boolean validatePrimaryKey(Integer integer) {
        try (var em = emf.createEntityManager()) {
            var employee = em.find(Employee.class, integer);
            return employee != null;

        }
    }
}
