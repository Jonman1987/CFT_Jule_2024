package datatypesutility.model;

import java.io.IOException;
import java.util.LinkedList;

public interface Model {
    boolean startFilesSort() throws Exception;

    void setOutputPath(String outputPath);

    void setInputFilesNames(LinkedList<String> inputFilesNames);
    LinkedList<String> getInputFilesNames();

    void setStatisticsCode(int statisticsCode);

    int getStatisticsCode();

    void setFilesPrefix(String filesPrefix);
    void setHasOptionO(boolean hasOptionO);
    void setHasOptionP(boolean hasOptionP);
    void setHasOptionA(boolean hasOptionA);
    LinkedList<Number> getStatistic();
    boolean getHasIntegersFile();
    boolean getHasDoublesFile();
    boolean getHasStringsFile();
    String getIntegerFileName();
    String getDoubleFileName();
    String getStringFileName();

}