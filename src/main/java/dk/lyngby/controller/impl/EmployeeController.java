package dk.lyngby.controller.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.IController;
import dk.lyngby.dao.impl.EmployeeDAO;
import dk.lyngby.dto.EmployeeDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Employee;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeeController implements IController<Employee, Integer> {

    private final EmployeeDAO employeeDAO;

    public EmployeeController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.employeeDAO = EmployeeDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
            Employee employee = employeeDAO.read(id);
            ctx.json(employee).status(200);
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {
        try {
            List<Employee> employees = employeeDAO.readAll();
            List<EmployeeDTO> employeeDtos = EmployeeDTO.toEmployeeDTOList(employees);
            ctx.json(employeeDtos).status(200);
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            Employee jsonRequest = validateEntity(ctx);
            Employee employee = employeeDAO.create(jsonRequest);
            EmployeeDTO employeeDto = new EmployeeDTO(employee);
            ctx.json(employeeDto).status(201);
        } catch (ApiException e) {
            ctx.status(e.getStatusCode()).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
            Employee update = employeeDAO.update(id, validateEntity(ctx));
            EmployeeDTO employeeDto = new EmployeeDTO(update);
            ctx.json(employeeDto).status(200);
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
            employeeDAO.delete(id);
            ctx.status(204);
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    private Employee validateEntity(Context ctx) throws ApiException {
        try {
            Employee employee = ctx.bodyValidator(Employee.class)
                    .check(e -> !e.getFirstName().isEmpty(), "First name must be provided")
                    .check(e -> !e.getLastName().isEmpty(), "Last name must be provided")
                    .check(e -> !e.getTitle().isEmpty(), "Title must be provided")
                    .check(e -> !e.getEmail().isEmpty(), "Email must be provided")
                    .check(e -> isValidEmail(e.getEmail()), "Invalid email format")
                    .check(e -> e.getPhone() > 0, "Phone number must be provided")
                    .get();
            return employee;
        } catch (ValidationException ex) {
            throw new ApiException(400, "Invalid input data: " + ex.getMessage());
        }
    }

    private Boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean validatePrimaryKey(Integer integer) {
        return employeeDAO.validatePrimaryKey(integer);
    }
}
