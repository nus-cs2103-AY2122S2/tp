package seedu.address.ui;

import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class DoublePersonCard extends UiPart<Region> {

    private static final String FXML = "DoublePersonCard.fxml";

    private PersonCard seller;
    private PersonCard buyer;

    /**
     * Creates a {@code DoublePersonCard} with the given two persons.
     */
    public DoublePersonCard(Person seller, Person buyer, int index) {
        super(FXML);
        this.seller = new PersonCard(seller, index);
        this.buyer = new PersonCard(buyer, index);
    }

}
