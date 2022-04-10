package seedu.address.testutil;

import seedu.address.model.InsuranceBook;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;

/**
 * A utility class to help with building Insurancebook objects.
 * Example usage: <br>
 * {@code InsuranceBook ab = new InsuranceBookBuilder().withInsurance("John", "Doe").build();}
 */
public class InsuranceBuilder {

    public static final String DEFAULT_DESCRIPTION = "Health";
    public static final String DEFAULT_DATETIME = "123";
    private Title title;
    private Price price;

    private InsuranceBook insurance;

    /**
     * Creates a {@code InsuranceBuilder} with the default details.
     */
    public InsuranceBuilder() {
        title = new Title(DEFAULT_DESCRIPTION);
        price = new Price(DEFAULT_DATETIME);
    }

    public InsuranceBuilder(InsuranceBook insurance) {
        this.insurance = insurance;
    }

    /**
     * Initializes the InsuranceBuilder with the data of {@code insuranceToCopy}.
     */
    public InsuranceBuilder(Insurance insuranceToCopy) {
        title = insuranceToCopy.getTitle();
        price = insuranceToCopy.getPrice();
    }

    /**
     * Sets the {@code Title} of the {@code Insurance} that we are building.
     */
    public InsuranceBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Insurance} that we are building.
     */
    public InsuranceBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Insurance build() {
        return new Insurance(title, price);
    }
}
