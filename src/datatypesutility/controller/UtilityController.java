package datatypesutility.controller;

import datatypesutility.controller.messages.StatisticMessages;
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

        // if (!setStatisticParameter()) {
        //    return false;
        // }

        return true;
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
        // TODO: Пересмотреть алгоритмы проверки
        // TODO: file1.txt file2.txt -o -p file Плохо работает
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

        return false;
    }

    private boolean createDirectory(String path){


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

        // TODO: Уточнить что выдается если не задан ключ статистики.
        /*if(model.getStatisticsCode() == 0){
            view.printMessage("Вы не указали параметр статистики. По умолчанию задан параметр -s.");
            model.setStatisticsCode(1);
        }*/
    }

    // TODO: проверка наличия фалов в которые идет запись при наличии опции
    private void setOptionA() {
        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i].equals("-a") || inputArgs[i].equals("-A")) {
                model.setHasOptionA(true);
            }
        }
    }

    public boolean isModelWorkResult() {
        try {
            if (!isInputArgsChecked()) {
                return false;
            }

            model.startFilesSort();

            if (model.getStatisticsCode() != 0) {
                printStatistics();
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            view.printMessage("Ошибка."); // TODO: Переделать
        }

        return false;
    }

    private void printStatistics() {
        LinkedList<Number> statisticList = model.getStatistic();

        // TODO: сделать рефакторинг - вынести первое сообщение статистики оно применяется везде где есть статистика

        if (model.getHasIntegersFile()) {
            if (model.getStatisticsCode() == 1) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                        + model.getIntegerFileName() + " : " + statisticList.getFirst() + ".\n");
            }

            if (model.getStatisticsCode() == 2) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getIntegerFileName()
                        + " : " + statisticList.getFirst() + ".");
                view.printMessage("Сумма записанных элементов в файл " + model.getIntegerFileName()
                        + " : " + statisticList.get(1) + ".");
                view.printMessage("Среднее значение записанных элементов в файл " + model.getIntegerFileName()
                        + " : " + statisticList.get(2) + ".");
                view.printMessage("Минимальное записанное значение элемента в файл " + model.getIntegerFileName()
                        + " : " + statisticList.get(3) + ".");
                view.printMessage("Максимальное записанное значение элемента в файл " + model.getIntegerFileName()
                        + " : " + statisticList.get(4) + ".\n");
            }
        }

        if (model.getHasDoublesFile()) {
            if (model.getStatisticsCode() == 1) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getDoubleFileName()
                        + " : " + statisticList.get(5) + ".\n");
            }

            if (model.getStatisticsCode() == 2) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getDoubleFileName()
                        + " : " + statisticList.get(5) + ".");
                view.printMessage("Сумма записанных элементов в файл " + model.getDoubleFileName()
                        + " : " + statisticList.get(6) + ".");
                view.printMessage("Среднее значение записанных элементов в файл " + model.getDoubleFileName()
                        + " : " + statisticList.get(7) + ".");
                view.printMessage("Минимальное записанное значение элемента в файл " + model.getDoubleFileName()
                        + " : " + statisticList.get(8) + ".");
                view.printMessage("Максимальное записанное значение элемента в файл " + model.getDoubleFileName()
                        + " : " + statisticList.get(9) + ".\n");
            }
        }

        if (model.getHasStringsFile()) {
            if (model.getStatisticsCode() == 1) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getStringFileName()
                        + " : " + statisticList.get(10) + ".\n");
            }

            if (model.getStatisticsCode() == 2) {
                view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getStringFileName()
                        + " : " + statisticList.get(10) + ".");
                view.printMessage("Длина минимальной строки записанной в файл " + model.getStringFileName()
                        + " : " + statisticList.get(11) + ".");
                view.printMessage("Длина максимальной строки записанной в файл " + model.getStringFileName()
                        + " : " + statisticList.get(12) + ".\n");
            }
        }
    }
}