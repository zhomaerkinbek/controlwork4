import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cat {
    private static final Random rnd = new Random();
    private static final List<String> usedNames = new ArrayList<>();
    private static final List<String> names = List.of("Peach", "Ginger", "Toby", "Seth", "Tibbles", "Tabby", "Poppy", "Millie", "Daisy", "Jasper", "Misty", "Minka");
    private String name;
    private int age;
    private int satiety;
    private int mood;
    private int health;
    private transient int average;
    private boolean played;

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        satiety = rnd.nextInt(61) + 20;
        mood = rnd.nextInt(61) + 20;
        health = rnd.nextInt(61) + 20;
        average = (satiety + mood + health) / 3;
        played = false;
    }

    public Cat() {
        name = getRandomName(names.get(rnd.nextInt(names.size())));
        age = rnd.nextInt(19) + 1;
        satiety = rnd.nextInt(61) + 20;
        mood = rnd.nextInt(61) + 20;
        health = rnd.nextInt(61) + 20;
        average = (satiety + mood + health) / 3;
        played = false;
    }

    public static final List<Cat> makeCats(int amount) {
        return Stream.generate(Cat::new)
                .limit(amount)
                .collect(Collectors.toList());
    }


    public void eatCat(int number){
        upSatiety(number);
        upMood(number);
        updateAverage();
    }
    public void healCat(List<Integer> number){
        upHealth(number.get(0));
        downMood(number.get(1));
        downSatiety(number.get(1));
        updateAverage();
    }

    public void playCat(List<Integer> number){
        upHealth(number.get(0));
        upMood(number.get(0));
        downSatiety(number.get(1));
        updateAverage();
    }

    public void upHealth(int health){
        setHealth(Math.min(Math.max(getHealth() + health, 0), 100));
    }
    public void downHealth(int health){
        setHealth(Math.max(getHealth() - health, 0));
    }
    public void upMood(int mood){
        setMood(Math.min(Math.max(getMood() + mood, 0), 100));
    }
    public void downMood(int mood){
        setMood(Math.max(getMood() - mood, 0));
    }
    public void upSatiety(int satiety){
        setSatiety(Math.min(Math.max(getSatiety() + satiety, 0), 100));
    }
    public void downSatiety(int satiety){
        setSatiety(Math.max(getSatiety() - satiety, 0));
    }


    public int getAverage() {
        return average;
    }

    public void updateAverage() {
        average = (satiety + mood + health) / 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private String getRandomName(String name){
        if(usedNames.contains(name)){
            return getRandomName(names.get(rnd.nextInt(names.size())));
        }
        usedNames.add(name);
        return name;
    }
    public void nextDay(){
        this.downSatiety(rnd.nextInt(5) + 1);
        this.upMood(rnd.nextInt(7) - 3);
        this.upHealth(rnd.nextInt(7) - 3);
        updateAverage();
        setPlayed(false);
    }

}
