package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tamodule.TaModule;

//@@author Gernene
/**
 * An UI component that displays information of a {@code TaModule}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TaModule module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label code;
    @FXML
    private Label academicYear;

    /**
     * Creates a {@code ModuleCard} with the given {@code TaModule} and index to display.
     *
     * @param module TaModule to display.
     * @param displayedIndex Index of the module in the displayed list.
     */
    public ModuleCard(TaModule module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + "");
        code.setText(module.getModuleCode().value);
        name.setText(module.getModuleName().value);
        academicYear.setText(module.getAcademicYear().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
