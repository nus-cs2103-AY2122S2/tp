package seedu.address.model.consultation;

/**
 * Represents a Person's Notes in the address book.
 */
public class TestsTakenAndResults {

    public static final String MESSAGE_CONSTRAINTS =
            "Test Taken And Results can be blank";

    public String testsTakenAndResults;

    /**
     * Constructs a {@code Name}.
     *
     */
    public TestsTakenAndResults(String testsTakenAndResults) {
        this.testsTakenAndResults = testsTakenAndResults;
    }


    @Override
    public String toString() {
        return testsTakenAndResults;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestsTakenAndResults // instanceof handles nulls
                && testsTakenAndResults.equals(((TestsTakenAndResults) other).testsTakenAndResults)); // state check
    }

    @Override
    public int hashCode() {
        return testsTakenAndResults.hashCode();
    }

}
