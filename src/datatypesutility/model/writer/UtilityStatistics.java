package datatypesutility.model.writer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;

public class UtilityStatistics {
    private static int integerFileElementsCount = 0;
    private static int doubleFileElementsCount = 0;
    private static int stringFileElementsCount = 0;

    private static BigInteger integersElementsSum = new BigInteger(String.valueOf(0));
    private static BigDecimal doublesElementsSum = new BigDecimal(0);

    private static BigDecimal integersElementsAverage = new BigDecimal(0);
    private static BigDecimal doublesElementsAverage = new BigDecimal(0);

    private static BigInteger maxInteger = new BigInteger(String.valueOf(Integer.MIN_VALUE));
    private static BigDecimal maxDouble = new BigDecimal(Double.MIN_VALUE);

    private static BigInteger minInteger = new BigInteger(String.valueOf(Integer.MAX_VALUE));
    private static BigDecimal minDouble = new BigDecimal(Double.MAX_VALUE);

    private static int maxString = 0;
    private static int minString = Integer.MAX_VALUE;

    public <T> void addStatistic(int fileCode, T data) {
        if (fileCode == 1) {
            integerFileElementsCount++;
            integersElementsSum = integersElementsSum.add((BigInteger) data);

            integersElementsAverage = new BigDecimal(integersElementsSum)
                    .divide(new BigDecimal(integerFileElementsCount), MathContext.DECIMAL128);

            if (((BigInteger) data).compareTo(maxInteger) > 0) {
                maxInteger = (BigInteger) data;
            }

            if (((BigInteger) data).compareTo(minInteger) < 0) {
                minInteger = (BigInteger) data;
            }
        } else if (fileCode == 2) {
            doubleFileElementsCount++;
            doublesElementsSum = doublesElementsSum.add((BigDecimal) data);

            doublesElementsAverage = doublesElementsSum
                    .divide(new BigDecimal(doubleFileElementsCount), MathContext.DECIMAL128);

            if (((BigDecimal) data).compareTo(maxDouble) > 0) {
                maxDouble = (BigDecimal) data;
            }

            if (((BigDecimal) data).compareTo(minDouble) < 0) {
                minDouble = (BigDecimal) data;
            }
        } else {
            stringFileElementsCount++;

            if (((String) data).length() > maxString) {
                maxString = ((String) data).length();
            }

            if (((String) data).length() < minString) {
                minString = ((String) data).length();
            }
        }
    }

    public static LinkedList<Number> getStatistic() {
        return new LinkedList<>(List.of(
                integerFileElementsCount,
                integersElementsSum,
                integersElementsAverage,
                minInteger,
                maxInteger,
                doubleFileElementsCount,
                doublesElementsSum,
                doublesElementsAverage,
                minDouble,
                maxDouble,
                stringFileElementsCount,
                minString,
                maxString));
    }
}