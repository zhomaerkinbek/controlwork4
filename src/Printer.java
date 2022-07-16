import static java.util.Comparator.*;
import java.util.List;


public final class Printer {
    private static int n;
    private static final void printHeader() {
        System.out.println(String.format("%1$3.3s | %1$10.10s | %1$-10.10s | %1$-10.10s | %1$-10.10s | %1$-7.7s | %1$-15.15s |", "----------------"));
        System.out.println(String.format("%3.3s | %10.10s | %-10.10s | %-10.10s | %-10.10s | %-7.7s | %-15.15s |",
                                         "#", "Имя", "Возраст", "Здоровье", "Настроение", "Сытость", "Средний уровень"));
        System.out.println(String.format("%1$3.3s | %1$10.10s | %1$-10.10s | %1$-10.10s | %1$-10.10s | %1$-7.7s | %1$-15.15s |", "----------------"));
    }
    private static final void printFooter() {
        System.out.println(String.format("%1$3.3s | %1$10.10s | %1$-10.10s | %1$-10.10s | %1$-10.10s | %1$-7.7s | %1$-15.15s |", "----------------"));
    }


    private static final void printCat(Cat cat) {
        n++;
        System.out.println(String.format("%3.3s | %10.10s | %-10.10s | %-10.10s | %-10.10s | %-7.7s | %-15.15s |",
                                         n, (cat.isPlayed() ? "* " : "") + cat.getName(), cat.getAge(), cat.getHealth(), cat.getMood(), cat.getSatiety(), cat.getAverage()));
    }
    public static final void print(List<Cat> cats) {
        n = 0;
        printHeader();
        cats.forEach(Printer::printCat);
        printFooter();
    }
}