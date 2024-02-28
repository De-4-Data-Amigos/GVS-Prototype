package dk.lyngby.controller.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.IPlantController;
import dk.lyngby.dao.IPlantDAO;
import dk.lyngby.dao.impl.PlantDAO;
import dk.lyngby.dao.impl.PlantDAOMock;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.exception.ApiException;
import io.javalin.http.Context;

public class PlantController implements IPlantController {

    private final IPlantDAO dao;

    public PlantController(){
        var emf = HibernateConfig.getEntityManagerFactory();
        dao = PlantDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        ctx.json(dao.read(id));
    }

    @Override
    public void readAll(Context ctx) throws ApiException {
        ctx.json(dao.readAll());
    }

    @Override
    public void create(Context ctx) throws ApiException {
        PlantDTO jsonRequest = ctx.bodyAsClass(PlantDTO.class);
        PlantDTO plantDTO = dao.create(jsonRequest);
        ctx.status(201).json(plantDTO);
    }

    @Override
    public void update(Context ctx) throws ApiException {
        throw new ApiException(500, "NIY");
    }

    @Override
    public void delete(Context ctx) throws ApiException {
        throw new ApiException(500, "NIY");
    }

    @Override
    public void readByPlantType(Context ctx) throws ApiException {
        String type = ctx.pathParamAsClass("type", String.class).get();
        ctx.json(dao.readByPlantType(type));
    }
}
