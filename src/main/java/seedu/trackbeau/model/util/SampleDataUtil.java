package seedu.trackbeau.model.util;

import static seedu.trackbeau.logic.parser.booking.AddBookingCommandParser.EMPTY_FEEDBACK_TYPE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.booking.Feedback;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.RegistrationDate;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.service.ServiceName;
import seedu.trackbeau.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackBeau} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[]{
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new SkinType("Oily"), new HairType("Dry"),
                getTagSet("Jason"), getTagSet("Organic Radiance Facial", "Student Facial"), getTagSet("Nickel"),
                new Birthdate("01-12-2001"), new RegistrationDate("23-01-2022")),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new SkinType("Sensitive"), new HairType("Oily"),
                getTagSet("Jane", "Lee"), getTagSet("Eyebrow Shaping"), getTagSet("Steroids"),
                new Birthdate("01-12-1996"), new RegistrationDate("23-02-2022")),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new SkinType("Combination"), new HairType("Dry"),
                getTagSet("Lee"),
                    getTagSet("Lymphatic Flow Facial"),
                    getTagSet("Salicylic acid", "Aluminium compounds"),
                    new Birthdate("05-10-1960"), new RegistrationDate("23-02-2022")),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new SkinType("Oily"), new HairType("Dry"),
                    getTagSet("Jason"),
                    getTagSet("Anti-Aging Eye Treatment"),
                    getTagSet("Cobalt"),
                    new Birthdate("26-05-2000"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new SkinType("Sensitive"), new HairType("Oily"),
                    getTagSet("Jolene", "Lee"),
                    getTagSet("Anti-Aging Eye Treatment"),
                    getTagSet("Fragrances"),
                    new Birthdate("23-05-2001"), new RegistrationDate("23-03-2022")),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new SkinType("Combination"), new HairType("Dry"),
                getTagSet("Lee"), getTagSet("Anti-Aging Eye Treatment"), getTagSet("Cocoa butter"),
                new Birthdate("01-08-2001"), new RegistrationDate("23-03-2022")),
        };
    }

    public static Service[] getSampleServices() {
        return new Service[]{
            new Service(new ServiceName("Organic Radiance Facial"), new Price(168.00), new Duration(60)),
            new Service(new ServiceName("Bio Ageless Facial"), new Price(268.00), new Duration(90)),
            new Service(new ServiceName("Lymphatic Flow Facial"), new Price(198.00), new Duration(90)),
            new Service(new ServiceName("Student Facial"), new Price(30.00), new Duration(45)),
            new Service(new ServiceName("Anti-Aging Eye Treatment"), new Price(58.00), new Duration(30)),
            new Service(new ServiceName("Eyebrow Shaping"), new Price(15.00), new Duration(30))
        };
    }

    public static Booking[] getSampleBookings() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTimeNow = LocalDateTime.now();

        return new Booking[] {
            new Booking(getSampleCustomers()[0], getSampleServices()[0],
                new BookingDateTime(dateTimeNow.format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[0], getSampleServices()[3],
                new BookingDateTime(dateTimeNow.plusDays(1).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[1], getSampleServices()[1],
                new BookingDateTime(dateTimeNow.plusDays(2).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[2], getSampleServices()[2],
                new BookingDateTime(dateTimeNow.plusDays(3).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[0], getSampleServices()[2],
                new BookingDateTime(dateTimeNow.minusDays(1).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[1], getSampleServices()[2],
                new BookingDateTime(dateTimeNow.minusDays(2).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE)),
            new Booking(getSampleCustomers()[2], getSampleServices()[2],
                new BookingDateTime(dateTimeNow.minusDays(3).format(formatter)), new Feedback(EMPTY_FEEDBACK_TYPE))
        };
    }

    public static ReadOnlyTrackBeau getSampleTrackBeau() {
        TrackBeau sampleTb = new TrackBeau();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleTb.addCustomer(sampleCustomer);
        }

        for (Service sampleService : getSampleServices()) {
            sampleTb.addService(sampleService);
        }
        for (Booking sampleBooking : getSampleBookings()) {
            sampleTb.addBooking(sampleBooking);
        }
        return sampleTb;
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
