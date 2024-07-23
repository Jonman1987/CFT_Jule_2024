package datatypesutility.strings;

public class ExceptionMessages {
    public static String getSetStatisticMessage(){
        return "Ошибка: Вы указали конфликтующие друг с другом параметры статистики.";
    }

    public static String getIsModelWorkResultMessage(){
        return "Ошибка.";
    }

    public static String getIsFileParametersFoundMessage(){
        return "Ошибка: Вы не указали ни одного имени исходного файла в формате .txt";
    }

    public static String getMakeFilesAccessMessagePartOne(){
        return "Ошибка. Вы указали параметр -a, но файл ";
    }

    public static String getMakeFilesAccessMessagePartTwo(){
        return ", в который нужно записать данные не найден!";
    }

    public static String getMakeFilesAccessMessagePartThree(){
        return "Ошибка подготовки к записи данных.\n";
    }

    public static String getMakeFilesAccessMessagePartFour(){
        return "Сообщение: ";
    }

    public static String getStartFilesSortMessagePartOne(){
        return "Ошибка считывания строки из файла ";
    }

    public static String getStartFilesSortMessagePartTwo(){
        return "Сообщение: ";
    }

    public static String getWriteLineMessage(){
        return "Ошибка записи строки в файл ";
    }
}
