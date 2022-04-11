package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.recipe.Recipe;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

    private static final String FXML = "RecipeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Recipe recipe;
    public final int index;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label steps;
    @FXML
    private Label completionTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public RecipeCard(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        this.index = displayedIndex;

        name.setWrapText(true);
        name.setText(index + ". " + recipe.getName().fullName);
        steps.setText(String.valueOf(recipe.getSteps().size()) + " steps");
        completionTime.setText(String.valueOf(recipe.getCompletionTime().value) + " mins");
        recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @FXML
    private void copyName() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent recipeName = new ClipboardContent();
        recipeName.putString(recipe.getName().fullName);
        clipboard.setContent(recipeName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        RecipeCard card = (RecipeCard) other;
        return this.index == card.index
                && recipe.equals(card.recipe);
    }
}
