package datatypesutility.controller.services;

import datatypesutility.messages.StatisticMessages;
import datatypesutility.model.Model;
import datatypesutility.view.View;

import java.util.LinkedList;

public class PrintStatistic {
    public void print(View view, Model model){
        final int fullStatisticCode = 2;

        LinkedList<Number> statisticList = model.getStatistic();

        int statisticCode = model.getStatisticsCode();

        // TODO: сделать рефакторинг - вынести первое сообщение статистики оно применяется везде где есть статистика
        // TODO: добавить префикс файла

        if (model.getHasIntegersFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + model.getFilesPrefix()
                    + model.getIntegerFileName()
                    + ": " + statisticList.getFirst() + ".");

            if (statisticCode == fullStatisticCode) {
                view.printMessage("Сумма записанных элементов в файл " + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + ": " + statisticList.get(1) + ".");
                view.printMessage("Среднее значение записанных элементов в файл " + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + ": " + statisticList.get(2) + ".");
                view.printMessage("Минимальное записанное значение элемента в файл " + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + ": " + statisticList.get(3) + ".");
                view.printMessage("Максимальное записанное значение элемента в файл " + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + ": " + statisticList.get(4) + ".");
            }

            view.printMessage("");
        }

        if (model.getHasDoublesFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getFilesPrefix()
                    + model.getDoubleFileName()
                    + ": " + statisticList.get(5) + ".");

            if (statisticCode == fullStatisticCode) {
                view.printMessage("Сумма записанных элементов в файл " + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + ": " + statisticList.get(6) + ".");
                view.printMessage("Среднее значение записанных элементов в файл " + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + ": " + statisticList.get(7) + ".");
                view.printMessage("Минимальное записанное значение элемента в файл " + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + ": " + statisticList.get(8) + ".");
                view.printMessage("Максимальное записанное значение элемента в файл " + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + ": " + statisticList.get(9) + ".");
            }

            view.printMessage("");
        }

        if (model.getHasStringsFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage() + model.getFilesPrefix()
                    + model.getStringFileName()
                    + ": " + statisticList.get(10) + ".");

            if (statisticCode == fullStatisticCode) {
                view.printMessage("Длина минимальной строки записанной в файл " + model.getFilesPrefix()
                        + model.getStringFileName()
                        + ": " + statisticList.get(11) + ".");
                view.printMessage("Длина максимальной строки записанной в файл " + model.getFilesPrefix()
                        + model.getStringFileName()
                        + ": " + statisticList.get(12) + ".");
            }

            view.printMessage("");
        }
    }
}
