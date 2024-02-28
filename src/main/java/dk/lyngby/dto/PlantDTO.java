package dk.lyngby.dto;

import dk.lyngby.model.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantDTO {
    private int plantId;
    private String plantType;
    private String name;
    private int maxHeight;
    private float price;
    private int resellerId;

    public PlantDTO(String plantType, String name, int maxHeight, float price) {
        this.plantType = plantType;
        this.name = name;
        this.maxHeight = maxHeight;
        this.price = price;
    }
    public PlantDTO(Plant plant){
        this.plantId = plant.getPlantId();
        this.plantType = plant.getType();
        this.name = plant.getName();
        this.maxHeight = plant.getMaxHeight();
        this.price = plant.getPrice();
        this.resellerId = plant.getReseller().getResellerId();
    }
    public static Set<PlantDTO> toPlantDTOList(Set<Plant> plants) {
        return plants.stream().map(PlantDTO::new).collect(Collectors.toSet());
    }

    public static List<PlantDTO> toPlantDTOList(List<Plant> plants) {
        return plants.stream().map(PlantDTO::new).collect(Collectors.toList());
    }
}
