package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BuyerAddressBook;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.buyer.Buyer;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "buyerAddressbook")
public class JsonSerializableBuyerAddressBook {

    public static final String MESSAGE_DUPLICATE_BUYER = "buyer list contains duplicate buyer(s).";

    private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given clients.
     */
    @JsonCreator
    public JsonSerializableBuyerAddressBook(@JsonProperty("buyers") List<JsonAdaptedBuyer> buyers) {
        this.buyers.addAll(buyers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBuyerAddressBook}.
     */
    public JsonSerializableBuyerAddressBook(ReadOnlyBuyerAddressBook source) {
        buyers.addAll(source.getBuyerList().stream().map(JsonAdaptedBuyer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BuyerAddressBook toModelType() throws IllegalValueException {
        BuyerAddressBook addressBook = new BuyerAddressBook();
        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            if (addressBook.hasBuyer(buyer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BUYER);
            }
            addressBook.addBuyer(buyer);
        }
        return addressBook;
    }

}
