package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.recipe.Recipe;

/**
 * Panel containing the list of recipes.
 */
public class RecipeListPanel extends UiPart<Region> {
    private static final String FXML = "RecipeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecipeListPanel.class);

    @FXML
    private ListView<Recipe> recipeListView;

    /**
     * Creates a {@code RecipeListPanel} with the given {@code ObservableList}.
     */
    public RecipeListPanel(ObservableList<Recipe> recipeList) {
        super(FXML);
        recipeListView.setItems(recipeList);
        recipeListView.setCellFactory(listView -> new RecipeListViewCell());
        recipeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setRecipe();
            }
        });
    }

    /**
     * Returns the {@code ReadOnlyObjectProperty} of the current selected recipe in the list.
     *
     * @return the {@code ReadOnlyObjectProperty} of the current selected recipe.
     */
    public ReadOnlyObjectProperty<Recipe> selectedRecipeProperty() {
        return recipeListView.getSelectionModel().selectedItemProperty();
    }

    /**
     * Sets internal selectedRecipeProperty to the selected recipe in the list.
     */
    private void setRecipe() {
        Recipe recipe = selectedRecipeProperty().get();
        if (recipe != null) {
            // workaround to trigger selection change
            recipeListView.getSelectionModel().select(null);
            recipeListView.getSelectionModel().select(recipe);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Recipe} using a {@code RecipeCard}.
     */
    class RecipeListViewCell extends ListCell<Recipe> {
        @Override
        protected void updateItem(Recipe recipe, boolean empty) {
            super.updateItem(recipe, empty);

            if (empty || recipe == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecipeCard(recipe, getIndex() + 1).getRoot());
            }
        }
    }

}
