package datatypesutility.controller;

import datatypesutility.controller.services.PrintStatistic;
import datatypesutility.messages.StatisticMessages;
import datatypesutility.model.Model;
import datatypesutility.view.View;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

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

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public boolean isInputArgsChecked() { // TODO: переименовать в Initialization
        if (!isFileParametersFound()) {
            return false;
        }

        if (!isFileFound()) {
            return false;
        }

        isPrefixAfterPFound();

        if (!isPathAfterOFound()) {
            return false;
        }

        setOptionA();

        return setStatisticParameter();
    }

    private boolean isFileFound() { // TODO: Совместить с isFileParametersFound()
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

    private boolean isFileParametersFound() {
        LinkedList<String> inputFilesNames = new LinkedList<>();

        for (String arg : inputArgs) {
            if (arg.toLowerCase().contains(".txt")) {
                inputFilesNames.add(arg);
            }
        }

        if (inputFilesNames.isEmpty()) { // TODO: Пересмотреть как ошибку
            view.printMessage("Внимание: Вы не указали ни одного имени исходного файла в формате .txt");

            return false;
        }

        model.setInputFilesNames(inputFilesNames);

        return true;
    }

    private void isPrefixAfterPFound() { // TODO: Отработать ситуацию, когда i+1 превышает length
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
                model.setHasOptionP(true);
            }
        }
    }

    private boolean isPathAfterOFound() { // TODO: Отработать ситуацию, когда i+1 превышает length
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
                model.setHasOptionO(true);

                return true;
            }
        }

        return true;
    }

    private boolean setStatisticParameter() {
        for (int i = 0; i < inputArgs.length; i++) { // TODO: Возможно нужен рефакторинг, так как есть повтор сообщения
            if (inputArgs[i].equals("-f") || inputArgs[i].equals("-F")) {
                if (model.getStatisticsCode() == 0) {
                    model.setStatisticsCode(2);
                } else {
                    view.printMessage("Ошибка: Вы указали конфликтующие друг с другом параметры статистики."); // TODO: Оформить как ошибку

                    return false;
                }
            } else if (inputArgs[i].equals("-s") || inputArgs[i].equals("-S")) {
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

    private void setOptionA() {
        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals("-a") || inputArgs[i].equals("-A")) {
                model.setHasOptionA(true);
            }
        }
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