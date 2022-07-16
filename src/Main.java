import java.util.*;

public class Main {
    static final Scanner console = new Scanner(System.in);
    static List<Cat> cats = Cat.makeCats(3);
    static Map<String, State> eventObj = new HashMap<>();

    static {
        eventObj.put("1", State.EAT);
        eventObj.put("2", State.PLAY);
        eventObj.put("3", State.HEAL);
        eventObj.put("a", State.ADD);
        eventObj.put("n", State.NEXT_DAY);
    }
    public static void main(String[] args){
        cats.sort(Comparator.comparing(Cat::getAverage).reversed());
        Printer.print(cats);
        while (true) {
            events();
        }
    }
    static void events(){
        String act;
        System.out.println( "1: Покормить\n" +
                            "2: Поиграть\n" +
                            "3: К ветеринару\n" +
                            "a: Завести нового питомца\n" +
                            "n: Следующий день");
        System.out.print("Введите номер/букву с действием: ");
        act = console.nextLine();
        if(eventObj.get(act) != null) {
            eventObj.get(act).run(cats);
            Printer.print(cats);

        }else System.out.println("Такого действия нету!");
    }

    static int tryParseAge(String str) throws Exception{
        if(str == null){
            throw new NullPointerException("Значение null!");
        }
        if(str.length() < 1){
            throw new NumberFormatException("Пустое значение!");
        }
        return Integer.parseInt(str);
    }

}
