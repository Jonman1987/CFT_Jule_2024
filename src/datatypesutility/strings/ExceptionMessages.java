package datatypesutility.strings;

public class ExceptionMessages {
    public static String getSetStatisticMessage() {
        return "Ошибка: Вы указали конфликтующие друг с другом параметры статистики.";
    }

    public static String getIsFileFoundMessageOne() {
        return "Ошибка. Файл ";
    }

    public static String getIsFileFoundMessageTwo() {
        return " не найден!";
    }

    public static String getIsFileParametersFoundMessage() {
        return "Ошибка: Вы не указали ни одного имени исходного файла в формате .txt";
    }

    public static String getIsFileParametersFoundMessageTwo() {
        return "Ошибка: В имени входного файла указано только расширение";
    }

    public static String getStartFilesSortMessagePartOne() {
        return "Ошибка считывания строки из файла ";
    }

    public static String getStartFilesSortMessagePartTwo() {
        return "Сообщение: ";
    }

    public static String getWriteLineMessage() {
        return "Ошибка записи строки в файл ";
    }
}