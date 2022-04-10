package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InsuranceBook;
import seedu.address.model.insurance.Insurance;

/**
 * A utility class containing a list of {@code Insurance} objects to be used in tests.
 */
public class TypicalInsurances {
    public static final Insurance INSURANCE1 = new InsuranceBuilder()
            .withTitle("Health")
            .withPrice("123").build();

    public static final Insurance INSURANCE2 = new InsuranceBuilder()
            .withTitle("Car")
            .withPrice("111").build();

    public static final Insurance INSURANCE3 = new InsuranceBuilder()
            .withTitle("House")
            .withPrice("222").build();

    private TypicalInsurances() {} // prevents instantiation

    /**
     * Returns an {@code InsuranceBook} with all the typical insurances.
     */
    public static InsuranceBook getTypicalInsuranceBook() {
        InsuranceBook ib = new InsuranceBook();
        for (Insurance insurance : getTypicalInsurances()) {
            ib.addInsurance(insurance);
        }
        return ib;
    }

    public static List<Insurance> getTypicalInsurances() {
        return new ArrayList<>(Arrays.asList(INSURANCE1, INSURANCE2, INSURANCE3));
    }
}
