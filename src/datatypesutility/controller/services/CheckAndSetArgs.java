package datatypesutility.controller.services;

import datatypesutility.messages.ExceptionMessages;
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
            if (arg.toLowerCase().contains(".txt")) { // TODO: Добавить если первый символ точка
                inputFilesNames.add(arg);
            }
        }

        if (inputFilesNames.isEmpty()) {
            view.printMessage(ExceptionMessages.getIsFileParametersFoundMessage());

            return false;
        }

        model.setInputFilesNames(inputFilesNames);

        return true;
    }

    public boolean isFileFound(Model model, View view) { // TODO: Совместить с isFileParametersFound()
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

    public void isPrefixAfterPFound(String[] inputArgs, View view, Model model) { // TODO: Отработать ситуацию, когда i+1 превышает length
        // TODO: Пересмотреть алгоритмы проверки
        // TODO: Названия не очень
        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals("-p") || inputArgs[i].equals("-P")) {
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

                if (inputArgs[i + 1].toLowerCase().contains(".")
                        || inputArgs[i + 1].toLowerCase().contains("*")
                        || inputArgs[i + 1].toLowerCase().contains("/")
                        || inputArgs[i + 1].toLowerCase().contains("?")
                        || inputArgs[i + 1].toLowerCase().contains(":")
                        || inputArgs[i + 1].toLowerCase().contains("|")
                        || inputArgs[i + 1].toLowerCase().contains("\"")
                        || inputArgs[i + 1].toLowerCase().contains("<")
                        || inputArgs[i + 1].toLowerCase().contains(">")) {
                    view.printMessage("Внимание: Вы указали префикс названия файла с использованием спецсимвола. Файлы сохранены с именем по умолчанию\n");
                    return;
                }

                model.setFilesPrefix(inputArgs[i + 1]);
            }
        }
    }

    public boolean isPathAfterOFound(String[] inputArgs, View view, Model model) { // TODO: Отработать ситуацию, когда i+1 превышает length
        // TODO: Пересмотреть алгоритмы проверки вынести циклы
        String outputPath = "";

        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals("-o") || inputArgs[i].equals("-O")) {
                if (i == (inputArgs.length - 1)) {
                    view.printMessage("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.");
                    model.setOutputPath(outputPath);
                    // model.setHasOptionO(true);

                    return true;
                }

                outputPath = inputArgs[i + 1];

                if (outputPath.charAt(0) != '-') {
                    /*if (outputPath.charAt(0) == '/') {
                        StringBuilder stringBuilder = new StringBuilder(outputPath);
                        stringBuilder.deleteCharAt(0);
                        outputPath = stringBuilder.toString();
                    }*/

                    // TODO: Подумать о проверке на спецсимволы file1.txt -o file2.txt -p file

                    if (outputPath.charAt(outputPath.length() - 1) != '/') {
                        outputPath += '/';
                    }

                    if(!Files.exists(Path.of(outputPath))){
                        new File(outputPath).mkdirs();
                    }
                } else {
                    view.printMessage("Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.");

                    return true;
                }

                model.setOutputPath(outputPath);

                return true;
            }
        }

        return true;
    }

    public boolean setStatisticParameter(String[] inputArgs, View view, Model model) {
        for (String inputArg : inputArgs) { // TODO: Возможно нужен рефакторинг, так как есть повтор сообщения
            if (inputArg.equals("-f") || inputArg.equals("-F")) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(2);
                } else {
                    view.printMessage("Ошибка: Вы указали конфликтующие друг с другом параметры статистики."); // TODO: Оформить как ошибку

                    return false;
                }
            } else if (inputArg.equals("-s") || inputArg.equals("-S")) {
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
            if (inputArg.equals("-a") || inputArg.equals("-A")) {
                model.setHasOptionA(true);
            }
        }
    }
}
