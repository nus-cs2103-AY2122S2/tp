package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static woofareyou.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE_TIME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static woofareyou.logic.parser.CliSyntax.PREFIX_CHARGE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_CHARGE_MONTH_YEAR;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DATE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DIET;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static woofareyou.logic.parser.CliSyntax.PREFIX_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PHONE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PICKUP;
import static woofareyou.logic.parser.CliSyntax.PREFIX_TAG;
import static woofareyou.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.AbsentAttendanceCommand.AbsentAttendanceDescriptor;
import woofareyou.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.model.Model;
import woofareyou.model.PetBook;
import woofareyou.model.pet.NameContainsKeywordsPredicate;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.AbsentAttendanceDescriptorBuilder;
import woofareyou.testutil.EditPetDescriptorBuilder;
import woofareyou.testutil.PresentAttendanceDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_BOB_WITH_SPACES = "Bob    Choo";
    public static final String VALID_PHONE_AMY = "81234567";
    public static final String VALID_PHONE_BOB = "61234567";
    public static final String VALID_OWNER_NAME_AMY = "Sarah Lee";
    public static final String VALID_OWNER_NAME_BOB = "Bob Lee";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "123 Bobby Street";
    public static final String VALID_ADDRESS_BOBA = "123, Jurong West Ave 6, #08-111";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friends";
    public static final String VALID_DIET_AMY = "No meat for Amy for one month";
    public static final String VALID_DIET_BOB = "No dietary restrictions";
    public static final String VALID_CHARGEAMT_AMY = "200.50";
    public static final String VALID_CHARGEAMT_BOB = "400.50";
    public static final String VALID_CHARGEDATE_AMY = "03-2022";
    public static final String VALID_CHARGEDATE_BOB = "03-2021";
    public static final String INVALID_CHARGEDATE_AMY = "22-22";
    public static final String INVALID_CHARGEAMT_AMY = "200.509";
    public static final String INVALID_DIET_AMY = "&&&&";
    public static final String VALID_CHARGE_AMY = " " + PREFIX_CHARGE_MONTH_YEAR + VALID_CHARGEDATE_AMY
            + " " + PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_NO_CHARGE_DATE = " " + PREFIX_CHARGE_MONTH_YEAR
            + " " + PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_NO_CHARGE_AMOUNT = " " + PREFIX_CHARGE_MONTH_YEAR
            + VALID_CHARGEDATE_AMY + " " + PREFIX_CHARGE;
    public static final String INVALID_CHARGE_AMY_INVALID_CHARGE_DATE = " " + PREFIX_CHARGE_MONTH_YEAR
            + INVALID_CHARGEDATE_AMY + " " + PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_INVALID_CHARGE_AMOUNT = " " + PREFIX_CHARGE_MONTH_YEAR
            + VALID_CHARGEDATE_AMY + " " + PREFIX_CHARGE + INVALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_INVALID_ARGS = " " + PREFIX_CHARGE_MONTH_YEAR
            + INVALID_CHARGEDATE_AMY + " " + PREFIX_CHARGE + INVALID_CHARGEAMT_AMY;
    public static final String INVALID_APPT_DATE_AMY = "27-03-2022";
    public static final String VALID_ATTENDANCE_DATE_AMY = "27-03-2022";
    public static final String VALID_ATTENDANCE_DATE_BOB = "28-03-2022";
    public static final String VALID_ATTENDANCE_DATE_CHARLIE = "27-03-2022";
    public static final String VALID_APPT_DATE_AMY = "27-03-2024";
    public static final String VALID_APPT_TIME_AMY = "12:00";
    public static final String VALID_PICKUP_TIME_AMY = "09:00";
    public static final String VALID_PICKUP_TIME_BOB = "10:00";
    public static final String VALID_DROPOFF_TIME_AMY = "17:30";
    public static final String VALID_DROPOFF_TIME_BOB = "18:30";




    public static final String APPT_DESC_AMY = " " + PREFIX_APPOINTMENT_DATE_TIME
            + VALID_APPT_DATE_AMY + " " + VALID_APPT_TIME_AMY
            + " " + PREFIX_APPOINTMENT_LOCATION + VALID_ADDRESS_AMY;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DIET_DESC_AMY = " " + PREFIX_DIET + VALID_DIET_AMY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String OWNER_NAME_DESC_AMY = " " + PREFIX_OWNER_NAME + VALID_OWNER_NAME_AMY;
    public static final String OWNER_NAME_DESC_BOB = " " + PREFIX_OWNER_NAME + VALID_OWNER_NAME_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String ATTENDANCE_DATE_DESC_AMY = " " + PREFIX_DATE + VALID_ATTENDANCE_DATE_AMY;
    public static final String ATTENDANCE_DATE_DESC_BOB = " " + PREFIX_DATE + VALID_ATTENDANCE_DATE_BOB;
    public static final String ATTENDANCE_DATE_DESC_CHARLIE = " " + PREFIX_DATE + VALID_ATTENDANCE_DATE_CHARLIE;
    public static final String PICKUP_TIME_DESC_AMY = " " + PREFIX_PICKUP + VALID_PICKUP_TIME_AMY;
    public static final String PICKUP_TIME_DESC_BOB = " " + PREFIX_PICKUP + VALID_PICKUP_TIME_BOB;

    public static final String DROPOFF_TIME_DESC_AMY = " " + PREFIX_DROPOFF + VALID_DROPOFF_TIME_AMY;
    public static final String DROPOFF_TIME_DESC_BOB = " " + PREFIX_DROPOFF + VALID_DROPOFF_TIME_BOB;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_DIET_DESC = " " + PREFIX_DIET + INVALID_DIET_AMY; // special characters
    public static final String INVALID_APPT_DESC = " " + PREFIX_APPOINTMENT_DATE_TIME // past date
            + INVALID_APPT_DATE_AMY + " " + VALID_PICKUP_TIME_AMY
            + " " + PREFIX_APPOINTMENT_LOCATION + VALID_ADDRESS_AMY;
    // not allowed

    // '!' not allowed in ownerNames
    public static final String INVALID_OWNER_NAME_DESC = " " + PREFIX_OWNER_NAME + "bob!yahoo";
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPetDescriptor DESC_AMY;
    public static final EditCommand.EditPetDescriptor DESC_BOB;

    public static final PresentAttendanceDescriptor PRESENT_DESC_WITH_TRANSPORT_AMY;
    public static final PresentAttendanceDescriptor PRESENT_DESC_WITH_TRANSPORT_BOB;
    public static final PresentAttendanceDescriptor PRESENT_DESC_WITHOUT_TRANSPORT_CHARLIE;

    public static final AbsentAttendanceDescriptor ABSENT_DESC_AMY;
    public static final AbsentAttendanceDescriptor ABSENT_DESC_BOB;
    public static final AbsentAttendanceDescriptor ABSENT_DESC_CHARLIE;

    static {
        DESC_AMY = new EditPetDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withOwnerName(VALID_OWNER_NAME_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTag(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withOwnerName(VALID_OWNER_NAME_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTag(VALID_TAG_HUSBAND).build();
        PRESENT_DESC_WITH_TRANSPORT_AMY = new PresentAttendanceDescriptorBuilder()
            .withDate("2022-03-27").withPickUpTime("09:00").withDropOffTime("17:30")
            .build();
        PRESENT_DESC_WITH_TRANSPORT_BOB = new PresentAttendanceDescriptorBuilder()
            .withDate("2022-03-28").withPickUpTime("10:00").withDropOffTime("18:30")
            .build();
        PRESENT_DESC_WITHOUT_TRANSPORT_CHARLIE = new PresentAttendanceDescriptorBuilder()
            .withDate("2022-03-27")
            .build();
        ABSENT_DESC_AMY = new AbsentAttendanceDescriptorBuilder()
            .withDate("2022-03-27")
            .build();
        ABSENT_DESC_BOB = new AbsentAttendanceDescriptorBuilder()
            .withDate("2022-03-28")
            .build();
        ABSENT_DESC_CHARLIE = new AbsentAttendanceDescriptorBuilder()
            .withDate("2022-03-27")
            .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the pet book, filtered pet list and selected pet in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PetBook expectedPetBook = new PetBook(actualModel.getPetBook());
        List<Pet> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPetList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPetBook, actualModel.getPetBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPetList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the pet at the given {@code targetIndex} in the
     * {@code model}'s pet book.
     */
    public static void showPetAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPetList().size());

        Pet pet = model.getFilteredPetList().get(targetIndex.getZeroBased());
        final String[] splitName = pet.getName().fullName.split("\\s+");
        model.updateFilteredPetList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPetList().size());
    }

}
