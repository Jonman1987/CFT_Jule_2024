package datatypesutility.strings;

public class ArgumentsKeys {
    public static String prefixKeyToLowerCase(){
        return "-p";
    }

    public static String prefixKeyToUpperCase(){
        return prefixKeyToLowerCase().toUpperCase();
    }

    public static String outputPathKeyToLowerCase(){
        return "-o";
    }

    public static String outputPathKeyToUpperCase(){
        return outputPathKeyToLowerCase().toUpperCase();
    }

    public static String fullStatisticKeyToLowerCase(){
        return "-f";
    }

    public static String fullStatisticKeyToUpperCase(){
        return fullStatisticKeyToLowerCase().toUpperCase();
    }

    public static String shortStatisticKeyToLowerCase(){
        return "-s";
    }

    public static String shortStatisticKeyToUpperCase(){
        return shortStatisticKeyToLowerCase().toUpperCase();
    }

    public static String overwritingKeyToLowerCase(){
        return "-a";
    }

    public static String overwritingKeyToUpperCase(){
        return overwritingKeyToLowerCase().toUpperCase();
    }
}
