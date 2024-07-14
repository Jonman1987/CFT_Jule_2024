package datatypesutility;

import datatypesutility.controller.UtilityController;
import datatypesutility.model.Model;
import datatypesutility.model.UtilityModel;
import datatypesutility.view.UtilityView;
import datatypesutility.view.View;

import java.util.Scanner;

public class DataTypesUtilityMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Model model = new UtilityModel();
        View view = new UtilityView();

        new UtilityController(model, view, args);

        view.runApplication();
    }
}