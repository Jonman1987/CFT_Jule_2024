package datatypesutility.model;

import java.util.LinkedList;

public interface Model {
    boolean startFilesSort();
    void setOutputPath(String outputPath);

    void setIntegerFilename(String integerFilename);
    String getIntegerFileName();

    void setDoubleFileName(String doubleFileName);
    String getDoubleFileName();

    void setStringFileName(String stringFileName);
    String getStringFileName();

    void setInputFilesNames(LinkedList<String> inputFilesNames);
}