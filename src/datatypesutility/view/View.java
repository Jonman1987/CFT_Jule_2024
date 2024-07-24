package datatypesutility.view;

import datatypesutility.controller.Controller;

public interface View {
    void setController(Controller controller);

    void printMessage(String message);

    void runApplication();
}