package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.EditCommand.createEditedLineup;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final Person VALID_PERSON = new PersonBuilder().withName("Joel")
            .withEmail("joel@example.com").withPhone("1267912")
            .withHeight("221").withJerseyNumber("83").withWeight("120")
            .withTags("C").build();
    private static final Name VALID_NAME = VALID_PERSON.getName();
    private static final Person VALID_PERSON_2 = new PersonBuilder().withName("Joelly")
            .withEmail("joelly@example.com").withPhone("1267582")
            .withHeight("190").build();
    private static final Name VALID_NAME_2 = VALID_PERSON_2.getName();
    private static final Person INVALID_PERSON = new PersonBuilder().withName("Joel").build();
    private static final Lineup VALID_LINEUP = new LineupBuilder().withLineupName("best 5").build();
    private static final Lineup VALID_LINEUP_2 = new LineupBuilder().withLineupName("worst 5").build();
    private static final LineupName VALID_LINEUP_NAME = VALID_LINEUP.getLineupName();
    private static final LineupName VALID_LINEUP_NAME_2 = VALID_LINEUP_2.getLineupName();
    private static final LineupName INVALID_LINEUP_NAME = VALID_LINEUP.getLineupName();


    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
        model.addPerson(VALID_PERSON);
        expectedModel.addPerson(VALID_PERSON);
    }

    @Test
    public void execute_editPerson_success() throws CommandException {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(VALID_PERSON_2).build();
        EditCommand editCommand = new EditCommand(VALID_NAME, descriptor);
        CommandResult commandResult = editCommand.execute(model);
        Person editedPerson = createEditedPerson(expectedModel.getPerson(VALID_NAME), descriptor);
        expectedModel.setPerson(VALID_PERSON, editedPerson);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    @Test
    public void execute_duplicatePerson_failure() {
        model.addPerson(VALID_PERSON_2);
        expectedModel.addPerson(VALID_PERSON_2);
        EditCommand command = new EditCommand(VALID_NAME_2,
                new EditPersonDescriptorBuilder(INVALID_PERSON).build());
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(EditCommand.MESSAGE_DUPLICATE_PERSON, e.getMessage());
        }
    }

    @Test
    public void execute_editLineup_success() throws CommandException {
        model.addLineup(VALID_LINEUP);
        expectedModel.addLineup(VALID_LINEUP);
        EditCommand editCommand = new EditCommand(VALID_LINEUP_NAME, VALID_LINEUP_NAME_2);
        CommandResult commandResult = editCommand.execute(model);
        Lineup editedLineup = createEditedLineup(VALID_LINEUP, VALID_LINEUP_NAME_2);
        expectedModel.setLineup(VALID_LINEUP, editedLineup);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(EditCommand.MESSAGE_EDIT_LINEUP_SUCCESS, editedLineup));
    }

    @Test
    public void execute_duplicateLineup_failure() {
        model.addLineup(VALID_LINEUP);
        model.addLineup(VALID_LINEUP_2);
        EditCommand command = new EditCommand(VALID_LINEUP_NAME_2, INVALID_LINEUP_NAME);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(EditCommand.MESSAGE_DUPLICATE_LINEUP, e.getMessage());
        }
    }


}
