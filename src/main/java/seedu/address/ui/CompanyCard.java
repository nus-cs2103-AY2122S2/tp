package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.entry.Company;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane archive;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        id.setWrapText(true);
        name.setText(company.getName().fullName);
        name.setWrapText(true);
        phone.setText(company.getPhone().value);
        phone.setWrapText(true);
        address.setText(company.getAddress().value);
        address.setWrapText(true);
        email.setText(company.getEmail().value);
        email.setWrapText(true);
        if (company.isArchived()) {
            archive.getChildren().add(new Label("ARCHIVED"));
        }
        company.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyCard)) {
            return false;
        }

        // state check
        CompanyCard card = (CompanyCard) other;
        return id.getText().equals(card.id.getText())
                && company.equals(card.company);
    }
}
