package seedu.address.ui;

import java.util.StringJoiner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

public class DoublePersonCard extends UiPart<Region> {

    private static final String FXML = "DoublePersonCard.fxml";

    private Person seller;
    private Person buyer;

    @FXML
    private Label name1;
    @FXML
    private Label phone1;
    @FXML
    private Label address1;
    @FXML
    private Label email1;
    @FXML
    private Label property1;
    @FXML
    private Label preference1;
    @FXML
    private Label favourite1;
    @FXML
    private Label tagBuyer1;
    @FXML
    private Label tagSeller1;

    @FXML
    private Label name2;
    @FXML
    private Label phone2;
    @FXML
    private Label address2;
    @FXML
    private Label email2;
    @FXML
    private Label property2;
    @FXML
    private Label preference2;
    @FXML
    private Label favourite2;
    @FXML
    private Label tagBuyer2;
    @FXML
    private Label tagSeller2;

    /**
     * Creates a {@code DoublePersonCard} with the given two persons.
     */
    public DoublePersonCard(Person seller, Person buyer) {
        super(FXML);

        this.seller = seller;
        this.buyer = buyer;

        setPersonToCard(seller, name1, phone1, address1, email1,
                property1, preference1, favourite1, tagBuyer1, tagSeller1);
        setPersonToCard(buyer, name2, phone2, address2, email2,
                property2, preference2, favourite2, tagBuyer2, tagSeller2);
    }

    private void setPersonToCard(Person person, Label name, Label phone, Label address, Label email,
                                 Label property, Label preference, Label favourite, Label tagBuyer, Label tagSeller) {

        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        if (person.getUserType().isBuyer()) {
            tagBuyer.setText(person.getUserType().value);
            tagSeller.setVisible(false);
            tagSeller.setManaged(false);
        } else {
            tagSeller.setText(person.getUserType().value);
            tagBuyer.setVisible(false);
            tagBuyer.setManaged(false);
        }

        if (!person.getFavourite().isUnfavourited()) {
            favourite.setText(person.getFavourite().toString());
        } else {
            favourite.setManaged(false);
        }

        if (person.getProperties().isEmpty()) {
            property.setManaged(false);
        } else {
            StringJoiner propertyJoiner = new StringJoiner("\n");
            person.getProperties().stream().map(Property::toString).forEach(propertyJoiner::add);
            property.setText(propertyJoiner.toString());
        }

        if (person.getPreference().isPresent()) {
            preference.setText(person.getPreference().get().toString());
        } else {
            preference.setManaged(false);
        }
    }
}
