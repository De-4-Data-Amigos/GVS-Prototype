package dk.lyngby.model;

import dk.lyngby.dto.PlantDTO;
import dk.lyngby.dto.ResellerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reseller_id")
    private int resellerId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(name ="phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "reseller", cascade = CascadeType.ALL)
    private Set<Plant> plants;

    public Reseller(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Reseller(ResellerDTO dto){
        //this.resellerId = dto.getResellerId();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.phoneNumber = dto.getPhoneNumber();
        plants = new HashSet<>();
        for (PlantDTO pdto : dto.getPlants()) {
            addPlant(new Plant(pdto));
        }
    }

    public ResellerDTO toDto(){
        return new ResellerDTO(this);
    }

    public void addPlant(Plant plant) {
        if(plant != null){
            plant.setReseller(this);
            plants.add(plant);
        }
    }
}
