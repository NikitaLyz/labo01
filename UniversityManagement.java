import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class UniversityManagement {

	 private List<Faculty> faculties;

	    public UniversityManagement() {
	        faculties = new ArrayList<>();
	    }

	    // ===================== ФУНКЦІЇ ДЛЯ УПРАВЛІННЯ ФАКУЛЬТЕТАМИ =====================
	    public void createFaculty() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String name = DataInput.getString();
	        if (findFacultyByName(name) != null) {
	            System.out.println("Факультет з такою назвою вже існує.");
	            return;
	        }
	        Faculty faculty = new Faculty(name);
	        faculties.add(faculty);
	        System.out.println("Факультет створено.");
	    }

	    public void editFaculty() throws IOException {
	        System.out.println("Введіть назву факультету для редагування:");
	        String name = DataInput.getString();
	        Faculty faculty = findFacultyByName(name);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть нову назву факультету:");
	        String newName = DataInput.getString();
	        faculty.setName(newName);
	        System.out.println("Факультет оновлено.");
	    }

	    public void deleteFaculty() throws IOException {
	        System.out.println("Введіть назву факультету для видалення:");
	        String name = DataInput.getString();
	        Faculty faculty = findFacultyByName(name);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        faculties.remove(faculty);
	        System.out.println("Факультет видалено.");
	    }

	    public Faculty findFacultyByName(String name) {
	        for (Faculty f : faculties) {
	            if (f.getName().equalsIgnoreCase(name)) {
	                return f;
	            }
	        }
	        return null;
	    }

	    public void listFaculties() {
	        if (faculties.isEmpty()) {
	            System.out.println("Немає факультетів.");
	        } else {
	            System.out.println("Список факультетів:");
	            for (Faculty f : faculties) {
	                System.out.println(f);
	            }
	        }
	    }

	    // ===================== ФУНКЦІЇ ДЛЯ УПРАВЛІННЯ КАФЕДРАМИ =====================
	    public void createDepartment() throws IOException {
	        System.out.println("Введіть назву факультету, до якого додається кафедра:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        if (findDepartmentByName(faculty, deptName) != null) {
	            System.out.println("Кафедра з такою назвою вже існує.");
	            return;
	        }
	        Department dept = new Department(deptName);
	        faculty.addDepartment(dept);
	        System.out.println("Кафедру створено.");
	    }

	    public void editDepartment() throws IOException {
	        System.out.println("Введіть назву факультету, до якого належить кафедра:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри для редагування:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть нову назву кафедри:");
	        String newDeptName = DataInput.getString();
	        dept.setName(newDeptName);
	        System.out.println("Кафедру оновлено.");
	    }

	    public void deleteDepartment() throws IOException {
	        System.out.println("Введіть назву факультету, до якого належить кафедра:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри для видалення:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        faculty.getDepartments().remove(dept);
	        System.out.println("Кафедру видалено.");
	    }

	    public Department findDepartmentByName(Faculty faculty, String deptName) {
	        for (Department d : faculty.getDepartments()) {
	            if (d.getName().equalsIgnoreCase(deptName)) {
	                return d;
	            }
	        }
	        return null;
	    }

	    // ============= ФУНКЦІЇ ДЛЯ УПРАВЛІННЯ СТУДЕНТАМИ ТА ВИКЛАДАЧАМИ У КАФЕДРІ =============
	    public void addStudentToDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        try {
	            System.out.println("Введіть ім'я студента: ");
	            String name = DataInput.getString();
	            System.out.println("Введіть факультет студента: ");
	            String studFaculty = DataInput.getString();
	            System.out.println("Введіть курс студента: ");
	            int course = DataInput.getInt();
	            System.out.println("Введіть спеціалізацію студента (група): ");
	            String spec = DataInput.getString();
	            System.out.println("Введіть середній бал студента: ");
	            double avgMark = Double.parseDouble(DataInput.getString());
	            Student student = new Student(name, studFaculty, course, spec, avgMark);
	            dept.addStudent(student);
	            System.out.println("Студента додано до кафедри.");
	        } catch (Exception e) {
	            System.out.println("Помилка при додаванні студента: " + e.getMessage());
	        }
	    }

	    public void editStudentInDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть ім'я студента для редагування:");
	        String name = DataInput.getString();
	        Student student = null;
	        for (Student s : dept.getStudents()) {
	            if (s.getName().equalsIgnoreCase(name)) {
	                student = s;
	                break;
	            }
	        }
	        if (student == null) {
	            System.out.println("Студента не знайдено.");
	            return;
	        }
	        // Для спрощення — повторне введення всіх даних
	        System.out.println("Введіть нове ім'я студента: ");
	        String newName = DataInput.getString();
	        System.out.println("Введіть новий факультет студента: ");
	        String newFaculty = DataInput.getString();
	        System.out.println("Введіть новий курс студента: ");
	        int newCourse = DataInput.getInt();
	        System.out.println("Введіть нову спеціалізацію студента (група): ");
	        String newSpec = DataInput.getString();
	        System.out.println("Введіть новий середній бал студента: ");
	        double newAvgMark = Double.parseDouble(DataInput.getString());
	        Student newStudent = new Student(newName, newFaculty, newCourse, newSpec, newAvgMark);
	        dept.getStudents().remove(student);
	        dept.getStudents().add(newStudent);
	        System.out.println("Інформацію про студента оновлено.");
	    }

	    public void deleteStudentFromDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть ім'я студента для видалення:");
	        String name = DataInput.getString();
	        Student student = null;
	        for (Student s : dept.getStudents()) {
	            if (s.getName().equalsIgnoreCase(name)) {
	                student = s;
	                break;
	            }
	        }
	        if (student == null) {
	            System.out.println("Студента не знайдено.");
	            return;
	        }
	        dept.getStudents().remove(student);
	        System.out.println("Студента видалено.");
	    }

	    public void addTeacherToDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        try {
	            System.out.println("Введіть ім'я викладача: ");
	            String name = DataInput.getString();
	            System.out.println("Введіть предмет викладача: ");
	            String subject = DataInput.getString();
	            Teacher teacher = new Teacher(name, subject);
	            dept.addTeacher(teacher);
	            System.out.println("Викладача додано до кафедри.");
	        } catch (Exception e) {
	            System.out.println("Помилка при додаванні викладача: " + e.getMessage());
	        }
	    }

	    public void editTeacherInDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть ім'я викладача для редагування:");
	        String name = DataInput.getString();
	        Teacher teacher = null;
	        for (Teacher t : dept.getTeachers()) {
	            if (t.getName().equalsIgnoreCase(name)) {
	                teacher = t;
	                break;
	            }
	        }
	        if (teacher == null) {
	            System.out.println("Викладача не знайдено.");
	            return;
	        }
	        System.out.println("Введіть нове ім'я викладача: ");
	        String newName = DataInput.getString();
	        System.out.println("Введіть новий предмет викладача: ");
	        String newSubject = DataInput.getString();
	        teacher.setName(newName);
	        teacher.setSubject(newSubject);
	        System.out.println("Інформацію про викладача оновлено.");
	    }

	    public void deleteTeacherFromDepartment() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть ім'я викладача для видалення:");
	        String name = DataInput.getString();
	        Teacher teacher = null;
	        for (Teacher t : dept.getTeachers()) {
	            if (t.getName().equalsIgnoreCase(name)) {
	                teacher = t;
	                break;
	            }
	        }
	        if (teacher == null) {
	            System.out.println("Викладача не знайдено.");
	            return;
	        }
	        dept.getTeachers().remove(teacher);
	        System.out.println("Викладача видалено.");
	    }

	    // ===================== ФУНКЦІЇ ПОШУКУ =====================
	    public void searchByName() throws IOException {
	        System.out.println("Введіть ім'я для пошуку (студента або викладача):");
	        String name = DataInput.getString();
	        boolean found = false;
	        for (Faculty f : faculties) {
	            for (Department d : f.getDepartments()) {
	                for (Student s : d.getStudents()) {
	                    if (s.getName().equalsIgnoreCase(name)) {
	                        System.out.println("Знайдено студента:\n" + s);
	                        found = true;
	                    }
	                }
	                for (Teacher t : d.getTeachers()) {
	                    if (t.getName().equalsIgnoreCase(name)) {
	                        System.out.println("Знайдено викладача:\n" + t);
	                        found = true;
	                    }
	                }
	            }
	        }
	        if (!found) {
	            System.out.println("За даним ім'ям нічого не знайдено.");
	        }
	    }

	    public void searchByCourse() throws IOException {
	        System.out.println("Введіть курс для пошуку студентів:");
	        int course = DataInput.getInt();
	        boolean found = false;
	        for (Faculty f : faculties) {
	            for (Department d : f.getDepartments()) {
	                for (Student s : d.getStudents()) {
	                    if (s.getCourse() == course) {
	                        System.out.println(s);
	                        found = true;
	                    }
	                }
	            }
	        }
	        if (!found) {
	            System.out.println("Студентів на даному курсі не знайдено.");
	        }
	    }

	    public void searchByGroup() throws IOException {
	        System.out.println("Введіть групу (спеціалізацію) для пошуку студентів:");
	        String group = DataInput.getString();
	        boolean found = false;
	        for (Faculty f : faculties) {
	            for (Department d : f.getDepartments()) {
	                for (Student s : d.getStudents()) {
	                    if (s.getSpec().equalsIgnoreCase(group)) { // використовуємо спеціалізацію як групу
	                        System.out.println(s);
	                        found = true;
	                    }
	                }
	            }
	        }
	        if (!found) {
	            System.out.println("Студентів даної групи не знайдено.");
	        }
	    }

	    // ===================== ФУНКЦІЇ ВИВОДУ ІНФОРМАЦІЇ =====================
	    public void displayAllStudentsSortedByCourse() {
	        List<Student> allStudents = new ArrayList<>();
	        for (Faculty f : faculties) {
	            for (Department d : f.getDepartments()) {
	                allStudents.addAll(d.getStudents());
	            }
	        }
	        if (allStudents.isEmpty()) {
	            System.out.println("Немає студентів для відображення.");
	            return;
	        }
	        allStudents.sort(Comparator.comparingInt(Student::getCourse));
	        System.out.println("Студенти, відсортовані за курсами:");
	        for (Student s : allStudents) {
	            System.out.println(s);
	        }
	    }

	    public void displayFacultyPersonsAlphabetically() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        List<String> persons = new ArrayList<>();
	        for (Department d : faculty.getDepartments()) {
	            for (Student s : d.getStudents()) {
	                persons.add(s.getName() + " (Студент)");
	            }
	            for (Teacher t : d.getTeachers()) {
	                persons.add(t.getName() + " (Викладач)");
	            }
	        }
	        if (persons.isEmpty()) {
	            System.out.println("На факультеті немає студентів або викладачів.");
	            return;
	        }
	        persons.sort(String.CASE_INSENSITIVE_ORDER);
	        System.out.println("Особи факультету, відсортовані за алфавітом:");
	        for (String person : persons) {
	            System.out.println(person);
	        }
	    }

	    public void displayDepartmentStudentsSortedByCourse() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        List<Student> students = new ArrayList<>(dept.getStudents());
	        if (students.isEmpty()) {
	            System.out.println("На кафедрі немає студентів.");
	            return;
	        }
	        students.sort(Comparator.comparingInt(Student::getCourse));
	        System.out.println("Студенти кафедри, відсортовані за курсами:");
	        for (Student s : students) {
	            System.out.println(s);
	        }
	    }

	    public void displayDepartmentPersonsAlphabetically() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        List<String> persons = new ArrayList<>();
	        for (Student s : dept.getStudents()) {
	            persons.add(s.getName() + " (Студент)");
	        }
	        for (Teacher t : dept.getTeachers()) {
	            persons.add(t.getName() + " (Викладач)");
	        }
	        if (persons.isEmpty()) {
	            System.out.println("На кафедрі немає студентів або викладачів.");
	            return;
	        }
	        persons.sort(String.CASE_INSENSITIVE_ORDER);
	        System.out.println("Особи кафедри, відсортовані за алфавітом:");
	        for (String person : persons) {
	            System.out.println(person);
	        }
	    }

	    public void displayDepartmentStudentsByCourse() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть курс для фільтрації:");
	        int course = DataInput.getInt();
	        List<Student> filtered = new ArrayList<>();
	        for (Student s : dept.getStudents()) {
	            if (s.getCourse() == course) {
	                filtered.add(s);
	            }
	        }
	        if (filtered.isEmpty()) {
	            System.out.println("На кафедрі немає студентів з вказаним курсом.");
	            return;
	        }
	        System.out.println("Студенти кафедри на курсі " + course + ":");
	        for (Student s : filtered) {
	            System.out.println(s);
	        }
	    }

	    public void displayDepartmentStudentsByCourseAlphabetically() throws IOException {
	        System.out.println("Введіть назву факультету:");
	        String facultyName = DataInput.getString();
	        Faculty faculty = findFacultyByName(facultyName);
	        if (faculty == null) {
	            System.out.println("Факультет не знайдено.");
	            return;
	        }
	        System.out.println("Введіть назву кафедри:");
	        String deptName = DataInput.getString();
	        Department dept = findDepartmentByName(faculty, deptName);
	        if (dept == null) {
	            System.out.println("Кафедру не знайдено.");
	            return;
	        }
	        System.out.println("Введіть курс для фільтрації:");
	        int course = DataInput.getInt();
	        List<Student> filtered = new ArrayList<>();
	        for (Student s : dept.getStudents()) {
	            if (s.getCourse() == course) {
	                filtered.add(s);
	            }
	        }
	        if (filtered.isEmpty()) {
	            System.out.println("На кафедрі немає студентів з вказаним курсом.");
	            return;
	        }
	        filtered.sort(Comparator.comparing(s -> s.getName().toLowerCase()));
	        System.out.println("Студенти кафедри на курсі " + course + " (відсортовані за алфавітом):");
	        for (Student s : filtered) {
	            System.out.println(s);
	        }
	    }

	    // ===================== МЕНЮ =====================
	    public void mainMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Головне меню ---
	                    1. Управління факультетами
	                    2. Управління кафедрами
	                    3. Управління студентами та викладачами
	                    4. Пошук
	                    5. Вивід інформації
	                    6. Вихід
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> facultyMenu();
	                case 2 -> departmentMenu();
	                case 3 -> personMenu();
	                case 4 -> searchMenu();
	                case 5 -> displayMenu();
	                case 6 -> {
	                    System.out.println("Вихід з програми.");
	                    return;
	                }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    private void facultyMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Меню управління факультетами ---
	                    1. Створити факультет
	                    2. Редагувати факультет
	                    3. Видалити факультет
	                    4. Показати всі факультети
	                    5. Назад
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> createFaculty();
	                case 2 -> editFaculty();
	                case 3 -> deleteFaculty();
	                case 4 -> listFaculties();
	                case 5 -> { return; }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    private void departmentMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Меню управління кафедрами ---
	                    1. Створити кафедру
	                    2. Редагувати кафедру
	                    3. Видалити кафедру
	                    4. Назад
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> createDepartment();
	                case 2 -> editDepartment();
	                case 3 -> deleteDepartment();
	                case 4 -> { return; }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    private void personMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Меню управління студентами та викладачами ---
	                    1. Додати студента
	                    2. Редагувати студента
	                    3. Видалити студента
	                    4. Додати викладача
	                    5. Редагувати викладача
	                    6. Видалити викладача
	                    7. Назад
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> addStudentToDepartment();
	                case 2 -> editStudentInDepartment();
	                case 3 -> deleteStudentFromDepartment();
	                case 4 -> addTeacherToDepartment();
	                case 5 -> editTeacherInDepartment();
	                case 6 -> deleteTeacherFromDepartment();
	                case 7 -> { return; }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    private void searchMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Меню пошуку ---
	                    1. Пошук за ім'ям (ПІБ)
	                    2. Пошук студентів за курсом
	                    3. Пошук студентів за групою
	                    4. Назад
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> searchByName();
	                case 2 -> searchByCourse();
	                case 3 -> searchByGroup();
	                case 4 -> { return; }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    private void displayMenu() throws IOException {
	        while (true) {
	            System.out.println("""
	                    --- Меню виводу інформації ---
	                    1. Вивести всіх студентів, відсортованих за курсами
	                    2. Вивести всіх студентів та викладачів факультету, відсортованих за алфавітом
	                    3. Вивести всіх студентів кафедри, відсортованих за курсами
	                    4. Вивести всіх студентів та викладачів кафедри, відсортованих за алфавітом
	                    5. Вивести всіх студентів кафедри вказаного курсу
	                    6. Вивести всіх студентів кафедри вказаного курсу, відсортованих за алфавітом
	                    7. Назад
	                    Оберіть дію:""");
	            int choice = DataInput.getInt();
	            switch (choice) {
	                case 1 -> displayAllStudentsSortedByCourse();
	                case 2 -> displayFacultyPersonsAlphabetically();
	                case 3 -> displayDepartmentStudentsSortedByCourse();
	                case 4 -> displayDepartmentPersonsAlphabetically();
	                case 5 -> displayDepartmentStudentsByCourse();
	                case 6 -> displayDepartmentStudentsByCourseAlphabetically();
	                case 7 -> { return; }
	                default -> System.out.println("Невірний вибір, спробуйте знову.");
	            }
	        }
	    }

	    public static void main(String[] args) {
	        UniversityManagement um = new UniversityManagement();
	        try {
	            um.mainMenu();
	        } catch (IOException e) {
	            System.out.println("Помилка введення/виводу: " + e.getMessage());
	        }
	    }
	}

