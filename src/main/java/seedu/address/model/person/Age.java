package seedu.address.model.person;

/**
 * Represents a Person's age in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Age {
    private String age;
    public static final String MESSAGE_CONSTRAINTS = "Ages should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Age should be between 0 and 100 (inclusive).\n";

    /**
     * Constructs a {@code Age}.
     * Hardcoded, to be further developed.
     */
    public Age() {
        this.age = "23";
    }

    public Age(String age) {
        this.age = age;
    }

    public static boolean isValid(String ageString) {
        int age = -1;

        try {
            age = Integer.parseInt(ageString);
        } catch (Exception e) {
            return false;
        }

        if (age < 0 || age > 100) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.age + "y/o";
    }
}
