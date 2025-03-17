import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.regex.Pattern;

public class StudentProcessor {

    private Student[] students;
    private int size;
    private int capacity;

    public StudentProcessor(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.students = new Student[capacity];
    }

    public void createStudent() throws IOException {
        if (size >= capacity)
            throw new IndexOutOfBoundsException("Переповнення студентів!");
        System.out.println("Введіть имя студента: ");
        String name = DataInput.getString();
        System.out.println("Введіть факультет студента: ");
        String faculty = DataInput.getString();
        System.out.println("Введіть курс студента: ");
        int course = DataInput.getInt();
        System.out.println("Введіть спеціалізацію студента: ");
        String spec = DataInput.getString();
        System.out.println("Введіте средній бал студента: ");
        double avgMark = Double.parseDouble(DataInput.getString());
        students[size++] = new Student(name, faculty, course, spec, avgMark);
    }

    public void findByNameStudent() throws IOException {
        System.out.println("Введіте имя студента для пошука: ");
        String pattern = DataInput.getString();
        Pattern regex = Pattern.compile("^" + pattern + ".*", Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < size; i++) {
            if (regex.matcher(students[i].getName()).find()) {
                System.out.println(students[i].toString());
            }
        }
    }

    public void showAllStudents() {
        for (int i = 0; i < size; i++) {
            if (students[i] != null)
                System.out.println(students[i].toString());
        }
    }

    // Сортировка вставкой по имени
    public void sortStudentsByName(boolean ascending) {
        for (int i = 1; i < size; i++) {
            Student key = students[i];
            int j = i - 1;
            while (j >= 0 && (ascending 
                    ? students[j].getName().compareToIgnoreCase(key.getName()) > 0 
                    : students[j].getName().compareToIgnoreCase(key.getName()) < 0)) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = key;
        }
    }

    // Сортировка вставкой по среднему баллу
    public void sortStudentsByAvgMark(boolean ascending) {
        for (int i = 1; i < size; i++) {
            Student key = students[i];
            int j = i - 1;
            while (j >= 0 && (ascending 
                    ? Double.compare(students[j].getAvgMark(), key.getAvgMark()) > 0
                    : Double.compare(students[j].getAvgMark(), key.getAvgMark()) < 0)) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = key;
        }
    }

    // Меню для выбора сортировки
    public void sortStudents() throws IOException {
        System.out.println("""
                Выберите поле для сортировки:
                1. за Імені
                2. По середньому балу
                """);
        int field = DataInput.getInt();
        System.out.println("""
                Выберите порядок сортировки:
                1. За зростанням
                2. За спаданням
                """);
        int order = DataInput.getInt();
        boolean ascending = (order == 1);

        if (field == 1) {
            sortStudentsByName(ascending);
        } else if (field == 2) {
            sortStudentsByAvgMark(ascending);
        } else {
            System.out.println("Невірний вибо поля для сортировки");
        }
    }

    public static void main(String[] args) throws IOException {
        StudentProcessor processor = new StudentProcessor(25);

        while (true) {
            System.out.println("""
                    1. Додати студента.
                    2. Показати всіх студентів.
                    3. Пошук за имени.
                    4. Сортувати студентів.
                    5. Вихід.
                    """);
            int menu = DataInput.getInt();
            switch (menu) {
                case 1 -> processor.createStudent();
                case 2 -> processor.showAllStudents();
                case 3 -> processor.findByNameStudent();
                case 4 -> {
                    processor.sortStudents();
                    System.out.println("Студенти отсортіровани.");
                }
                case 5 -> {
                    return;
                }
                default -> System.out.println("Невірна команда ");
            }
        }
    }
}
