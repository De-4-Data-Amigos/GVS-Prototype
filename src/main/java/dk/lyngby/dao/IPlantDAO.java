package dk.lyngby.dao;

import dk.lyngby.dto.PlantDTO;
import dk.lyngby.dto.ResellerDTO;
import dk.lyngby.exception.ApiException;

import java.util.List;

public interface IPlantDAO extends IDao <PlantDTO, Integer>{
    List<PlantDTO> readByPlantType(String type) throws ApiException;

    List<PlantDTO> readByHeightRange(int lowerBound, int upperBound) throws ApiException;

    List<String> readPlantNames() throws ApiException;

    List<PlantDTO> readAndSortByNames() throws ApiException;

    ResellerDTO addPlantToReseller(int resellerId, int plantId) throws ApiException;

    List<PlantDTO> getPlantsByReseller(int resellerId) throws ApiException;
}
