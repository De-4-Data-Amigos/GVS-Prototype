package dk.lyngby.dao.impl;

import dk.lyngby.dao.IPlantDAO;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.dto.ResellerDTO;
import dk.lyngby.exception.ApiException;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantDAOMock implements IPlantDAO {

    private static List<PlantDTO> mockDB;
    private static PlantDAOMock instance;

    public static IPlantDAO getInstance(EntityManagerFactory emf){
        if(instance == null){
            instance = new PlantDAOMock();
        }
        return instance;
    }
    private PlantDAOMock(){
        mockDB = populateMockDB();
    }

    private List<PlantDTO> populateMockDB() {
        ArrayList<PlantDTO> db = new ArrayList<>();

        //db.add(new PlantDTO(1,   "Rose",             "Albertine",    400,    199.5f));
        //db.add(new PlantDTO(2,   "Bush",             "Aronia",       200,    169.5f));
        //db.add(new PlantDTO(3,   "FruitAndBerries",  "AromaApple",   350,    399.5f));
        //db.add(new PlantDTO(4,   "Rhododendron",     "Astrid",       40,     269.5f));
        //db.add(new PlantDTO(5,   "Rose",             "TheDarkLady",  100,    199.5f));

        return db;
    }
    private PlantDTO addToMockDB(PlantDTO plant){
        int id = mockDB.size()+1;
        plant.setPlantId(id);
        mockDB.add(plant);
        return plant;
    }


    @Override
    public List<PlantDTO> readAll() {
        return mockDB;
    }

    @Override
    public List<PlantDTO> readByPlantType(String type) {
        List<PlantDTO> plants = new ArrayList<>();
        for (PlantDTO plant : mockDB) {
            if(plant.getPlantType().equalsIgnoreCase(type)){
                plants.add(plant);
            }
        }
        return plants;
    }

    @Override
    public PlantDTO read(Integer id) throws ApiException {
        for (PlantDTO plant : mockDB) {
            if(plant.getPlantId() == id){
                return plant;
            }
        }
        throw new ApiException(404, "Plant with id:" + id +" couldn't be found.");
    }
    @Override
    public List<PlantDTO> readByHeightRange(int lowerBound, int upperBound){
        if(lowerBound > upperBound){
            int temp = upperBound;
            upperBound = lowerBound;
            lowerBound = temp;
        }
        int finalUpperBound = upperBound;
        int finalLowerBound = lowerBound;
        return mockDB
                .stream()
                .filter(
                        plantDTO -> plantDTO.getMaxHeight() < finalUpperBound
                                &&  plantDTO.getMaxHeight() > finalLowerBound
                )
                .toList();
    }
    @Override
    public List<String> readPlantNames(){
        return mockDB
                .stream()
                .map(PlantDTO::getName)
                .toList();
    }
    @Override
    public List<PlantDTO> readAndSortByNames(){
        return mockDB
                .stream()
                .sorted(Comparator.comparing(PlantDTO::getName))
                .toList();
    }

    @Override
    public ResellerDTO addPlantToReseller(int resellerId, int plantId) throws ApiException {
        throw new ApiException(500, "NIY");
    }

    @Override
    public List<PlantDTO> getPlantsByReseller(int resellerId) throws ApiException {
        throw new ApiException(500, "NIY");
    }

    @Override
    public PlantDTO create(PlantDTO plant) {
        return addToMockDB(plant);
    }

    @Override
    public PlantDTO update(Integer id, PlantDTO plantDTO) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            PlantDTO plant = mockDB.get(i);
            if (plant.getPlantId() == id) {
                plantDTO.setPlantId(id);
                mockDB.set(i, plantDTO);
                return plantDTO;
            }
        }
        throw new ApiException(404, "Plant with id:" + id +" couldn't be found.");
    }

    @Override
    public void delete(Integer id) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            PlantDTO plant = mockDB.get(i);
            if (plant.getPlantId() == id) {
                mockDB.remove(i);
                return;
            }
        }
        throw new ApiException(404, "Plant with id:" + id +" couldn't be found.");
    }
}
