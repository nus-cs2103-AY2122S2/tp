package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;

public class TypicalApplicants {
    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withNric("S9920202A").withInterviewDate("2022-01-28").withDateApplied("2022-01-21")
            .withJob("1").withQualification("degree in Computing").withApplicationStatus("1")
            .withTags("friends").build();
    public static final Applicant BENSON = new ApplicantBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withDateApplied("2022-01-22").withNric("S9930303B").withInterviewDate("2022-01-29")
            .withJob("2").withQualification("degree in Computer Engineering").withApplicationStatus("3")
            .withTags("owesMoney", "friends").build();
    public static final Applicant CARL = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withDateApplied("2022-01-23").withNric("S9940404C").withInterviewDate("2022-01-30")
            .withJob("1").withQualification("degree in Economics").withApplicationStatus("1")
            .build();
    public static final Applicant DANIEL = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withDateApplied("2022-01-24").withNric("S9950505D").withInterviewDate("2022-01-31")
            .withJob("1").withQualification("degree in Business").withApplicationStatus("2")
            .withTags("friends").build();
    public static final Applicant ELLE = new ApplicantBuilder().withName("Elle Meyer").withPhone("94822241")
            .withEmail("werner@example.com").withAddress("michegan ave").withApplicationStatus("2")
            .withDateApplied("2022-01-25").withNric("S9960606E").withInterviewDate("2022-02-01")
            .withJob("2").withQualification("degree in Data Science").build();
    public static final Applicant FIONA = new ApplicantBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withAddress("little tokyo").withApplicationStatus("3")
            .withDateApplied("2022-01-26").withNric("S9970707F").withInterviewDate("2022-02-02")
            .withJob("1").withQualification("degree in Real Estate")
            .build();
    public static final Applicant GEORGE = new ApplicantBuilder().withName("George Best").withPhone("94824421")
            .withEmail("anna@example.com").withAddress("4th street").withApplicationStatus("0")
            .withDateApplied("2022-01-27").withNric("S9980808G").withInterviewDate("2022-02-03")
            .withJob("1").withQualification("degree in Political Science")
            .build();


    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new ApplicantBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Applicant BOB = new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    private TypicalApplicants() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical applicants.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Applicant applicant: getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        ab.setIdCount(9);
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
