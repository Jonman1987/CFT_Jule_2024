package datatypesutility.controller.services;

import datatypesutility.messages.ArgumentsKeys;
import datatypesutility.messages.ExceptionMessages;
import datatypesutility.messages.SpecialCharacters;
import datatypesutility.model.Model;
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
                view.printMessage("Ошибка: В имени входного файла указано только расширение");

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
                view.printMessage("Ошибка. Файл " + filesNamesList.get(i) + " не найден!");

                return false;
            }
        }

        return true;
    }

    public void isPrefixAfterPFound(String[] inputArgs, View view, Model model) {
        // TODO: Пересмотреть алгоритмы проверки
        // TODO: Названия не очень
        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals(ArgumentsKeys.prefixKeyToLowerCase())
                    || inputArgs[i].equals(ArgumentsKeys.prefixKeyToUpperCase())) {
                if (i == (inputArgs.length - 1)) {
                    view.printMessage("Внимание: Вы не указали префикс названия файла после команды -p. Файлы сохранены с именем по умолчанию\n"); // TODO: Вывести сообщения в отдельный класс
                    return;
                }

                if (inputArgs[i + 1].charAt(0) == '-') { // TODO: Сделать через или
                    view.printMessage("Внимание: Вы не указали префикс названия файла после команды -p. Файлы сохранены с именем по умолчанию\n");
                    return;
                }

                if (inputArgs[i + 1].toLowerCase().contains(".txt")) {
                    view.printMessage("Внимание: Вы указали название файла вместо префикса. Файлы сохранены с именем по умолчанию\n");
                    return;
                }

                if (inputArgs[i + 1].contains(SpecialCharacters.asterisk())
                        || inputArgs[i + 1].contains(SpecialCharacters.forwardSlash())
                        || inputArgs[i + 1].contains(SpecialCharacters.questionMark())
                        || inputArgs[i + 1].contains(SpecialCharacters.colon())
                        || inputArgs[i + 1].contains(SpecialCharacters.pipe())
                        || inputArgs[i + 1].contains(SpecialCharacters.quotationMark())
                        || inputArgs[i + 1].contains(SpecialCharacters.leftAngleBracket())
                        || inputArgs[i + 1].contains(SpecialCharacters.rightAngleBracket())) {
                    view.printMessage("Внимание: Вы указали префикс названия файла с использованием спецсимвола. Файлы сохранены с именем по умолчанию\n");
                    return;
                }

                model.setFilesPrefix(inputArgs[i + 1]);
            }
        }
    }

    public void controlPathAfterO(String[] inputArgs, View view, Model model) {
        // TODO: Пересмотреть алгоритмы проверки вынести циклы
        String outputPath = "";

        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals(ArgumentsKeys.outputPathKeyToLowerCase())
                    || inputArgs[i].equals(ArgumentsKeys.outputPathKeyToUpperCase())) {
                if (i == (inputArgs.length - 1)) {
                    view.printMessage("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.");
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
                        view.printMessage("Внимание: Вы указали путь для исходящих файлов с использованием спецсимвола. Файлы сохранены в текущую папку.");
                        model.setOutputPath("");
                        return;
                    }

                    // TODO: Подумать о проверке на спецсимволы file1.txt -o file2.txt -p file

                    if (outputPath.charAt(outputPath.length() - 1) != '/') {
                        outputPath += '/';
                    }

                    if (!Files.exists(Path.of(outputPath))) {
                        new File(outputPath).mkdirs();
                    }
                } else {
                    view.printMessage("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.");
                    return;
                }

                model.setOutputPath(outputPath);
            }
        }
    }

    public boolean setStatisticParameter(String[] inputArgs, View view, Model model) {
        for (String inputArg : inputArgs) { // TODO: Возможно нужен рефакторинг, так как есть повтор сообщения
            if (inputArg.equals(ArgumentsKeys.fullStatisticKeyToLowerCase())
                    || inputArg.equals(ArgumentsKeys.fullStatisticKeyToUpperCase())) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(2);
                } else {
                    view.printMessage("Ошибка: Вы указали конфликтующие друг с другом параметры статистики."); // TODO: Оформить как ошибку

                    return false;
                }
            } else if (inputArg.equals(ArgumentsKeys.shortStatisticKeyToLowerCase())
                    || inputArg.equals(ArgumentsKeys.shortStatisticKeyToUpperCase())) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(1);
                } else {
                    view.printMessage("Вы указали конфликтующие друг с другом параметры статистики."); // TODO: Оформить как ошибку

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
