package datatypesutility.controller;

import datatypesutility.model.Model;
import datatypesutility.view.View;

import java.util.LinkedList;

public class UtilityController implements Controller {
    private String[] inputArgs;
    private final View view;
    private final Model model;

    public UtilityController(Model model, View view, String[] inputArgs){
        this.model = model;
        this.view = view;
        this.inputArgs = inputArgs;
        view.setController(this);
    }

    public void setInputArgs(String[] inputArgs){
        this.inputArgs = inputArgs;
    }

    public String[] getInputArgs(){
        return inputArgs;
    }

    public View getView(){
        return view;
    }

    public Model getModel(){
        return model;
    }

    public boolean isInputArgsChecked(){
        return isFileFound() && isFileParametersFound() && isPrefixAfterPFound() && isPathAfterOFound();
    }

    private boolean isFileFound(){
        return true;
    }

    private boolean isFileParametersFound(){
        LinkedList<String> inputFilesNames = new LinkedList<>();

        for (String arg : inputArgs) {
            if (arg.toLowerCase().contains(".txt")) {
                inputFilesNames.add(arg);
            }
        }

        if(inputFilesNames.isEmpty()){ // TODO: Пересмотреть как ошибку
            view.printMessage("Внимание: Вы не указали ни одного имени исходного файла в формате .txt");

            return false;
        }

        model.setInputFilesNames(inputFilesNames);

        return true;
    }

    private boolean isPrefixAfterPFound(){ // TODO: Отработать ситуацию, когда i+1 превышает length
        // TODO: Пересмотреть алгоритмы проверки
        for(int i = 0; i < inputArgs.length; i++){
            if (inputArgs[i + 1].charAt(0) == '-') {
                view.printMessage("Внимание: Вы не указали префикс названия файла после команды -p. Файлы сохранены с именем по умолчанию\n");
            } else if (inputArgs[i + 1].toLowerCase().contains(".txt")) {
                view.printMessage("Внимание: Вы указали название файла вместо префикса. Файлы сохранены с именем по умолчанию\n");
            } else if (inputArgs[i + 1].toLowerCase().contains(".") || inputArgs[i + 1].toLowerCase().contains("*") || inputArgs[i + 1].toLowerCase().contains("/")
                    || inputArgs[i + 1].toLowerCase().contains("?") || inputArgs[i + 1].toLowerCase().contains(":") || inputArgs[i + 1].toLowerCase().contains("|")
                    || inputArgs[i + 1].toLowerCase().contains("\"") || inputArgs[i + 1].toLowerCase().contains("<") || inputArgs[i + 1].toLowerCase().contains(">")) {
                view.printMessage("Внимание: Вы указали префикс названия файла с использованием спецсимвола. Файлы сохранены с именем по умолчанию\n");
            } else {
                model.setIntegerFilename(model.getIntegerFileName() + inputArgs[i + 1]);
                model.setDoubleFileName(model.getDoubleFileName() + inputArgs[i + 1]);
                model.setStringFileName(model.getStringFileName() + inputArgs[i + 1]);
            }
        }

        return true;
    }

    private boolean isPathAfterOFound(){ // TODO: Отработать ситуацию, когда i+1 превышает length
        // TODO: Пересмотреть алгоритмы проверки
        String outputPath;

        for(int i = 0; i < inputArgs.length; i++){
            if (inputArgs[i].equals("-o") || inputArgs[i].equals("-O")) {
                outputPath = inputArgs[i + 1];

                if (outputPath.charAt(0) != '-') {
                    if (outputPath.charAt(0) == '/') {
                        StringBuilder stringBuilder = new StringBuilder(outputPath);
                        stringBuilder.deleteCharAt(0);
                        outputPath = stringBuilder.toString();
                    }

                    if (outputPath.charAt(outputPath.length() - 1) != '/') {
                        outputPath += '/';
                    }
                } else {
                    view.printMessage("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.");
                }

                model.setOutputPath(outputPath);

                return true;
            }
        }

        return false;
    }

    public boolean isModelWorkResult(){
        return isInputArgsChecked() && model.startFilesSort();
    }
}
