package dk.lyngby.controller.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.IController;
import dk.lyngby.dao.impl.ManagerDAO;
import dk.lyngby.dto.ManagerDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Manager;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ManagerController implements IController<Manager, Integer> {
    private ManagerDAO dao;

    public ManagerController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = ManagerDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        Manager manager = dao.read(id);
        ctx.res().setStatus(200);
        ctx.json(manager);


    }


    @Override
    public void readAll(Context ctx) throws ApiException {
        List<Manager> managers = dao.readAll();
        List<ManagerDTO> managerDtos = ManagerDTO.toManagerDTOList(managers);
        ctx.res().setStatus(200);
        ctx.json(managerDtos, ManagerDTO.class);


    }

    @Override
    public void create(Context ctx) throws ApiException {
        try {
            Manager jsonRequest = validateEntity(ctx);
            Manager manager = dao.create(jsonRequest);
            ManagerDTO managerDto = new ManagerDTO(manager);
            ctx.res().setStatus(201);
            ctx.json(managerDto, ManagerDTO.class);
        } catch (ApiException e) {
            ctx.status(e.getStatusCode()).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        boolean deleted = dao.delete(id);
        if (deleted) {
            ctx.res().setStatus(204);
        } else {
            ctx.status(404).result("Manager not found");
        }
    }

    @Override
    public void update(Context ctx) throws ApiException {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        Manager update = dao.update(id, validateEntity(ctx));
        ManagerDTO managerDto = new ManagerDTO(update);
        ctx.res().setStatus(200);
        ctx.json(managerDto, ManagerDTO.class);
    }

    private Manager validateEntity(Context ctx) {
        return ctx.bodyValidator(Manager.class)
                .check(m -> m.getFirstName() != null, "Id cannot be null")
                .check(m -> m.getLastName() != null, "Name cannot be null")
                .check(m -> m.getEmail() != null, "Email cannot be null")
                .check(m -> m.getPhone() > 0, "Phone cannot be null")
                .get();

    }

    private Boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }

}
