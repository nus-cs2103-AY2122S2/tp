package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.ReadOnlyBuyerAddressBook;
import seedu.address.model.ReadOnlySellerAddressBook;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.NullPropertyToBuy;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Appointment NO_APPOINTMENT = new Appointment("");
    public static final PropertyToBuy NULL_PROPERTY_TO_BUY = NullPropertyToBuy.getNullPropertyToBuy();
    public static final PropertyToSell NULL_PROPERTY_TO_SELL = NullPropertyToSell.getNullPropertyToSell();

    public static Client[] getSampleclients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"), new Phone("87438807"),
                NO_APPOINTMENT, getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), NO_APPOINTMENT,
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), NO_APPOINTMENT,
                getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), NO_APPOINTMENT,
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), NO_APPOINTMENT,
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), NO_APPOINTMENT,
                getTagSet("colleagues"))
        };
    }

    public static Seller[] getSampleSellers() {
        return new Seller[] {
            new Seller(new Name("Chok Hoe"), new Phone("12345678"),
            NO_APPOINTMENT, getTagSet("fav"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Yong Ler"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("friendly"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Jacky"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("funny"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Ian"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("talkative"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Joey"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("chef"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Justin"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("negotiator"), NULL_PROPERTY_TO_SELL),
            new Seller(new Name("Chee Kean"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("Inquisitive"), NULL_PROPERTY_TO_SELL)
        };
    }

    public static Buyer[] getSampleBuyers() {
        return new Buyer[] {
            new Buyer(new Name("Shi Hong"), new Phone("12345678"),
            NO_APPOINTMENT, getTagSet("smart"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Oviya"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("kind"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Zhongfu"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("cool"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Janald"), new Phone("21345678"),
                    NO_APPOINTMENT, getTagSet("cat"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Jun Hong"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("rich"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Jun Heng"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("big"), NULL_PROPERTY_TO_BUY),
            new Buyer(new Name("Dara"), new Phone("12345678"),
                    NO_APPOINTMENT, getTagSet("fav"), NULL_PROPERTY_TO_BUY)
        };
    }

    public static ReadOnlySellerAddressBook getSampleSellerAddressBook() {
        SellerAddressBook sampleSab = new SellerAddressBook();
        for (Seller sampleSeller : getSampleSellers()) {
            sampleSab.addSeller(sampleSeller);
        }
        return sampleSab;
    }

    public static ReadOnlyBuyerAddressBook getSampleBuyerAddressBook() {
        BuyerAddressBook sampleSab = new BuyerAddressBook();
        for (Buyer sampleBuyer : getSampleBuyers()) {
            sampleSab.addBuyer(sampleBuyer);
        }
        return sampleSab;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
