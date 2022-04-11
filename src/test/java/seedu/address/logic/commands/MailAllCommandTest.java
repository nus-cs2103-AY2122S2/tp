package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tasks.PriorityTaskList;



public class MailAllCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new PriorityTaskList());
    private Model emptyModel = new ModelManager(new ContactList(), new UserPrefs(), new PriorityTaskList());

    @Test
    public void retrieveEmailStringsTest_commandExceptionThrown() throws CommandException {
        MailAllCommand mailAllCommand = new MailAllCommand();
        assertCommandFailure(mailAllCommand, emptyModel, MailAllCommand.NO_EMAILS_SPECIFIED);
    }

    @Test
    public void retrieveEmailStringsTest_success() throws CommandException {
        MailAllCommand mailAllCommand = new MailAllCommand();

        List<String> l = model.getFilteredPersonList()
                .stream()
                .map(p -> p.getEmail().toString())
                .distinct()
                .collect(Collectors.toList());

        String[] emailStrings = new String[l.size()];
        emailStrings = l.toArray(emailStrings);

        assertArrayEquals(mailAllCommand.retrieveEmailStrings(model), emailStrings);
    }

}
