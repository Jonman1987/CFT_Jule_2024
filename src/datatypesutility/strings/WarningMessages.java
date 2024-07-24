package datatypesutility.strings;

public class WarningMessages {
    public static String getControlPathAfterOMessageOne() {
        return "Внимание: Вы не указали путь после команды -o. Файлы сохранены в текущую папку.";
    }

    public static String getControlPathAfterOMessageTwo() {
        return "Внимание: Вы указали путь для исходящих файлов с использованием спецсимвола. Файлы сохранены в текущую папку.";
    }

    public static String getIsPrefixAfterPFoundMessageOne() {
        return "Внимание: Вы не указали префикс названия файла после команды -p. Файлы сохранены с именем по умолчанию\n";
    }

    public static String getIsPrefixAfterPFoundMessageTwo() {
        return "Внимание: Вы указали название файла вместо префикса. Файлы сохранены с именем по умолчанию\n";
    }

    public static String getIsPrefixAfterPFoundMessageThree() {
        return "Внимание: Вы указали префикс названия файла с использованием спецсимвола. Файлы сохранены с именем по умолчанию\n";
    }

    public static String getIsModelWorkResultMessage() {
        return "Внимание! Работа программы принудительно завершена!";
    }
}