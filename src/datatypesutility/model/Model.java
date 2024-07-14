package datatypesutility.model;

import java.io.IOException;
import java.util.LinkedList;

public interface Model {
    boolean startFilesSort() throws IOException;

    void setOutputPath(String outputPath);

    void setInputFilesNames(LinkedList<String> inputFilesNames);

    void setStatisticsCode(int statisticsCode);

    int getStatisticsCode();

    void setFilesPrefix(String filesPrefix);
    void setHasOptionO(boolean hasOptionO);
    void setHasOptionP(boolean hasOptionP);
    void setHasOptionA(boolean hasOptionA);
}