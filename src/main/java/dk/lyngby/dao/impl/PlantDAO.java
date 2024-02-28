package dk.lyngby.dao.impl;

import dk.lyngby.dao.IPlantDAO;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.dto.ResellerDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Plant;
import dk.lyngby.model.Reseller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PlantDAO implements IPlantDAO {

    private static PlantDAO instance;
    private static EntityManagerFactory emf;

    public static IPlantDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new PlantDAO();
        }
        return instance;
    }
    private PlantDAO(){}


    @Override
    public PlantDTO read(Integer id) throws ApiException {
        try(EntityManager em = emf.createEntityManager()){
            Plant plant = em.find(Plant.class, id);
            if(plant == null){
                throw new ApiException(404, "Plant not found.");
            }
            return plant.toDto();
        }
    }

    @Override
    public List<PlantDTO> readAll() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT p FROM Plant p", Plant.class);
            return PlantDTO.toPlantDTOList(query.getResultList());
        }
    }

    @Override
    public PlantDTO create(PlantDTO plantDTO) throws ApiException {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant p = new Plant(plantDTO);
            Reseller reseller = em.find(Reseller.class, plantDTO.getResellerId());
            if(reseller == null){
                throw new ApiException(404, "reseller with id: "+plantDTO.getResellerId()+" not found.");
            }
            p.setReseller(reseller);
            em.persist(p);
            em.getTransaction().commit();
            return p.toDto();
        }
    }

    @Override
    public PlantDTO update(Integer id, PlantDTO plantDTO) throws ApiException {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            var p = em.find(Plant.class, id);
            p.setName(plantDTO.getName());
            p.setType(plantDTO.getPlantType());
            p.setMaxHeight(plantDTO.getMaxHeight());
            p.setPrice(plantDTO.getPrice());
            Plant merge = em.merge(p);
            em.getTransaction().commit();
            return merge.toDto();
        }
    }

    @Override
    public void delete(Integer id) throws ApiException {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            var plant = em.find(Plant.class, id);
            if(plant == null){
                throw new ApiException(404, "Plant not found.");
            }
            em.remove(plant);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<PlantDTO> readByPlantType(String type) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT p FROM Plant p WHERE p.type = :type", Plant.class);
            query.setParameter("type", type);
            return PlantDTO.toPlantDTOList(query.getResultList());
        }
    }

    @Override
    public List<PlantDTO> readByHeightRange(int lowerBound, int upperBound) throws ApiException {
        return null;
    }

    @Override
    public List<String> readPlantNames() throws ApiException {
        return null;
    }

    @Override
    public List<PlantDTO> readAndSortByNames() throws ApiException {
        return null;
    }

    @Override
    public ResellerDTO addPlantToReseller(int resellerId, int plantId) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Reseller reseller = em.find(Reseller.class, resellerId);
            if(reseller == null){
                throw new ApiException(404, "Reseller with id: " + resellerId + " couldn't be found.");
            }
            Plant plant = em.find(Plant.class, plantId);
            if(plant == null){
                throw new ApiException(404, "Plant with id: " + plantId + " couldn't be found.");
            }
            reseller.addPlant(plant);
            Reseller r = em.merge(reseller);
            em.getTransaction().commit();
            return r.toDto();
        }
    }

    @Override
    public List<PlantDTO> getPlantsByReseller(int resellerId) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            var query = em.createQuery("SELECT p FROM Plant p WHERE p.reseller.resellerId = :resellerId", Plant.class);
            query.setParameter("resellerId", resellerId);
            return PlantDTO.toPlantDTOList(query.getResultList());
        }
    }
}
