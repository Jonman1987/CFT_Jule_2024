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

        if (model.getHasIntegersFile()) { // TODO: Декомпозировать
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + model.getFilesPrefix()
                    + model.getIntegerFileName()
                    + StatisticMessages.getPrintMessageColon()
                    + statisticList.getFirst()
                    + StatisticMessages.getPrintMessageDot());

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticElementsSumMessage()
                        + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(1)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsAverageMessage()
                        + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(2)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsMinMessage()
                        + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(3)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsMaxMessage()
                        + model.getFilesPrefix()
                        + model.getIntegerFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(4)
                        + StatisticMessages.getPrintMessageDot());
            }

            view.printMessage("");
        }

        if (model.getHasDoublesFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + model.getFilesPrefix()
                    + model.getDoubleFileName()
                    + StatisticMessages.getPrintMessageColon()
                    + statisticList.get(5)
                    + StatisticMessages.getPrintMessageDot());

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticElementsSumMessage()
                        + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(6)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsAverageMessage()
                        + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(7)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsMinMessage()
                        + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(8)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticElementsMaxMessage()
                        + model.getFilesPrefix()
                        + model.getDoubleFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(9)
                        + StatisticMessages.getPrintMessageDot());
            }

            view.printMessage("");
        }

        if (model.getHasStringsFile()) {
            view.printMessage(StatisticMessages.getStatisticElementsCountMessage()
                    + model.getFilesPrefix()
                    + model.getStringFileName()
                    + StatisticMessages.getPrintMessageColon()
                    + statisticList.get(10)
                    + StatisticMessages.getPrintMessageDot());

            if (statisticCode == fullStatisticCode) {
                view.printMessage(StatisticMessages.getStatisticStringMinLengthMessage() + model.getFilesPrefix()
                        + model.getStringFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(11)
                        + StatisticMessages.getPrintMessageDot());
                view.printMessage(StatisticMessages.getStatisticStringMaxLengthMessage() + model.getFilesPrefix()
                        + model.getStringFileName()
                        + StatisticMessages.getPrintMessageColon()
                        + statisticList.get(12)
                        + StatisticMessages.getPrintMessageDot());
            }

            view.printMessage("");
        }
    }
}
