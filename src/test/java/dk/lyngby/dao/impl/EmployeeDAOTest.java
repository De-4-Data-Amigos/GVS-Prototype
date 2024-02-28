package dk.lyngby.dao.impl;

import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeDAOTest {
 /*   private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee("John Doe", "Software Engineer", "john@example.com", "123456789", "password123", "Employee");
    }*/

    @Test
    public void testCreateEmployeeNotNull() throws ApiException {
        // Arrange
        EmployeeDAO employeeDao = new EmployeeDAO();
        Employee employee = new Employee("John Doe", "Mama","employee","john@example.com", 123456789 );

        // Act
        Employee result = employeeDao.create(employee);

        // Assert
        assertNotNull(result, "Employee should not be null");


    }

    @Test
    public void testCreateEmployeeNameNotNull() throws ApiException {
        // Arrange
        String name = "Marco";
        EmployeeDAO employeeDao = new EmployeeDAO();
        Employee employee = new Employee("John", "Mama","employee","john@example.com", 123456789 );

        // Act
        Employee result = employeeDao.create(employee);

        // Assert
        assertNotNull(result.getFirstName(), "Name should not be null");

    }

    @Test
    public void testCreateEmployeeTitleNotNull() throws ApiException {
        // Arrange
        String title = "Software Engine";
        EmployeeDAO employeeDao = new EmployeeDAO();
        Employee employee = new Employee("John", "Mama","employee","john@example.com", 123456789 );

        // Act
        Employee result = employeeDao.create(employee);

        // Assert
        assertNotNull(result.getTitle(), "Title should not be null");
    }
}