package datatypesutility.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class UtilityModel implements Model {
    private int statisticsCode = 0;

    private LinkedList<String> inputFilesNames;
    private String integerFileName = "Integers.txt";
    private String doubleFileName = "Floats.txt";
    private String stringFileName = "Strings.txt";
    private String inputPath = "";
    private String outputPath = "";
    private String filesPrefix = "";

    private boolean hasOptionO = false;
    private boolean hasOptionP = false;
    private boolean hasOptionA = false;

    private int integerFileElementsCount = 0;
    private int doubleFileElementsCount = 0;
    private int stringFileElementsCount = 0;

    private int integersElementsSum = 0;
    private double doublesElementsSum = 0.0;

    private int integersElementsAverage = 0;
    private double doublesElementsAverage = 0.0;

    private int maxInteger = Integer.MIN_VALUE;
    private double maxDouble = Double.MIN_VALUE;

    private int minInteger = Integer.MAX_VALUE;
    private double minDouble = Double.MAX_VALUE;

    private int maxString = 0;
    private int minString = Integer.MAX_VALUE;

    public void setInputFilesNames(LinkedList<String> inputFilesNames) {
        this.inputFilesNames = inputFilesNames;
    }

    public LinkedList<String> getInputFilesNames() {
        return inputFilesNames;
    }

    public void setStatisticsCode(int statisticsCode) {
        this.statisticsCode = statisticsCode;
    }

    public int getStatisticsCode() {
        return statisticsCode;
    }

    public void setIntegerFilename(String integerFilename) {
        this.integerFileName = integerFilename;
    }

    public String getIntegerFileName() {
        return integerFileName;
    }

    public void setDoubleFileName(String doubleFileName) {
        this.doubleFileName = doubleFileName;
    }

    public String getDoubleFileName() {
        return doubleFileName;
    }

    public void setStringFileName(String stringFileName) {
        this.stringFileName = stringFileName;
    }

    public String getStringFileName() {
        return stringFileName;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setFilesPrefix(String filesPrefix) {
        this.filesPrefix = filesPrefix;
    }

    public String getFilesPrefix() {
        return filesPrefix;
    }

    public void setHasOptionO(boolean hasOptionO) {
        this.hasOptionO = hasOptionO;
    }

    public boolean getOptionO() {
        return hasOptionO;
    }

    public void setHasOptionP(boolean hasOptionP) {
        this.hasOptionP = hasOptionP;
    }

    public boolean getOptionP() {
        return hasOptionP;
    }

    public void setHasOptionA(boolean hasOptionA) {
        this.hasOptionA = hasOptionA;
    }

    public boolean getOptionA() {
        return hasOptionA;
    }

    public boolean startFilesSort() throws IOException {
        String writeFile = ""; // TODO: Не понятно как будет работать пере присваивание файлов
        String readFile = "";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));

        try (FileWriter fileWriter = new FileWriter(writeFile)) {

        };

        return true;
    }

    private void fileWriter(FileWriter fileWriter) {

    }

    public Number[] getStatistic(int statisticsCode) {
        return new Number[]{};
    }
}
