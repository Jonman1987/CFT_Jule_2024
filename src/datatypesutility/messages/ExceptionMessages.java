package datatypesutility.messages;

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
}
