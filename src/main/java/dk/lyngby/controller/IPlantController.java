package dk.lyngby.controller;

import dk.lyngby.exception.ApiException;
import io.javalin.http.Context;

public interface IPlantController extends IController {
    void readByPlantType(Context ctx) throws ApiException;
}
