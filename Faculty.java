

	public class Faculty {

	 private String name;
	    private List<Department> departments;

	    public Faculty(String name) {
	        this.name = name;
	        this.departments = new ArrayList<>();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<Department> getDepartments() {
	        return departments;
	    }

	    public void addDepartment(Department dept) {
	        departments.add(dept);
	    }

	    public void removeDepartment(Department dept) {
	        departments.remove(dept);
	    }

	    @Override
	    public String toString() {
	        return "Факультет: " + name;
	    }
	}
