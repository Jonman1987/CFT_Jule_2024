package datatypesutility.model.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UtilityWriter {
    private boolean integerAppendStatus;
    private boolean doubleAppendStatus;
    private boolean stringAppendStatus;

    private final UtilityStatistic utilityStatistic;

    public UtilityWriter(boolean appendsStatus) {
        integerAppendStatus = appendsStatus;
        doubleAppendStatus = appendsStatus;
        stringAppendStatus = appendsStatus;

        utilityStatistic = new UtilityStatistic();
    }

    public void writeLine(String outputFileName, BigInteger data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputFileName, integerAppendStatus)) {
            if (!integerAppendStatus) {
                integerAppendStatus = true;
            }

            fileWriter.write(String.valueOf(data));
            fileWriter.write("\n");

            utilityStatistic.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }
    }

    public void writeLine(String outputFileName, BigDecimal data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputFileName, doubleAppendStatus)) {
            if (!doubleAppendStatus) {
                doubleAppendStatus = true;
            }

            fileWriter.write(String.valueOf(data));
            fileWriter.write("\n");

            utilityStatistic.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }
    }

    public void writeLine(String outputFileName, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputFileName, stringAppendStatus)) {
            if (!stringAppendStatus) {
                stringAppendStatus = true;
            }

            fileWriter.write(data);
            fileWriter.write("\n");

            utilityStatistic.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }
    }
}
