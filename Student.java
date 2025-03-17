class Student{
	private String name;
    private String faculty;
    private int course;
    private String spec;
    private double avgMark;

    public Student(String name, String faculty, int course, String spec, double avgMark) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.spec = spec;
        this.avgMark = avgMark;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public String getSpec() {
        return spec;
    }

    public double getAvgMark() {
        return avgMark;
    }

    @Override
    public String toString() {
        return String.format("""
                __
                Імя: %s
                Факультет: %s
                Курс: %d
                Спеціальність: %s
                Сeредній бал: %.2f
                __
                """, name, faculty, course, spec, avgMark);
    }
}

