package datatypesutility.model.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UtilityWriter {
    private boolean integerAppendStatus;
    private boolean doubleAppendStatus;
    private boolean stringAppendStatus;

    private final UtilityStatistics utilityStatistics;

    public UtilityWriter(boolean appendsStatus) {
        integerAppendStatus = appendsStatus;
        doubleAppendStatus = appendsStatus;
        stringAppendStatus = appendsStatus;

        utilityStatistics = new UtilityStatistics();
    }

    // TODO: Сделать преобразование Big типов из строки в этом классе

    public void writeLine(String outputFileName, BigInteger data) throws IOException {
        /*try (FileWriter fileWriter = new FileWriter(outputFileName, integerAppendStatus)) {
            if (!integerAppendStatus) {
                integerAppendStatus = true;
            }

            fileWriter.write(String.valueOf(data));
            fileWriter.write("\n");

            utilityStatistics.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }*/
    }

    public void writeLine(String outputFileName, BigDecimal data) throws IOException {
        // write(2, outputFileName, stringAppendStatus, data);
        /*try (FileWriter fileWriter = new FileWriter(outputFileName, doubleAppendStatus)) {
            if (!doubleAppendStatus) {
                doubleAppendStatus = true;
            }

            fileWriter.write(String.valueOf(data));
            fileWriter.write("\n");

            utilityStatistics.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }*/
    }

    public void writeLine(String outputFileName, String data) throws IOException {
        write(3, outputFileName, stringAppendStatus, data);
        /*try (FileWriter fileWriter = new FileWriter(outputFileName, stringAppendStatus)) {
            if (!stringAppendStatus) {
                stringAppendStatus = true;
            }

            fileWriter.write(data);
            fileWriter.write("\n");

            utilityStatistics.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }*/
    }

    private void write(int fileType, String outputFileName, boolean appendStatus, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputFileName, appendStatus)) {
            if (!appendStatus) {
                appendStatus = true;
            }

            if(fileType == 1){
                fileWriter.write(String.valueOf(data));
                fileWriter.write("\n");
            }else if(fileType == 2){
                fileWriter.write(String.valueOf(data));
                fileWriter.write("\n");
            }else {
                fileWriter.write(data);
                fileWriter.write("\n");
            }

            utilityStatistics.addStatistic(data);
        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }
    }
}
