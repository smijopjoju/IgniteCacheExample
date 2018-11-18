package my.apache.ignite.examples.model;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

public class EmployeeKey {

	/** ID. */
    private int id;

    /** Organization ID. */
    @AffinityKeyMapped
    private int organizationId;

    /**
     * Required for binary deserialization.
     */
    public EmployeeKey() {
        // No-op.
    }

    /**
     * @param id ID.
     * @param organizationId Organization ID.
     */
    public EmployeeKey(int id, int organizationId) {
        this.id = id;
        this.organizationId = organizationId;
    }

    /**
     * @return ID.
     */
    public int id() {
        return id;
    }

    /**
     * @return Organization ID.
     */
    public int organizationId() {
        return organizationId;
    }

    /** {@inheritDoc} */
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EmployeeKey key = (EmployeeKey)o;

        return id == key.id && organizationId == key.organizationId;
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        int res = id;

        res = 31 * res + organizationId;

        return res;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return "EmployeeKey [id=" + id +
            ", organizationId=" + organizationId + ']';
    }
}
