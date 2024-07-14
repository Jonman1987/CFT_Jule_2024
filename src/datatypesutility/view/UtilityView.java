package datatypesutility.view;

import datatypesutility.controller.Controller;

public class UtilityView implements View {
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void runApplication() {
        controller.isInputArgsChecked();
    }
}
