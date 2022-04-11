package seedu.address.testutil;

import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;

/**
 * A utility class to help with building Driver objects.
 */
public class DriverBuilder {

    public static final String DEFAULT_NAME = "Aron Ree";
    public static final String DEFAULT_PHONE = "85353355";

    private NameDriver nameDriver;
    private PhoneDriver phoneDriver;
    private DriverStatus driverStatus;

    /**
     * Creates a {@code DriverBuilder} with the default details.
     */
    public DriverBuilder() {
        nameDriver = new NameDriver(DEFAULT_NAME);
        phoneDriver = new PhoneDriver(DEFAULT_PHONE);
        driverStatus = DriverStatus.FREE;
    }

    /**
     * Initializes the DriverBuilder with the data of {@code driverToCopy}.
     */
    public DriverBuilder(Driver driverToCopy) {
        nameDriver = driverToCopy.getName();
        phoneDriver = driverToCopy.getPhone();
    }

    /**
     * Sets the {@code NameDriver} of the {@code Driver} that we are building.
     */
    public DriverBuilder withName(String name) {
        this.nameDriver = new NameDriver(name);
        return this;
    }

    /**
     * Sets the {@code PhoneDriver} of the {@code Driver} that we are building.
     */
    public DriverBuilder withPhone(String phone) {
        this.phoneDriver = new PhoneDriver(phone);
        return this;
    }

    /**
     * Sets the {@code DriverStatus} of the {@code Driver} that we are building.
     */
    public DriverBuilder withStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
        return this;
    }

    public Driver build() {
        return new Driver(nameDriver, phoneDriver);
    }
}
