package dk.lyngby.routes;

import dk.lyngby.controller.impl.EmployeeController;
import dk.lyngby.controller.impl.ManagerController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class ManagerRoute {

    private final ManagerController managerController = new ManagerController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/manager", () -> {
                post("/", managerController::create);
                get("/", managerController::readAll);
                get("/{id}", managerController::read);
                put("/{id}", managerController::update);
                delete("/{id}", managerController::delete);
            });
        };
    }
}
