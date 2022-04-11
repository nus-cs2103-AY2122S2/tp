package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PF;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;
import static seedu.address.logic.commands.EditCommand.createEditedSchedule;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final LineupName EXISTING_LINEUP_NAME = new LineupBuilder().build().getLineupName();
    private static final LineupName VALID_LINEUP_NAME = new LineupName("Lakaka");
    private static final LineupName DUPLICATE_LINEUP_NAME = EXISTING_LINEUP_NAME;
    private static final Schedule VALID_SCHEDULE = new ScheduleBuilder().withScheduleName("final")
            .withScheduleDescription("sixers vs suns").withDateTime("01/06/2030 2030").build();
    private static final Schedule VALID_SCHEDULE_2 = new ScheduleBuilder().withScheduleName("nba final")
            .withScheduleDescription("sixers vs lakers").withDateTime("01/06/2030 2100").build();
    private static final ScheduleName VALID_SCHEDULE_NAME = VALID_SCHEDULE_2.getScheduleName();
    private static final ScheduleDescription VALID_SCHEDULE_DESCRIPTION = VALID_SCHEDULE_2.getScheduleDescription();

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addLineup(new LineupBuilder().build());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(BENSON.getName(), descriptor);
        editedPerson = createEditedPerson(BENSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        String res = editCommand.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        PersonBuilder personInList = new PersonBuilder(BENSON);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_PF).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_PF).build();
        EditCommand editCommand = new EditCommand(BENSON.getName(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        String res = editCommand.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() throws CommandException {
        EditCommand editCommand = new EditCommand(BENSON.getName(), new EditPersonDescriptor());
        Person editedPerson = new PersonBuilder(BENSON).build();

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);


        String res = editCommand.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        Person editedPerson = new PersonBuilder(BENSON).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(BENSON.getName(),
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        String res = editCommand.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        Person secondPerson = model.getFilteredPersonList().get(0); //Alice
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(secondPerson).build();
        EditCommand editCommand = new EditCommand(BENSON.getName(), descriptor);
        try {
            editCommand.execute(model);

        } catch (CommandException e) {
            assertEquals(e.getMessage(), EditCommand.MESSAGE_DUPLICATE_PERSON);
        }
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(CARL).build();
        EditCommand editCommand = new EditCommand(BENSON.getName(),
                descriptor);
        try {
            editCommand.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), EditCommand.MESSAGE_DUPLICATE_PERSON);
        }
    }

    @Test
    public void execute_editLineupName_success() throws CommandException {
        EditCommand standardCommand = new EditCommand(EXISTING_LINEUP_NAME, VALID_LINEUP_NAME);
        String msg = standardCommand.execute(model).getFeedbackToUser();
        Lineup editedLineup = model.getLineup(VALID_LINEUP_NAME);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LINEUP_SUCCESS, editedLineup);
        assertEquals(msg, expectedMessage);
    }

    @Test
    public void execute_duplicateLineupName_failure() {
        model.addLineup(new Lineup(VALID_LINEUP_NAME));
        EditCommand standardCommand = new EditCommand(EXISTING_LINEUP_NAME, VALID_LINEUP_NAME);
        try {
            standardCommand.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), EditCommand.MESSAGE_DUPLICATE_LINEUP);
        }
    }

    @Test
    public void execute_editSchedule_success() throws CommandException {
        model.addSchedule(VALID_SCHEDULE);
        EditCommand.EditScheduleDescriptor editScheduleDescriptor = new EditCommand.EditScheduleDescriptor();
        editScheduleDescriptor.setScheduleName(VALID_SCHEDULE_NAME);
        editScheduleDescriptor.setScheduleDescription(VALID_SCHEDULE_DESCRIPTION);
        EditCommand command = new EditCommand(Index.fromZeroBased(0), editScheduleDescriptor);
        Schedule editedSchedule = createEditedSchedule(VALID_SCHEDULE, editScheduleDescriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule);

        String res = command.execute(model).getFeedbackToUser();
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_invalidScheduleIndex_failure() {
        EditCommand.EditScheduleDescriptor editScheduleDescriptor = new EditCommand.EditScheduleDescriptor();
        editScheduleDescriptor.setScheduleName(VALID_SCHEDULE_NAME);
        editScheduleDescriptor.setScheduleDescription(VALID_SCHEDULE_DESCRIPTION);
        EditCommand command = new EditCommand(Index.fromZeroBased(0), editScheduleDescriptor);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }
    }

    @Test
    public void execute_duplicateSchedule_failure() {
        model.addSchedule(VALID_SCHEDULE);
        model.addSchedule(VALID_SCHEDULE_2);
        EditCommand.EditScheduleDescriptor editScheduleDescriptor = new EditCommand.EditScheduleDescriptor();
        editScheduleDescriptor.setScheduleName(VALID_SCHEDULE_NAME);
        editScheduleDescriptor.setScheduleDescription(VALID_SCHEDULE_DESCRIPTION);
        EditCommand command = new EditCommand(Index.fromZeroBased(0), editScheduleDescriptor);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), EditCommand.MESSAGE_DUPLICATE_SCHEDULE);
        }
    }

    @Test
    public void equalsEditPerson() {
        final EditCommand standardCommand = new EditCommand(BENSON.getName(), DESC_AMY);

        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(BENSON.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(BENSON.getName(), DESC_BOB)));
    }

    @Test
    public void equalsEditLineup() {

        final EditCommand editLineupCommand = new EditCommand(EXISTING_LINEUP_NAME, VALID_LINEUP_NAME);
        final EditCommand sameEditLinupCommand = new EditCommand(EXISTING_LINEUP_NAME, VALID_LINEUP_NAME);

        // same object -> returns true
        assertTrue(editLineupCommand.equals(sameEditLinupCommand));

        //null -> returns false
        assertFalse(editLineupCommand.equals(null));

        // different types -> returns false
        assertFalse(editLineupCommand.equals(new ClearCommand()));

        // different lineupName -> returns false
        assertFalse(editLineupCommand.equals(new EditCommand(EXISTING_LINEUP_NAME, DUPLICATE_LINEUP_NAME)));
    }

    @Test
    public void equalsEditSchedule() {

    }
}
