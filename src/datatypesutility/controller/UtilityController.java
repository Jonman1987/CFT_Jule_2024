package datatypesutility.controller;

import datatypesutility.model.Model;
import datatypesutility.view.View;

import java.util.Scanner;

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
        return true;
    }

    private boolean isPrefixAfterPFound(){
        return true;
    }

    private boolean isPathAfterOFound(){
        String outputPath;

        for(int i = 0; i < inputArgs.length; i++){
            if (inputArgs[i].equals("-o") || inputArgs[i].equals("-O")) {
                outputPath = inputArgs[i + 1];

                if (outputPath.charAt(0) != '-') {
                    if (outputPath.charAt(0) == '/') {
                        StringBuilder sb = new StringBuilder(outputPath);
                        sb.deleteCharAt(0);
                        outputPath = sb.toString();
                    }

                    if (outputPath.charAt(outputPath.length() - 1) != '/') {
                        outputPath = outputPath + '/';
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
