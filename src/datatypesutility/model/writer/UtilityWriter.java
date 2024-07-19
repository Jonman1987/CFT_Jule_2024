package datatypesutility.model.writer;

import java.io.FileWriter;
import java.io.IOException;

public class UtilityWriter{
    private boolean isAppend;

    private final UtilityStatistics utilityStatistics;

    public UtilityWriter(boolean appendsStatus) {
        isAppend = appendsStatus;

        utilityStatistics = new UtilityStatistics();
    }

    public <T> void writeLine(int fileCode, String outputFileName, boolean appendStatus, T data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputFileName, isAppend)) {
            if (!appendStatus) {
                isAppend = true;
            }

            if (fileCode == 1) {
                fileWriter.write(String.valueOf(data));
                fileWriter.write("\n");
            } else if (fileCode == 2) {
                fileWriter.write(String.valueOf(data));
                fileWriter.write("\n");
            } else {
                fileWriter.write(String.valueOf(data));
                fileWriter.write("\n");
            }

            utilityStatistics.addStatistic(fileCode, data);

        } catch (IOException e) {
            throw new IOException("Ошибка записи строки в файл " + outputFileName + ".");
        }
    }
}