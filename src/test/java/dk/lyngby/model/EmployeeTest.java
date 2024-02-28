package dk.lyngby.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class EmployeeTest {

    @Test
    void createEmployeeTest() {
        // Arrange

        String name = "John Doe";
        String title = "Software Engineer";
        String email = "john.doe@example.com";
        String phone = "12345678";
        String password = "password123";
        String role = "Developer";

        // Act
        Employee result = new Employee(name, title, email, phone, password, role);

        // Assert
        assertNotNull(result, "Employee should not be null");
        assertEquals(name, result.getName(), "Names should match");
        assertEquals(title, result.getTitle(), "Titles should match");
        assertEquals(email, result.getEmail(), "Emails should match");
        assertEquals(phone, result.getPhone(), "Phones should match");
        assertEquals(password, result.getPassword(), "Passwords should match");
        assertEquals(role, result.getRole(), "Roles should match");


    }


}