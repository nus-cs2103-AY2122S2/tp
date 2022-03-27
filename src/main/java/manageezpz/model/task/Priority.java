package manageezpz.model.task;

public enum Priority {

    HIGH(0), LOW(2), MEDIUM(1), NONE(3);
    private final int value;

    /**
    * Constructor to initialize an instance of TaskType enumeration with abbreviation field.
    * @param value
    */
    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
