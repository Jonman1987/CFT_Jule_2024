package datatypesutility;

import datatypesutility.controller.UtilityController;
import datatypesutility.model.Model;
import datatypesutility.model.UtilityModel;
import datatypesutility.view.UtilityView;
import datatypesutility.view.View;

public class DataTypesUtilityMain {
    public static void main(String[] args) {
        Model model = new UtilityModel();
        View view = new UtilityView();

        new UtilityController(model, view, args);

        view.runApplication();
    }
}