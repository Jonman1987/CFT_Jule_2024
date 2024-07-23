package datatypesutility.controller;

import datatypesutility.controller.services.CheckAndSetArgs;
import datatypesutility.controller.services.PrintStatistic;
import datatypesutility.model.Model;
import datatypesutility.view.View;

public class UtilityController implements Controller { // TODO: заменить массивы LinkedList и итератором
    private String[] inputArgs;
    private final View view;
    private final Model model;

    public UtilityController(Model model, View view, String[] inputArgs) {
        this.model = model;
        this.view = view;
        this.inputArgs = inputArgs;

        view.setController(this);
    }

    public void setInputArgs(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }

    public String[] getInputArgs() {
        return inputArgs;
    }

    public boolean isInputArgsChecked() { // TODO: переименовать в Initialization
        // TODO: Не нравится, что модель передает ресурсы view и model
        CheckAndSetArgs checkArgs = new CheckAndSetArgs();

        if (!checkArgs.isFileParametersFound(inputArgs, model, view)) {
            return false;
        }

        if (!checkArgs.isFileFound(model, view)) {
            return false;
        }

        checkArgs.isPrefixAfterPFound(inputArgs, view, model);

        checkArgs.controlPathAfterO(inputArgs, view, model);

        checkArgs.setOptionA(inputArgs, model);

        return checkArgs.setStatisticParameter(inputArgs, view, model);
    }

    public boolean isModelWorkResult() {
        final int defaultStatisticCode = 0;

        try {
            if (!isInputArgsChecked()) {
                return false;
            }

            model.startFilesSort();

            if (model.getStatisticsCode() != defaultStatisticCode) {
                printStatistics();
            }

            return true;
        } catch (Exception e) {
            view.printMessage(e.getMessage());
            view.printMessage("Внимание! Работа программы принудительно завершена!"); // TODO: Переделать
        }

        return false;
    }

    private void printStatistics() {
        PrintStatistic printStatistic = new PrintStatistic();
        printStatistic.print(view, model);
    }
}