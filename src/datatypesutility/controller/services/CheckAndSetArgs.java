package datatypesutility.controller.services;

import datatypesutility.strings.ArgumentsKeys;
import datatypesutility.strings.ExceptionMessages;
import datatypesutility.strings.SpecialCharacters;
import datatypesutility.model.Model;
import datatypesutility.strings.WarningMessages;
import datatypesutility.view.View;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class CheckAndSetArgs {
    public boolean isFileParametersFound(String[] inputArgs, Model model, View view) {
        LinkedList<String> inputFilesNames = new LinkedList<>();

        for (String arg : inputArgs) {
            if (!arg.toLowerCase().contains(".txt")) {
                break;
            }

            if (arg.charAt(0) == '.') {
                view.printMessage(ExceptionMessages.getIsFileParametersFoundMessageTwo());

                return false;
            }

            inputFilesNames.add(arg);
        }

        if (inputFilesNames.isEmpty()) {
            view.printMessage(ExceptionMessages.getIsFileParametersFoundMessage());

            return false;
        }

        model.setInputFilesNames(inputFilesNames);

        return true;
    }

    public boolean isFileFound(Model model, View view) {
        LinkedList<String> filesNamesList = model.getInputFilesNames();
        File[] filesArray = new File[filesNamesList.size()];

        for (int i = 0; i < filesArray.length; i++) {
            filesArray[i] = new File(filesNamesList.get(i));

            if (!filesArray[i].exists() && !filesArray[i].isDirectory()) {
                view.printMessage(ExceptionMessages.getIsFileFoundMessageOne()
                        + filesNamesList.get(i) + ExceptionMessages.getIsFileFoundMessageTwo());

                return false;
            }
        }

        return true;
    }

    public void isPrefixAfterPFound(String[] inputArgs, View view, Model model) {
        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals(ArgumentsKeys.prefixKeyToLowerCase())
                    || inputArgs[i].equals(ArgumentsKeys.prefixKeyToUpperCase())) {
                if (i == (inputArgs.length - 1)) {
                    view.printMessage(WarningMessages.getIsPrefixAfterPFoundMessageOne());
                    return;
                }

                if (inputArgs[i + 1].charAt(0) == '-') {
                    view.printMessage(WarningMessages.getIsPrefixAfterPFoundMessageOne());
                    return;
                }

                if (inputArgs[i + 1].toLowerCase().contains(".txt")) {
                    view.printMessage(WarningMessages.getIsPrefixAfterPFoundMessageTwo());
                    return;
                }

                if (inputArgs[i + 1].contains(SpecialCharacters.asterisk())
                        || inputArgs[i + 1].contains(SpecialCharacters.forwardSlash())
                        || inputArgs[i + 1].contains(SpecialCharacters.backwardSlash())
                        || inputArgs[i + 1].contains(SpecialCharacters.questionMark())
                        || inputArgs[i + 1].contains(SpecialCharacters.colon())
                        || inputArgs[i + 1].contains(SpecialCharacters.pipe())
                        || inputArgs[i + 1].contains(SpecialCharacters.quotationMark())
                        || inputArgs[i + 1].contains(SpecialCharacters.leftAngleBracket())
                        || inputArgs[i + 1].contains(SpecialCharacters.rightAngleBracket())) {
                    view.printMessage(WarningMessages.getIsPrefixAfterPFoundMessageThree());
                    return;
                }

                model.setFilesPrefix(inputArgs[i + 1]);
            }
        }
    }

    public void controlPathAfterO(String[] inputArgs, View view, Model model) {
        String outputPath = "";

        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals(ArgumentsKeys.outputPathKeyToLowerCase())
                    || inputArgs[i].equals(ArgumentsKeys.outputPathKeyToUpperCase())) {
                if (i == (inputArgs.length - 1)) {
                    view.printMessage(WarningMessages.getControlPathAfterOMessageOne());
                    model.setOutputPath(outputPath);
                    return;
                }

                outputPath = inputArgs[i + 1];

                if (outputPath.charAt(0) != '-') {
                    if (outputPath.contains(SpecialCharacters.asterisk())
                            || outputPath.contains(SpecialCharacters.questionMark())
                            || outputPath.contains(SpecialCharacters.colon())
                            || outputPath.contains(SpecialCharacters.pipe())
                            || outputPath.contains(SpecialCharacters.quotationMark())
                            || outputPath.contains(SpecialCharacters.leftAngleBracket())
                            || outputPath.contains(SpecialCharacters.rightAngleBracket())) {
                        view.printMessage(WarningMessages.getControlPathAfterOMessageTwo());
                        model.setOutputPath("");
                        return;
                    }

                    if (outputPath.charAt(outputPath.length() - 1) != '/') {
                        outputPath += '/';
                    }

                    if (!Files.exists(Path.of(outputPath))) {
                        new File(outputPath).mkdirs();
                    }
                } else {
                    view.printMessage(WarningMessages.getControlPathAfterOMessageOne());
                    return;
                }

                model.setOutputPath(outputPath);
            }
        }
    }

    public boolean setStatisticParameter(String[] inputArgs, View view, Model model) {
        for (String inputArg : inputArgs) {
            int shortStatisticCode = 1;
            int fullStatisticCode = 2;

            if (inputArg.equals(ArgumentsKeys.fullStatisticKeyToLowerCase())
                    || inputArg.equals(ArgumentsKeys.fullStatisticKeyToUpperCase())) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(fullStatisticCode);
                } else {
                    view.printMessage(ExceptionMessages.getSetStatisticMessage());

                    return false;
                }
            } else if (inputArg.equals(ArgumentsKeys.shortStatisticKeyToLowerCase())
                    || inputArg.equals(ArgumentsKeys.shortStatisticKeyToUpperCase())) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(shortStatisticCode);
                } else {
                    view.printMessage(ExceptionMessages.getSetStatisticMessage());

                    return false;
                }
            }
        }

        return true;
    }

    public void setOptionA(String[] inputArgs, Model model) {
        for (String inputArg : inputArgs) {
            if (inputArg.equals(ArgumentsKeys.overwritingKeyToLowerCase())
                    || inputArg.equals(ArgumentsKeys.overwritingKeyToUpperCase())) {
                model.setHasOptionA(true);
            }
        }
    }
}