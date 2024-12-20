import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцiй з даними типу byte.
 * 
 * <p>Цей клас зчитує данi з файлу "list/byte.data", сортує їх та виконує пошук значення в масивi та списку.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив byte.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi byte.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi byte.</li>
 *   <li>{@link #sortList()} - Сортує список byte.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в списку byte.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мiнiмальне та максимальне значення в списку byte.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingList(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #byteValueToSearch} - Значення byte для пошуку.</li>
 *   <li>{@link #byteArray} - Масив byte.</li>
 *   <li>{@link #byteList} - Список byte.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingList "-45"
 * }
 * </pre>
 */
public class BasicDataOperationUsingList {
    static final String PATH_TO_DATA_FILE = "list/byte.data";

    Byte byteValueToSearch;
    Byte[] byteArray;
    List<Byte> byteList;

    public static void main(String[] args) {
        BasicDataOperationUsingList basicDataOperationUsingList = new BasicDataOperationUsingList(args);
        basicDataOperationUsingList.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingList(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String searchValue = args[0];
        byteValueToSearch = Byte.parseByte(searchValue);

        byteArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        byteList = new ArrayList<>(Arrays.asList(byteArray));
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та список об'єктiв byte з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операцiї з масивом дати та часу
        searchArray();
        findMinAndMaxInArray();

        sortArray();
        
        searchArray();
        findMinAndMaxInArray();

        // операцiї з ArrayList
        searchList();
        findMinAndMaxInList();

        sortList();

        searchList();
        findMinAndMaxInList();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(byteArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв byte та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();
        byteArray = Arrays.stream(byteArray).sorted().toArray(Byte[]::new);
        Utils.printOperationDuration(startTime, "сортування масиву байтiв");
    }

    /**
     * Метод для пошуку значення в масивi байтiв.
     */
    void searchArray() {
        long startTime = System.nanoTime();
        int index = Arrays.asList(byteArray).indexOf(byteValueToSearch);
        Utils.printOperationDuration(startTime, "пошук в масивi байтiв");
        if (index >= 0) {
            System.out.println("Значення '" + byteValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + byteValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi байтiв.
     */
    void findMinAndMaxInArray() {
        if (byteArray == null || byteArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }
    
        long startTime = System.nanoTime();
        Byte min = Arrays.stream(byteArray).min(Byte::compare).orElse(null);
        Byte max = Arrays.stream(byteArray).max(Byte::compare).orElse(null);
        Utils.printOperationDuration(startTime, "пошук мiнiмального i максимального значення в масивi байтiв");
        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Шукає задане значення байтiв в ArrayList байтiв.
     */
    void searchList() {
        long startTime = System.nanoTime();
        int index = byteList.stream().collect(Collectors.toList()).indexOf(byteValueToSearch);
        Utils.printOperationDuration(startTime, "пошук в ArrayList байтiв");
        if (index >= 0) {
            System.out.println("Значення '" + byteValueToSearch + "' знайдено в ArrayList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + byteValueToSearch + "' в ArrayList не знайдено.");
        }
    }    

    /**
     * Знаходить мiнiмальне та максимальне значення в ArrayList байтiв.
     */
    void findMinAndMaxInList() {
        if (byteList == null || byteList.isEmpty()) {
            System.out.println("ArrayList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();
        Byte min = Collections.min(byteList);
        Byte max = Collections.max(byteList);
        Utils.printOperationDuration(startTime, "пошук мiнiмального i максимального значення в ArrayList байтiв");
        System.out.println("Мiнiмальне значення в ArrayList: " + min);
        System.out.println("Максимальне значення в ArrayList: " + max);
    }

    /**
     * Сортує ArrayList об'єктiв byte та виводить початковий i вiдсортований списки.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();
        byteList = byteList.stream().sorted().collect(Collectors.toList());
        Utils.printOperationDuration(startTime, "сортування ArrayList байтiв");
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу byte.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв byte з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв byte.
     */
    static Byte[] readArrayFromFile(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            List<Byte> byteList = br.lines()
                .map(Byte::parseByte)
                .collect(Collectors.toList());
            return byteList.toArray(new Byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new Byte[0];
        }
    }

    /**
     * Записує масив об'єктiв byte у файл.
     * 
     * @param byteArray Масив об'єктiв byte.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(Byte[] byteArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (Byte byteValue : byteArray) {
                writer.write(Byte.toString(byteValue));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}