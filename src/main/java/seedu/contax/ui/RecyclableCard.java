package seedu.contax.ui;

import javafx.scene.Node;

public interface RecyclableCard<T> {
    /**
     * Updates the information being displayed by this card to that of the supplied model and the supplied
     * new displayed index.
     *
     * @param model The new model to display in this card.
     * @param displayedIndex The new index that should be displayed by this card.
     * @return A JavaFX UI Node that can be added to some parent Node.
     */
    Node updateModel(T model, int displayedIndex);
}
