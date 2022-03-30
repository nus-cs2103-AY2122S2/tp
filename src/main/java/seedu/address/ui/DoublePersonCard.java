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
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label property;
    @FXML
    private Label preference;
    @FXML
    private Label favourite;
    @FXML
    private Label tagBuyer;
    @FXML
    private Label tagSeller;

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

        // seller card
        name.setText(seller.getName().fullName);
        phone.setText(seller.getPhone().value);
        address.setText(seller.getAddress().value);
        email.setText(seller.getEmail().value);

        if (seller.getUserType().isBuyer()) {
            tagBuyer.setText(seller.getUserType().value);
            tagSeller.setVisible(false);
            tagSeller.setManaged(false);
        } else {
            tagSeller.setText(seller.getUserType().value);
            tagBuyer.setVisible(false);
            tagBuyer.setManaged(false);
        }

        if (!seller.getFavourite().isUnfavourited()) {
            favourite.setText(seller.getFavourite().toString());
        } else {
            favourite.setManaged(false);
        }

        if (seller.getProperties().isEmpty()) {
            property.setManaged(false);
        } else {
            StringJoiner propertyJoiner = new StringJoiner("\n");
            seller.getProperties().stream().map(Property::toString).forEach(propertyJoiner::add);
            property.setText(propertyJoiner.toString());
        }

        if (seller.getPreference().isPresent()) {
            preference.setText(seller.getPreference().get().toString());
        } else {
            preference.setManaged(false);
        }

        // buyer card
        name2.setText(buyer.getName().fullName);
        phone2.setText(buyer.getPhone().value);
        address2.setText(buyer.getAddress().value);
        email2.setText(buyer.getEmail().value);

        if (buyer.getUserType().isBuyer()) {
            tagBuyer2.setText(buyer.getUserType().value);
            tagSeller2.setVisible(false);
            tagSeller2.setManaged(false);
        } else {
            tagSeller2.setText(buyer.getUserType().value);
            tagBuyer2.setVisible(false);
            tagBuyer2.setManaged(false);
        }

        if (!buyer.getFavourite().isUnfavourited()) {
            favourite2.setText(buyer.getFavourite().toString());
        } else {
            favourite2.setManaged(false);
        }

        if (buyer.getProperties().isEmpty()) {
            property2.setManaged(false);
        } else {
            StringJoiner propertyJoiner = new StringJoiner("\n");
            buyer.getProperties().stream().map(Property::toString).forEach(propertyJoiner::add);
            property2.setText(propertyJoiner.toString());
        }

        if (buyer.getPreference().isPresent()) {
            preference2.setText(buyer.getPreference().get().toString());
        } else {
            preference2.setManaged(false);
        }
    }
}
