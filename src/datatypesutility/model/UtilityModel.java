package datatypesutility.model;

import datatypesutility.model.reader.UtilityReader;
import datatypesutility.model.writer.UtilityStatistic;
import datatypesutility.model.writer.UtilityWriter;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class UtilityModel implements Model {
    UtilityReader[] reader;
    UtilityWriter integerWriter;
    UtilityWriter doubleWriter;
    UtilityWriter stringWriter;

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

    public boolean startFilesSort() throws IOException { // TODO: Вернуть IOEXCEPTION
        reader = new UtilityReader[inputFilesNames.size()];

        boolean appendsStatus = false;

        boolean isInteger = false;
        boolean isDouble = false;
        boolean isString = false;

        LinkedList<Boolean> endOfFiles = new LinkedList<>();

        for (int i = 0; i < reader.length; i++) {
            endOfFiles.add(false);
        }

        for (int i = 0; i < inputFilesNames.size(); i++) {
            reader[i] = new UtilityReader();
            reader[i].setBufferedReader(inputFilesNames.get(i));
        }

        if (hasOptionA) {
            appendsStatus = true;
        }

        String string;
        BigInteger bigInteger = new BigInteger(String.valueOf(0));
        BigDecimal bigDecimal = new BigDecimal(0);

        do {
            for (int i = 0; i < reader.length; i++) {
                try {
                    string = reader[i].getLine();

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
                    isInteger = true;
                } catch (Exception e) { // TODO: нужно вынести без вложения сделать через флаги

                }

                if (isInteger) {
                    try {
                        if(hasOptionA){
                            File file = new File(outputPath + filesPrefix + integerFileName);

                            if (!file.exists() && !file.isDirectory()) {
                                throw new FileNotFoundException("Файл не найден"); // TODO: Подписать ошибки
                            }
                        }

                        if(!hasIntegersFile){
                            integerWriter = new UtilityWriter(appendsStatus);
                            hasIntegersFile = true;
                        }

                        integerWriter.writeLine(outputPath + filesPrefix + integerFileName, bigInteger);

                        isInteger = false;

                        continue;
                    } catch (IOException e) {
                        throw new IOException(e.getMessage());
                    }
                }

                try {
                    bigDecimal = new BigDecimal(string);
                    isDouble = true;
                } catch (Exception e) { // TODO: нужно вынести без вложения сделать через флаги
                    // Строка не является
                }

                if (isDouble) {
                    try {
                        if(!hasDoublesFile){
                            doubleWriter = new UtilityWriter(appendsStatus);
                            hasDoublesFile = true;
                        }

                        doubleWriter.writeLine(outputPath + filesPrefix + doubleFileName, bigDecimal);

                        isDouble = false;
                        continue;
                    } catch (Exception e) {
                        throw new IOException("Ошибка double");
                    }
                }

                try {
                    if(!hasStringsFile){
                        stringWriter = new UtilityWriter(appendsStatus);
                        hasStringsFile = true;
                    }

                    stringWriter.writeLine(outputPath + filesPrefix + stringFileName, string);

                } catch (Exception e) { // TODO: нужно вынести без вложения сделать через флаги
                    throw new IOException("Ошибка string");
                }
            }
        } while (endOfFiles.contains(false));

        return true;
    }

    public LinkedList<Number> getStatistic() {
        // TODO: Подумать как упростить выборку статистики
        return UtilityStatistic.getStatistic();
    }
}
