package my.apache.ignite.examples.model;

import java.util.Collection;

public class Employee {

	/** Name. */
    private String name;

    /** Salary. */
    private long salary;

    /** Address. */
    private Address addr;

    /** Departments. */
    private Collection<String> departments;

    /**
     * Required for binary deserialization.
     */
    public Employee() {
        // No-op.
    }

    /**
     * @param name Name.
     * @param salary Salary.
     * @param addr Address.
     * @param departments Departments.
     */
    public Employee(String name, long salary, Address addr, Collection<String> departments) {
        this.name = name;
        this.salary = salary;
        this.addr = addr;
        this.departments = departments;
    }

    /**
     * @return Name.
     */
    public String name() {
        return name;
    }

    /**
     * @return Salary.
     */
    public long salary() {
        return salary;
    }

    /**
     * @return Address.
     */
    public Address address() {
        return addr;
    }

    /**
     * @return Departments.
     */
    public Collection<String> departments() {
        return departments;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return "Employee [name=" + name +
            ", salary=" + salary +
            ", address=" + addr +
            ", departments=" + departments + ']';
    }
	
}
