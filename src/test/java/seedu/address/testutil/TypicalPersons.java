package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    /*
    Multiple instances of withTaskList can be used for a Person, if there are multiple tasks to be added.
    Example: new PersonBuilder().withTaskList("task a", false).withTaskList("task b", true).build();
     */
    public static final Person ALICE = new PersonBuilder().withStudentId("A1000000Z").withName("Alice")
            .withModuleCode("CS2101").withPhone("10000000").withTelegramHandle("aliceee")
            .withEmail("aliceee@u.nus.edu").withTaskList("Task A", false).build();
    public static final Person BENSON = new PersonBuilder().withStudentId("A2000000Z").withName("Benson")
            .withModuleCode("CS2102").withPhone("20000000").withTelegramHandle("bensonnn")
            .withEmail("bensonnn@u.nus.edu").withTaskList("Task B", true).build();
    public static final Person CARL = new PersonBuilder().withStudentId("A3000000Z").withName("Carl")
            .withModuleCode("CS2103").withPhone("30000000").withTelegramHandle("carlll")
            .withEmail("carlll@u.nus.edu").build();
    public static final Person DANIEL = new PersonBuilder().withStudentId("A4000000Z").withName("Daniel")
            .withModuleCode("CS2104").withPhone("40000000").withTelegramHandle("danielll")
            .withEmail("danielll@u.nus.edu").build();
    public static final Person ELLE = new PersonBuilder().withStudentId("A5000000Z").withName("Elle")
            .withModuleCode("CS2105").withPhone("50000000").withTelegramHandle("elleee")
            .withEmail("elleee@u.nus.edu").build();
    public static final Person FIONA = new PersonBuilder().withStudentId("A6000000Z").withName("Fiona")
            .withModuleCode("CS2106").withPhone("60000000").withTelegramHandle("fionaaa")
            .withEmail("fionaaa@u.nus.edu").build();
    public static final Person GEORGE = new PersonBuilder().withStudentId("A7000000Z").withName("George")
            .withModuleCode("CS2107").withPhone("70000000").withTelegramHandle("georgeee")
            .withEmail("georgeee@u.nus.edu").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withStudentId("A8000000Z").withName("Hoon")
            .withModuleCode("CS2108").withPhone("80000000").withTelegramHandle("hoonnn")
            .withEmail("hoonnn@u.nus.edu").build();
    public static final Person IDA = new PersonBuilder().withStudentId("A9000000Z").withName("Ida")
            .withModuleCode("CS2109").withPhone("90000000").withTelegramHandle("idaaa")
            .withEmail("idaaa@u.nus.edu").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withStudentId(VALID_ID_AMY).withName(VALID_NAME_AMY)
            .withModuleCode(VALID_MODULE_CODE_AMY).withPhone(VALID_PHONE_AMY)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).withEmail(VALID_EMAIL_AMY).build();
    public static final Person BOB = new PersonBuilder().withStudentId(VALID_ID_BOB).withName(VALID_NAME_BOB)
            .withModuleCode(VALID_MODULE_CODE_BOB).withPhone(VALID_PHONE_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).withEmail(VALID_EMAIL_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
