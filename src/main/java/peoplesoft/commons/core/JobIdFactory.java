package peoplesoft.commons.core;

/**
 * Class to generate unique {@code JobIds}.
 */
public class JobIdFactory {
    private static int id = 0;

    /**
     * Returns a unique {@code JobId}.
     *
     * @return JobId.
     */
    // TODO: currently missing functionality with serdes
    public static String nextId() {
        return String.valueOf(++id);
    }

    /**
     * Sets the current id.
     *
     * @param id To set.
     */
    public static void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("id should not be negative");
        }
        JobIdFactory.id = id;
    }

    /**
     * Returns the current id.
     *
     * @return Id.
     */
    public static int getId() {
        return id;
    }
}
