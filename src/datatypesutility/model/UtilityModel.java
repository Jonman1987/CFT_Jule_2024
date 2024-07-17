package datatypesutility.model;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;

public class UtilityModel implements Model {
    private int statisticsCode = 0;

    private LinkedList<String> inputFilesNames;
    private String integerFileName = "Integers.txt";
    private String doubleFileName = "Floats.txt";
    private String stringFileName = "Strings.txt";
    private String outputPath = "";
    private String filesPrefix = "";

    private boolean hasOptionO = false;
    private boolean hasOptionP = false;
    private boolean hasOptionA = false;

    private boolean hasIntegersFile = false;
    private boolean hasDoublesFile = false;
    private boolean hasStringsFile = false;

    private int integerFileElementsCount = 0;
    private int doubleFileElementsCount = 0;
    private int stringFileElementsCount = 0;

    private BigInteger integersElementsSum = new BigInteger(String.valueOf(0));
    private BigDecimal doublesElementsSum = new BigDecimal(0);

    private BigDecimal integersElementsAverage = new BigDecimal(0);
    private BigDecimal doublesElementsAverage = new BigDecimal(0);

    private BigInteger maxInteger = new BigInteger(String.valueOf(Integer.MIN_VALUE));
    private BigDecimal maxDouble = new BigDecimal(Double.MIN_VALUE);

    private BigInteger minInteger = new BigInteger(String.valueOf(Integer.MAX_VALUE));
    private BigDecimal minDouble = new BigDecimal(Double.MAX_VALUE);

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

    public void setHasIntegersFile(boolean hasIntegersFile) {
        this.hasIntegersFile = hasIntegersFile;
    }

    public boolean getHasIntegersFile() {
        return hasIntegersFile;
    }

    public void setHasDoublesFile(boolean hasDoublesFile) {
        this.hasDoublesFile = hasDoublesFile;
    }

    public boolean getHasDoublesFile() {
        return hasDoublesFile;
    }

    public void setHasStringsFile(boolean hasStringsFile) {
        this.hasStringsFile = hasStringsFile;
    }

    public boolean getHasStringsFile() {
        return hasStringsFile;
    }

    public boolean startFilesSort() throws IOException {
        inputFilesNames = new LinkedList<>();
        inputFilesNames.add("file1.txt"); // TODO: поменять зависимости
        inputFilesNames.add("file2.txt");

        BufferedReader[] bufferedReaders = new BufferedReader[inputFilesNames.size()];

        LinkedList<Boolean> endOfFiles = new LinkedList<>();

        for (int i = 0; i < bufferedReaders.length; i++) {
            endOfFiles.add(false);
        }

        for (int i = 0; i < inputFilesNames.size(); i++) {
            bufferedReaders[i] = new BufferedReader(new FileReader(inputFilesNames.get(i)));
        }

        String string = "";
        BigInteger bigInteger;
        BigDecimal bigDecimal;

        do { // TODO: Сделать проверку по файлам
            for (int i = 0; i < bufferedReaders.length; i++) {
                try {
                    string = bufferedReaders[i].readLine();

                    if (string == null) {
                        endOfFiles.set(i, true);
                        continue;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());

                    throw new IOException("Ошибка считывания строки из файла " + inputFilesNames.get(i));
                }

                try {
                    bigInteger = new BigInteger(string);
                    try (FileWriter integersWriter = new FileWriter(outputPath + filesPrefix + integerFileName, true)) {
                        integersWriter.write(String.valueOf(bigInteger));
                        integersWriter.write("\n");

                        if (!hasIntegersFile) {
                            hasIntegersFile = true;
                        }

                        integerFileElementsCount++;
                        integersElementsSum = integersElementsSum.add(bigInteger);
                        // TODO: Доделать среднее
                        integersElementsAverage = new BigDecimal(integersElementsSum)
                                .divide(new BigDecimal(integerFileElementsCount), MathContext.DECIMAL128);

                        if (bigInteger.compareTo(maxInteger) > 0) {
                            maxInteger = bigInteger;
                        }

                        if (bigInteger.compareTo(minInteger) < 0) {
                            minInteger = bigInteger;
                        }

                        continue;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());

                        throw new IOException("Ошибка записи в файл " + filesPrefix + inputFilesNames);
                    }
                } catch (Exception e) {
                    // Строка не является int
                }

                try {
                    bigDecimal = new BigDecimal(string);
                    try (FileWriter doublesWriter = new FileWriter(outputPath + filesPrefix + doubleFileName, true)) {
                        doublesWriter.write(String.valueOf(bigDecimal));
                        doublesWriter.write("\n");

                        if (!hasDoublesFile) {
                            hasDoublesFile = true;
                        }

                        doubleFileElementsCount++;
                        doublesElementsSum = doublesElementsSum.add(bigDecimal);
                        // TODO: Доделать среднее 1.528535047E-25 проскакивает
                        doublesElementsAverage = doublesElementsSum
                                .divide(new BigDecimal(doubleFileElementsCount), MathContext.DECIMAL128);

                        if (bigDecimal.compareTo(maxDouble) > 0) {
                            maxDouble = bigDecimal;
                        }

                        if (bigDecimal.compareTo(minDouble) < 0) {
                            minDouble = bigDecimal;
                        }

                        continue;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());

                        throw new IOException("Ошибка записи в файл " + filesPrefix + doubleFileName);
                    }
                } catch (Exception e) {
                    // Строка не является double
                }

                try {
                    try (FileWriter stringsWriter = new FileWriter(outputPath + filesPrefix + stringFileName, true)) {
                        stringsWriter.write(string);
                        stringsWriter.write("\n");

                        if (!hasStringsFile) {
                            hasStringsFile = true;
                        }

                        stringFileElementsCount++;

                        if (string.length() > maxString) {
                            maxString = string.length();
                        }

                        if (string.length() < minString) {
                            minString = string.length();
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());

                        throw new IOException("Ошибка записи в файл " + filesPrefix + stringFileName);
                    }
                } catch (Exception e) {
                    // TODO: Подумать оставлять ли эти места пустыми
                    return false;
                }
            }
        } while (endOfFiles.contains(false));

        return true;
    }

    private void fileWriter(FileWriter fileWriter) {

    }

    public LinkedList<Number> getStatistic() {
        // TODO: Подумать как упростить выборку статистики
        LinkedList<Number> statisticList = new LinkedList<>();

        statisticList.add(integerFileElementsCount);
        statisticList.add(integersElementsSum);
        statisticList.add(integersElementsAverage);
        statisticList.add(minInteger);
        statisticList.add(maxInteger);

        statisticList.add(doubleFileElementsCount);
        statisticList.add(doublesElementsSum);
        statisticList.add(doublesElementsAverage);
        statisticList.add(minDouble);
        statisticList.add(maxDouble);

        statisticList.add(stringFileElementsCount);
        statisticList.add(minString);
        statisticList.add(maxString);

        return statisticList;
    }
}
