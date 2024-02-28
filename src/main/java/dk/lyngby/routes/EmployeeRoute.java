package dk.lyngby.routes;

import dk.lyngby.controller.impl.EmployeeController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class EmployeeRoute {

    private final EmployeeController employeeController = new EmployeeController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/employee", () -> {
                post("/", employeeController::create);
                get("/", employeeController::readAll);
                get("/{id}", employeeController::read);
                put("/{id}", employeeController::update);
                delete("/{id}", employeeController::delete);
            });
        };
    }
}
