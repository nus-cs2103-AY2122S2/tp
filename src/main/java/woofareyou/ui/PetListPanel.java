package woofareyou.ui;

import static woofareyou.commons.util.GuiUtil.isEvenIndexCard;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import woofareyou.commons.core.LogsCenter;
import woofareyou.model.pet.Pet;

/**
 * Panel containing the list of pets.
 */
public class PetListPanel extends UiPart<Region> {
    private static final String FXML = "PetListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PetListPanel.class);

    @FXML
    private ListView<Pet> petListView;

    /**
     * Creates a {@code PetListPanel} with the given {@code ObservableList}.
     */
    public PetListPanel(ObservableList<Pet> petList) {
        super(FXML);
        petListView.setItems(petList);
        petListView.setCellFactory(listView -> new PetListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pet} using a {@code PetCard}.
     */
    class PetListViewCell extends ListCell<Pet> {
        @Override
        protected void updateItem(Pet pet, boolean empty) {
            super.updateItem(pet, empty);

            if (empty || pet == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(createPetCard(pet).getRoot());
            }
        }

        private PetCard createPetCard(Pet pet) {
            int currentCellIndex = getIndex();
            PetCard petCard = new PetCard(pet, currentCellIndex + 1);

            if (isEvenIndexCard(currentCellIndex)) {
                petCard.setColour("#744D26");
            } else {
                petCard.setColour("#C4A484");
            }

            return petCard;
        }
    }

}
