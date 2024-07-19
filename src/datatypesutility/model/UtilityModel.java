package datatypesutility.model;

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
        reader = new UtilityReader[inputFilesNames.size()];

        LinkedList<Boolean> endOfFiles = new LinkedList<>();

        for (int i = 0; i < reader.length; i++) {
            endOfFiles.add(false);
        }

        for (int i = 0; i < inputFilesNames.size(); i++) {
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
                    throw new IOException("Ошибка считывания строки из файла "
                            + inputFilesNames.get(i) + "\n"
                            + "Сообщение: " + e.getMessage());
                }

                try {
                    bigInteger = new BigInteger(string);
                    isInteger = true;
                } catch (Exception e) {
                    // Строка не является типом int. Нет необходимости в обработке.
                }

                if (isInteger) {
                    try {
                        if (hasOptionA) {
                            File file = new File(outputPath + filesPrefix + integerFileName);

                            if (!file.exists() && !file.isDirectory()) {
                                throw new FileNotFoundException("Ошибка. Вы указали параметр -a, но файл "
                                        + outputPath + filesPrefix + integerFileName
                                        + ", в который нужно записать данные не найден!");
                            }
                        }

                        if (!hasIntegersFile) {
                            integerWriter = new UtilityWriter(hasOptionA);
                            hasIntegersFile = true;
                        }

                        integerWriter.writeLine(1,outputPath + filesPrefix + integerFileName, hasOptionA, bigInteger);

                        isInteger = false;

                        continue;
                    } catch (IOException e) {
                        throw new IOException("Ошибка подготовки к записи данных.\n"
                                + "Сообщение: " + e.getMessage() + ".");
                    }
                }

                try {
                    bigDecimal = new BigDecimal(string);
                    isDouble = true;
                } catch (Exception e) {
                    // Строка не является типом double. Нет необходимости в обработке.
                }

                if (isDouble) {
                    try {
                        if (hasOptionA) {
                            File file = new File(outputPath + filesPrefix + doubleFileName);

                            if (!file.exists() && !file.isDirectory()) {
                                throw new FileNotFoundException("Ошибка. Вы указали параметр -a, но файл "
                                        + outputPath + filesPrefix + doubleFileName
                                        + ", в который нужно записать данные не найден!");
                            }
                        }

                        if (!hasDoublesFile) {
                            doubleWriter = new UtilityWriter(hasOptionA);
                            hasDoublesFile = true;
                        }

                        doubleWriter.writeLine(2,  outputPath + filesPrefix + doubleFileName, hasOptionA, bigDecimal);

                        isDouble = false;

                        continue;
                    } catch (IOException e) {
                        throw new IOException("Ошибка подготовки к записи данных.\n"
                                + "Сообщение: " + e.getMessage() + ".");
                    }
                }

                if(!string.isEmpty()) {
                    try {
                        if (hasOptionA) {
                            File file = new File(outputPath + filesPrefix + stringFileName);

                            if (!file.exists() && !file.isDirectory()) {
                                throw new FileNotFoundException("Ошибка. Вы указали параметр -a, но файл "
                                        + outputPath + filesPrefix + stringFileName
                                        + ", в который нужно записать данные не найден!");
                            }
                        }

                        if (!hasStringsFile) {
                            stringWriter = new UtilityWriter(hasOptionA);
                            hasStringsFile = true;
                        }

                        stringWriter.writeLine(3,outputPath + filesPrefix + stringFileName, hasOptionA, string);
                    } catch (IOException e) {
                        throw new IOException("Ошибка подготовки к записи данных.\n"
                                + "Сообщение: " + e.getMessage() + ".");
                    }
                }
            }
        } while (endOfFiles.contains(false));

        return true;
    }

    public LinkedList<Number> getStatistic() {
        // TODO: Подумать как упростить выборку статистики
        return UtilityStatistics.getStatistic();
    }
}