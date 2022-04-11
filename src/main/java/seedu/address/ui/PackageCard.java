package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.InsurancePackage;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PackageCard extends UiPart<Region> {

    private static final String FXML = "PackageListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final InsurancePackage insurancePackage;

    @FXML
    private HBox cardPane;
    @FXML
    private Label packageName;
    @FXML
    private Label packageDesc;

    /**
     * Creates a {@code PackageCard} with the given {@code InsurancePackage}
     */
    public PackageCard(InsurancePackage insurancePackage) {
        super(FXML);
        this.insurancePackage = insurancePackage;
        packageName.setText(insurancePackage.getPackageName());
        packageDesc.setText(insurancePackage.getPackageDescription());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PackageCard card = (PackageCard) other;
        return insurancePackage.equals(card.insurancePackage);
    }
}
