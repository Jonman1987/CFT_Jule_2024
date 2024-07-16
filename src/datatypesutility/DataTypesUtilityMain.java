package datatypesutility;

import datatypesutility.controller.UtilityController;
import datatypesutility.model.Model;
import datatypesutility.model.UtilityModel;
import datatypesutility.view.UtilityView;
import datatypesutility.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class DataTypesUtilityMain {
    public static void main(String[] args) throws FileNotFoundException {
       // Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(new FileInputStream("file1.txt")); // TODO: Определиться Scanner или BufferReader
        scanner.useLocale(Locale.US);
        Model model = new UtilityModel();
        View view = new UtilityView();

        new UtilityController(model, view, args, scanner);

        view.runApplication();
    }
}