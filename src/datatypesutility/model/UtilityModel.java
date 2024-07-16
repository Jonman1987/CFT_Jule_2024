package datatypesutility.model;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class UtilityModel implements Model {
    private int statisticsCode = 0;

    private LinkedList<String> inputFilesNames;
    private String integerFileName = "Integers.txt";
    private String doubleFileName = "Floats.txt";
    private String stringFileName = "Strings.txt";
    //private String inputPath = "";
    private String outputPath = "";
    private String filesPrefix = "";

    private boolean hasOptionO = false;
    private boolean hasOptionP = false;
    private boolean hasOptionA = false;

    private boolean hasIntegerOutputFile = false; // TODO: Если не нужно, то удалить
    private boolean hasDoubleOutputFile = false;
    private boolean hasStringOutputFile = false;

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

    //public void setInputPath(String inputPath) {
        //this.inputPath = inputPath;
    //}

    //public String getInputPath() {
        //return inputPath;
    //}

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

    public boolean startFilesSort(Scanner scanner) throws IOException {
        inputFilesNames = new LinkedList<>();
        inputFilesNames.add("file1.txt");
        inputFilesNames.add("file2.txt");
        scanner.useLocale(Locale.US);

        Scanner[] scanners = new Scanner[inputFilesNames.size()];

        for(int i = 0; i < inputFilesNames.size(); i++){
            scanners[i] = new Scanner(new FileInputStream(inputFilesNames.get(i)));
        }

        String string = "";
        BigInteger bigInteger;
        BigDecimal bigDecimal;

        int g = 0;

        while (g <= 9){
         //   for(int i = 0; i < scanners.length; i++){
                string = scanner.nextLine();

                    try {
                        bigInteger = new BigInteger(string);
                        try (FileWriter fileWriter1 = new FileWriter("integer.txt", true)) {
                            fileWriter1.write(String.valueOf(bigInteger));
                            fileWriter1.write("\n");
                            continue;
                        }
                    }catch (Exception e){

                    }

                    try {
                        bigDecimal = new BigDecimal(string);
                        try (FileWriter fileWriter1 = new FileWriter("decimal.txt", true)) {
                            fileWriter1.write(String.valueOf(bigDecimal));
                            fileWriter1.write("\n");
                            continue;
                        }
                    }catch (Exception e){

                    }

                   try {
                       try (FileWriter fileWriter3 = new FileWriter("string.txt", true)) {
                           fileWriter3.write(string);
                           fileWriter3.write("\n");
                       }
                   }catch (Exception e){

                   }



           // }

            g++;
        }

        return true;
    }

    private void fileWriter(FileWriter fileWriter) {

    }

    public Map<String, Number> getStatistic(int statisticsCode) {
        HashMap<String, Number> map = new HashMap<>();

        if(statisticsCode == 1){
            map.put(integerFileName, integerFileElementsCount);
            map.put(doubleFileName, doubleFileElementsCount);
            map.put(stringFileName, stringFileElementsCount);
        }

        if(statisticsCode == 2){
            map.put(integerFileName, integerFileElementsCount);
            map.put(integerFileName, integersElementsSum);
            map.put(integerFileName, integersElementsAverage);
            map.put(integerFileName, minInteger);
            map.put(integerFileName, maxInteger);
            map.put(doubleFileName, doubleFileElementsCount);
            map.put(doubleFileName, doublesElementsSum);
            map.put(doubleFileName, doublesElementsAverage);
            map.put(doubleFileName, minDouble);
            map.put(doubleFileName, maxDouble);
            map.put(stringFileName, stringFileElementsCount);
            map.put(stringFileName, minString);
            map.put(stringFileName, maxString);
        }

        return map;
    }
}
