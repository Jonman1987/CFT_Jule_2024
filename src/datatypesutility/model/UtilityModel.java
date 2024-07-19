package datatypesutility.model;

import datatypesutility.messages.ExceptionMessages;
import datatypesutility.model.reader.UtilityReader;
import datatypesutility.model.writer.UtilityStatistics;
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

    public boolean startFilesSort() throws IOException { // TODO: Сделать декомпозицию по аналогии с UtilityWriter
        final int integerFileCode = 1;
        final int doubleFileCode = 2;
        final int stringFileCode = 3;

        reader = new UtilityReader[inputFilesNames.size()];

        LinkedList<Boolean> endOfFiles = new LinkedList<>();

        for (int i = 0; i < inputFilesNames.size(); i++) {
            endOfFiles.add(false);

            reader[i] = new UtilityReader();
            reader[i].setBufferedReader(inputFilesNames.get(i));
        }

        boolean isInteger = false;
        boolean isDouble = false;

        String string;

        BigInteger bigInteger = new BigInteger(String.valueOf(0));
        BigDecimal bigDecimal = new BigDecimal(0);

        do {
            for (int i = 0; i < reader.length; i++) {
                try {
                    string = reader[i].getLine();

                    if (string == null) {
                        endOfFiles.set(i, true);
                        reader[i].CloseBufferReader();

                        continue;
                    }
                } catch (IOException e) {
                    throw new IOException(ExceptionMessages.getStartFilesSortMessagePartOne()
                            + inputFilesNames.get(i) + "\n"
                            + ExceptionMessages.getStartFilesSortMessagePartTwo()
                            + e.getMessage());
                }

                try {
                    bigInteger = new BigInteger(string);
                    isInteger = true;
                } catch (Exception e) {
                    // Строка не является типом int. Нет необходимости в обработке.
                }

                if (isInteger) {
                    if (!hasIntegersFile) {
                        integerWriter = new UtilityWriter(hasOptionA);
                        integerWriter.setFileWriter(outputPath + filesPrefix + integerFileName);
                        hasIntegersFile = true;
                    }

                    makeFilesAccess(integerFileCode, hasOptionA, outputPath + filesPrefix + integerFileName,
                            integerWriter, bigInteger);

                    isInteger = false;

                    continue;
                }

                try {
                    bigDecimal = new BigDecimal(string);
                    isDouble = true;
                } catch (Exception e) {
                    // Строка не является типом double. Нет необходимости в обработке.
                }

                if (isDouble) {
                    if (!hasDoublesFile) {
                        doubleWriter = new UtilityWriter(hasOptionA);
                        doubleWriter.setFileWriter(outputPath + filesPrefix + doubleFileName);
                        hasDoublesFile = true;
                    }

                    makeFilesAccess(doubleFileCode, hasOptionA, outputPath + filesPrefix + doubleFileName,
                            doubleWriter, bigDecimal);

                    isDouble = false;

                    continue;
                }

                if (!string.isEmpty()) {
                    if (!hasStringsFile) {
                        stringWriter = new UtilityWriter(hasOptionA);
                        stringWriter.setFileWriter(outputPath + filesPrefix + stringFileName);
                        hasStringsFile = true;
                    }

                    makeFilesAccess(stringFileCode, hasOptionA, outputPath + filesPrefix + stringFileName,
                            stringWriter, string);
                }
            }
        } while (endOfFiles.contains(false));

        closeFileWriterResources();

        return true;
    }

    private <T> void makeFilesAccess(int fileCode, boolean hasOptionA, String outputFilesPath, UtilityWriter fileWriter,
                                     T data) throws IOException {
        try {
            if (hasOptionA) {
                File file = new File(outputFilesPath);

                if (!file.exists() && !file.isDirectory()) {
                    throw new FileNotFoundException(ExceptionMessages.getMakeFilesAccessMessagePartOne()
                            + outputFilesPath
                            + ExceptionMessages.getMakeFilesAccessMessagePartTwo());
                }
            }

            fileWriter.writeLine(fileCode, outputFilesPath, hasOptionA, data);
        } catch (IOException e) {
            throw new IOException(ExceptionMessages.getMakeFilesAccessMessagePartThree()
                    + ExceptionMessages.getMakeFilesAccessMessagePartFour()
                    + e.getMessage());
        }
    }

    private void closeFileWriterResources() throws IOException {
        if(hasIntegersFile){
            integerWriter.closeFileWriter();
        }

        if(hasDoublesFile){
            doubleWriter.closeFileWriter();
        }

        if(hasStringsFile){
            stringWriter.closeFileWriter();
        }
    }

    public LinkedList<Number> getStatistic() {
        return UtilityStatistics.getStatistic();
    }
}