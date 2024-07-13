package datatypesutility.controller;

import datatypesutility.model.Model;
import datatypesutility.view.View;

public class UtilityController {
    private String[] inputArgs;
    private View view;
    private Model model;

    public void setInputArgs(String[] inputArgs){
        this.inputArgs = inputArgs;
    }

    public String[] getInputArgs(){
        return inputArgs;
    }

    public void setView(View view){
        this.view = view;
    }

    public View getView(){
        return view;
    }

    public void setModel(Model model){
        this.model = model;
    }

    public Model getModel(){
        return model;
    }

    public boolean isInputArgsChecked(){
        return true;
    }

    public boolean isModelWorkResult(){
        return true;
    }
}
