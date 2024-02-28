package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDao;
import dk.lyngby.dto.ResellerDTO;
import dk.lyngby.exception.ApiException;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResellerDAOMock implements IDao<ResellerDTO, Integer> {
    private static List<ResellerDTO> mockDB;
    private static ResellerDAOMock instance;

    public static IDao getInstance(EntityManagerFactory emf){
        if(instance == null){
            instance = new ResellerDAOMock();
        }
        return instance;
    }
    private ResellerDAOMock(){
        mockDB = populateMockDB();
    }

    private List<ResellerDTO> populateMockDB() {
        ArrayList<ResellerDTO> db = new ArrayList<>();

        db.add(new ResellerDTO(1,   "Lyngby Plantecenter",   "Firskovvej 18",    "+45 33212334", new HashSet<>()));
        db.add(new ResellerDTO(2,   "Glostrup Planter",      "Tværvej 35",       "+45 32233232", new HashSet<>()));
        db.add(new ResellerDTO(3,   "Holbæk Planteskole",    "Stenhusvej 49",    "+45 59430945", new HashSet<>()));

        return db;
    }
    private ResellerDTO addToMockDB(ResellerDTO reseller){
        int id = mockDB.size()+1;
        reseller.setResellerId(id);
        mockDB.add(reseller);
        return reseller;
    }

    @Override
    public ResellerDTO read(Integer id) throws ApiException {
        for (ResellerDTO reseller : mockDB) {
            if(reseller.getResellerId() == id){
                return reseller;
            }
        }
        throw new ApiException(404, "Reseller with id:" + id +" couldn't be found.");
    }

    @Override
    public List<ResellerDTO> readAll() throws ApiException {
        return mockDB;
    }

    @Override
    public ResellerDTO create(ResellerDTO resellerDTO) throws ApiException {
        return addToMockDB(resellerDTO);
    }

    @Override
    public ResellerDTO update(Integer id, ResellerDTO resellerDTO) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            ResellerDTO reseller = mockDB.get(i);
            if (reseller.getResellerId() == id) {
                resellerDTO.setResellerId(id);
                mockDB.set(i, resellerDTO);
                return resellerDTO;
            }
        }
        throw new ApiException(404, "Reseller with id:" + id +" couldn't be found.");
    }

    @Override
    public void delete(Integer id) throws ApiException {
        for (int i = 0; i < mockDB.size(); i++) {
            ResellerDTO reseller = mockDB.get(i);
            if (reseller.getResellerId() == id) {
                mockDB.remove(i);
                return;
            }
        }
        throw new ApiException(404, "Reseller with id:" + id +" couldn't be found.");
    }
}
