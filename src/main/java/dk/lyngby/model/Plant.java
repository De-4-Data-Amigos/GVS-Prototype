package dk.lyngby.model;

import dk.lyngby.dto.PlantDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private int plantId;

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String name;
    @Column(name = "max_height", nullable = false)
    private int maxHeight;
    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "reseller_id", nullable = false)
    private Reseller reseller;

    public Plant(String type, String name, int maxHeight, float price) {
        this.type = type;
        this.name = name;
        this.maxHeight = maxHeight;
        this.price = price;
    }

    public Plant(PlantDTO dto){
        //this.plantId = dto.getPlantId();
        this.type = dto.getPlantType();
        this.name = dto.getName();
        this.maxHeight = dto.getMaxHeight();
        this.price = dto.getPrice();
    }

    public PlantDTO toDto(){
        return new PlantDTO(this);
    }
}
