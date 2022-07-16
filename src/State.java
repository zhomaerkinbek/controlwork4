import java.lang.reflect.Array;
import java.util.*;

public enum State implements Event{
    EAT("Кормить кота"){
        @Override
        public void run(List<Cat> cats) {
            Cat cat = cats.get(getNumberOfCats(cats));
            if(!cat.isPlayed()) {
                cat.eatCat(getStepOfChanging(cat).get(0));
                System.out.printf("Вы покормили кота %s, возраст которого - %s\n", cat.getName(), cat.getAge());
                cat.setPlayed(true);
            }
        }
    },
    PLAY("Играть с котом"){
        @Override
        public void run(List<Cat> cats) {
            Cat cat = cats.get(getNumberOfCats(cats));
            if(!cat.isPlayed()) {
                cat.playCat(getStepOfChanging(cat));
                System.out.printf("Вы поиграли с котом %s, возраст которого - %s\n", cat.getName(), cat.getAge());
                cat.setPlayed(true);
            }
        }
    },
    HEAL("Лечить кота"){
        @Override
        public void run(List<Cat> cats) {
            Cat cat = cats.get(getNumberOfCats(cats));
            if(!cat.isPlayed()) {
                cat.healCat(getStepOfChanging(cat));
                System.out.printf("Вы отвезли к ветеринару кота %s, возраст которого - %s\n", cat.getName(), cat.getAge());
                cat.setPlayed(true);
            }
        }
    },
    ADD("Добавить кота"){
        @Override
        public void run(List<Cat> cats) {
            String newName;
            int newAge;
            System.out.print("Хотите завести нового кота в ручную? (Y/N): ");
            if(console.nextLine().equalsIgnoreCase("Y")){
                System.out.println("Замечательно!");
                while (true) {
                    try {
                        System.out.print("Введите имя кота:");
                        newName = checkName(console.nextLine());
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
                while (true) {
                    try {
                        System.out.print("Введите возраст кота:");
                        newAge = tryParseAge(console.nextLine());
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (Exception e) {
                        System.out.println("Введите числовое значение");
                        continue;
                    }
                    if(newAge > 18){
                        System.out.println("Неправильный возраст кота. Исследование показывает, что коты живут до 18 лет!");
                        continue;
                    }else if(newAge < 1){
                        System.out.println("Неправильный возраст кота. Этот кот еще не родился!");
                        continue;
                    }
                    break;
                }
                cats.add(new Cat(newName, newAge));
                cats.sort(Comparator.comparing(Cat::getAverage).reversed());
                System.out.println("Ураа мы завели нового кота!");
            } else {
                System.out.println("Вы завели нового случайного кота");
                cats.add(new Cat());
                cats.sort(Comparator.comparing(Cat::getAverage).reversed());
            }
        }
    },
    NEXT_DAY("Следующий день"){
        @Override
        public void run(List<Cat> cats) {
            cats.forEach(Cat::nextDay);
        }
    };

    private String value;
    static final Scanner console = new Scanner(System.in);

    private static final Random rnd = new Random();

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static List<Integer> getStepOfChanging(Cat cat){
        if(cat.getAge() < 6){
            return List.of(7, 3);
        } else if(cat.getAge() < 11){
            return List.of(5, 5);
        }
        return List.of(4, 6);
    }

    public int getNumberOfCats(List<Cat> cats){
        int number;
        while (true) {
            try {
                System.out.printf("Введите номер кота (1-%s): ", cats.size());
                number = tryParseAge(console.nextLine());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (Exception e) {
                System.out.println("Введите числовое значение");
                continue;
            }
            if(number < 1 || number > cats.size()){
                System.out.println("Кота с таким номером нету в списке!");
                continue;
            }
            break;
        }
        return number - 1;
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
    static String checkName(String str) throws Exception {
        if(str == null){
            throw new NullPointerException("Значение null!");
        }
        if(str.length() < 1){
            throw new NumberFormatException("Пустое значение!");
        }
        for(int i = 0; i < str.length(); i++){
            if(isInteger(str.charAt(i))){
                throw new Exception("Введите имя без числовых значений");
            }
        }
        return str;
    }

    static boolean isInteger(char s) {
        try {
            Integer.parseInt(String.valueOf(s));
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }


}
