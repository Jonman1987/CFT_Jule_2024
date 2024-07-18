package datatypesutility.model.writer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.LinkedList;

public class UtilityStatistic {
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

    public void addStatistic(BigInteger bigInteger){
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
    }

    public void addStatistic(BigDecimal bigDecimal){
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
    }

    public void addStatistic(String string){
        stringFileElementsCount++;

        if (string.length() > maxString) {
            maxString = string.length();
        }

        if (string.length() < minString) {
            minString = string.length();
        }
    }

    public static LinkedList<Number> getStatistic() {
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
