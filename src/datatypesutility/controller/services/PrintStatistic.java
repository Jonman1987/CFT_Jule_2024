package datatypesutility.controller.services;

import datatypesutility.strings.StatisticMessages;
import datatypesutility.model.Model;
import datatypesutility.view.View;

import java.util.LinkedList;

public class PrintStatistic {
    public void print(View view, Model model) {
        final int fullStatisticCode = 2;
        final int startIntegerStat = 0;
        final int endIntegerStat = 5;
        final int endDoubleStat = 10;

        LinkedList<Number> statisticList = model.getStatistic();

        int statisticCode = model.getStatisticsCode();

        if (model.getHasIntegersFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + makeMessage(model.getFilesPrefix(), model.getIntegerFileName(), statisticList.removeFirst()));

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticElementsSumMessage()
                        + makeMessage(model.getFilesPrefix(), model.getIntegerFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsAverageMessage()
                        + makeMessage(model.getFilesPrefix(), model.getIntegerFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsMinMessage()
                        + makeMessage(model.getFilesPrefix(), model.getIntegerFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsMaxMessage()
                        + makeMessage(model.getFilesPrefix(), model.getIntegerFileName(), statisticList.removeFirst()));
            } else {
                for (int i = startIntegerStat + 1; i < endIntegerStat; i++) {
                    statisticList.removeFirst();
                }
            }

            view.printMessage("");
        } else {
            for (int i = startIntegerStat; i < endIntegerStat; i++) {
                statisticList.removeFirst();
            }
        }

        if (model.getHasDoublesFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + makeMessage(model.getFilesPrefix(), model.getDoubleFileName(), statisticList.removeFirst()));

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticElementsSumMessage()
                        + makeMessage(model.getFilesPrefix(), model.getDoubleFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsAverageMessage()
                        + makeMessage(model.getFilesPrefix(), model.getDoubleFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsMinMessage()
                        + makeMessage(model.getFilesPrefix(), model.getDoubleFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticElementsMaxMessage()
                        + makeMessage(model.getFilesPrefix(), model.getDoubleFileName(), statisticList.removeFirst()));
            } else {
                for (int i = endIntegerStat + 1; i < endDoubleStat; i++) {
                    statisticList.removeFirst();
                }
            }

            view.printMessage("");
        } else {
            for (int i = endIntegerStat; i < endDoubleStat; i++) {
                statisticList.removeFirst();
            }
        }

        if (model.getHasStringsFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + makeMessage(model.getFilesPrefix(), model.getStringFileName(), statisticList.removeFirst()));

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticStringMinLengthMessage()
                        + makeMessage(model.getFilesPrefix(), model.getStringFileName(), statisticList.removeFirst()));

                view.printMessage(StatisticMessages.getStatisticStringMaxLengthMessage()
                        + makeMessage(model.getFilesPrefix(), model.getStringFileName(), statisticList.removeFirst()));
            }

            view.printMessage("");
        }
    }

    private String makeMessage(String filePrefix, String fileName, Number data) {
        return filePrefix
                + fileName
                + StatisticMessages.getPrintMessageColon()
                + data
                + StatisticMessages.getPrintMessageDot();
    }
}