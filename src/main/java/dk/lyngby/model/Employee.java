package dk.lyngby.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class Employee {

    private String name;
    private String title;
    private String email;
    private String phone;
    private String password;
    private String role;

    public Employee(String name, String title, String email, String phone, String password, String role) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }



}
