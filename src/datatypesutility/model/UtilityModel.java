package datatypesutility.model;

import java.util.Scanner;

public class UtilityModel implements Model {
    private int statisticsCode;

    private String[] inputFilesNames;
    private String integerFileName = "Integers.txt";
    private String doubleFileName = "Floats.txt";
    private String stringFileName = "Strings.txt";
    private String inputPath;
    private String outputPath;
    private String filesPrefix;

    private boolean hasOptionO;
    private boolean hasOptionP;
    private boolean hasOptionA;

    private int integerFileElementsCount;
    private int doubleFileElementsCount;
    private int stringFileElementsCount;

    private int integersElementsSum;
    private double doublesElementsSum;

    private int integersElementsAverage;
    private double doublesElementsAverage;

    private int maxInteger;
    private double maxDouble;

    private int minInteger;
    private double minDouble;

    private int maxString;
    private int minString;

    public void setInputFilesNames(String[] inputFilesNames){
        this.inputFilesNames = inputFilesNames;
    }

    public String[] getInputFilesNames(){
        return inputFilesNames;
    }

    public void setStatisticsCode(int statisticsCode){
        this.statisticsCode = statisticsCode;
    }

    public int getStatisticsCode(){
        return statisticsCode;
    }

    public void setIntegerFilename(String integerFilename){
        this.integerFileName = integerFilename;
    }

    public String getIntegerFileName(){
        return integerFileName;
    }

    public void setDoubleFileName(String doubleFileName){
        this.doubleFileName = doubleFileName;
    }

    public String getDoubleFileName(){
        return doubleFileName;
    }

    public void setStringFileName(String stringFileName){
        this.stringFileName = stringFileName;
    }

    public String getStringFileName(){
        return stringFileName;
    }

    public void setInputPath(String inputPath){
        this.inputPath = inputPath;
    }

    public String getInputPath(){
        return inputPath;
    }

    public void setOutputPath(String outputPath){
        this.outputPath = outputPath;
    }

    public String getOutputPath(){
        return outputPath;
    }

    public void setFilesPrefix(String filesPrefix){
        this.filesPrefix = filesPrefix;
    }

    public String getFilesPrefix(){
        return filesPrefix;
    }

    public void setHasOptionO(boolean hasOptionO){
        this.hasOptionO = hasOptionO;
    }

    public boolean getOptionO(){
        return hasOptionO;
    }

    public void setHasOptionP(boolean hasOptionP){
        this.hasOptionP = hasOptionP;
    }

    public boolean getOptionP(){
        return hasOptionP;
    }

    public void setHasOptionA(boolean hasOptionA){
        this.hasOptionA = hasOptionA;
    }

    public boolean getOptionA(){
        return hasOptionA;
    }

    public boolean startFilesSort(){
        return true;
    }

    private void fileWriter(Scanner scanner){

    }

    public Number[] getStatistic(int statisticsCode){
        return new Number[]{};
    }
}
