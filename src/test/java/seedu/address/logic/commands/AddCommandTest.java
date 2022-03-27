package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Person;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {
    private static Person VALID_PERSON = new PersonBuilder().withName("Joel")
            .withEmail("joel@example.com").withPhone("1267912")
            .withHeight("221").withJerseyNumber("83").withWeight("120")
            .withTags("C").build();
    private static Person INVALID_PERSON = new PersonBuilder().withName("Alice Pauline").build();
    private static Person INVALID_PERSON_2 = new PersonBuilder().withName("Daniel Lee").withJerseyNumber("2").build();
    private static final Lineup VALID_LINEUP = new Lineup(new LineupName("Dummy"));
    private static final Lineup VALID_LINEUP_2 = new LineupBuilder().build();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());;
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
        model.addLineup(VALID_LINEUP);
        expectedModel.addLineup(VALID_LINEUP);
    }

    @Test
    public void execute_addPerson_success() throws CommandException {
        expectedModel.addPerson(VALID_PERSON);
        CommandResult commandResult = new AddCommand(VALID_PERSON).execute(model);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(AddCommand.MESSAGE_ADD_PERSON_SUCCESS, VALID_PERSON));
    }

    @Test
    public void getExecute_invalidAddPerson_duplicatePerson() {
        Command command = new AddCommand(INVALID_PERSON);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(AddCommand.MESSAGE_DUPLICATE_PERSON ,e.getMessage());
        }
    }

    @Test
    public void getExecute_invalidAddPerson_duplicateJerseyNumber() {
        Command command = new AddCommand(INVALID_PERSON_2);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(AddCommand.MESSAGE_DUPLICATE_JERSEY_NUMBER ,e.getMessage());
        }
    }

    @Test
    public void execute_addLineup_success() throws CommandException {
        expectedModel.addLineup(VALID_LINEUP_2);
        CommandResult commandResult = new AddCommand(VALID_LINEUP_2).execute(model);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(AddCommand.MESSAGE_ADD_LINEUP_SUCCESS, VALID_LINEUP_2));
    }

    @Test
    public void getExecute_invalidAddLineup_duplicateLineup() {
        Command command = new AddCommand(VALID_LINEUP);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(AddCommand.MESSAGE_DUPLICATE_LINEUP_NAME ,e.getMessage());
        }
    }
}
