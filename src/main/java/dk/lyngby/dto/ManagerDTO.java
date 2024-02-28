package dk.lyngby.dto;

import dk.lyngby.model.Manager;

import java.util.List;

public class ManagerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;


    public ManagerDTO(String firstName, String lastName, String email, int phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public ManagerDTO(Manager manager) {
        this.id = manager.getManagerId();
        this.firstName = manager.getFirstName();
        this.lastName = manager.getLastName();
        this.email = manager.getEmail();
        this.phone = manager.getPhone();
    }

    public static List<ManagerDTO> toManagerDTOList(List<Manager> managers) {
        return managers.stream().map(ManagerDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
