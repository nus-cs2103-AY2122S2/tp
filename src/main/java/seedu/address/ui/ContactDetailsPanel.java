package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

public class ContactDetailsPanel extends UiPart<Region> {
    private static final String FXML = "ContactDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactDetailsPanel.class);

    private final ParticularsCard particularsCard;
    private final NumbersCard numbersCard;
    private final EmailsCard emailsCard;
    private final AddressesCard addressesCard;

    @FXML
    private StackPane particularsCardPlaceholder;

    @FXML
    private StackPane numbersCardPlaceholder;

    @FXML
    private StackPane emailsCardPlaceholder;

    @FXML
    private StackPane addressesCardPlaceholder;

    private Person person;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ContactDetailsPanel(Person personToDisplay) {
        super(FXML);
        person = personToDisplay;
        particularsCard = new ParticularsCard(person.getName(), person.getPronouns(), person.getTags(),
                person.getJobTitle().orElse(null), person.getCompany().orElse(null));
        particularsCardPlaceholder.getChildren().add(particularsCard.getRoot());

        numbersCard = new NumbersCard(person.getNumbers());
        numbersCardPlaceholder.getChildren().add(numbersCard.getRoot());

        emailsCard = new EmailsCard(person.getEmails());
        emailsCardPlaceholder.getChildren().add(emailsCard.getRoot());

        addressesCard = new AddressesCard(person.getAddresses());
        addressesCardPlaceholder.getChildren().add(addressesCard.getRoot());
    }

    public Person getPerson() {
        return person;
    }
}

