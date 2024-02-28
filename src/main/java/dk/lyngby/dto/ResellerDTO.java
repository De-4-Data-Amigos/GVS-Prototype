package dk.lyngby.dto;

import dk.lyngby.model.Reseller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResellerDTO {
    private int resellerId;
    private String name;
    private String address;
    private String phoneNumber;
    private Set<PlantDTO> plants = new HashSet();

    public ResellerDTO(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public ResellerDTO(Reseller reseller) {
        this.resellerId = reseller.getResellerId();
        this.name = reseller.getName();
        this.address = reseller.getAddress();
        this.phoneNumber = reseller.getPhoneNumber();
        this.plants = PlantDTO.toPlantDTOList(reseller.getPlants());
    }

    public void addPlant(PlantDTO plantDTO){
        if(plantDTO != null){
            plantDTO.setResellerId(resellerId);
            plants.add(plantDTO);
        }
    }

    public static List<ResellerDTO> toResellerDTOList(List<Reseller> resellers) {
        return resellers.stream().map(ResellerDTO::new).collect(Collectors.toList());
    }
}
