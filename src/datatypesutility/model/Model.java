package datatypesutility.model;

import java.io.IOException;
import java.util.LinkedList;

public interface Model {
    void startFilesSort() throws IOException;

    void setOutputPath(String outputPath);

    void setInputFilesNames(LinkedList<String> inputFilesNames);

    LinkedList<String> getInputFilesNames();

    String getFilesPrefix();

    void setStatisticsCode(int statisticsCode);

    int getStatisticsCode();

    void setFilesPrefix(String filesPrefix);

    void setHasOptionA(boolean hasOptionA);

    LinkedList<Number> getStatistic();

    boolean getHasIntegersFile();

    boolean getHasDoublesFile();

    boolean getHasStringsFile();

    String getIntegerFileName();

    String getDoubleFileName();

    String getStringFileName();
}