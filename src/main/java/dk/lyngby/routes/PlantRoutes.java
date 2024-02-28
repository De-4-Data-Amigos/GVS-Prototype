package dk.lyngby.routes;

import dk.lyngby.controller.impl.PlantController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class PlantRoutes {
    private final PlantController controller = new PlantController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/plants", () -> {
                get("/", controller::readAll);
                get("/type/{type}", controller::readByPlantType);
                get("/{id}", controller::read);
                post("/", controller::create);
            });
        };
    }
}
