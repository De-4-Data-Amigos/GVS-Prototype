package dk.lyngby.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.lyngby.model.Employee;

import java.util.List;
import java.util.stream.Collectors;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private int phone;

    public EmployeeDTO(String firstName, String lastName, String title, String email, int phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.phone = phone;
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.title = employee.getTitle();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
    }

    public static List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
        System.out.println("Number of employees before mapping: " + employees.size());
        List<EmployeeDTO> dtos = employees.stream().map(EmployeeDTO::new).collect(Collectors.toList());
        System.out.println("Number of DTOs after mapping: " + dtos.size());
        dtos.forEach(dto -> System.out.println("DTO: " + dto.toString()));
        return dtos;
    }
}
