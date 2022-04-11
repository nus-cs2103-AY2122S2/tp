package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MAIL_X_DESC_AMY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.testutil.MailXDescriptorBuilder;

public class MailXCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new PriorityTaskList());

    @Test
    public void handleNameAdditionTest_success() throws CommandException {
        MailXCommand mailXCommand = new MailXCommand(new MailXCommand.MailXDescriptor());

        Person p = model.getFilteredPersonList().get(INDEX_FIRST.getOneBased());

        assertArrayEquals(mailXCommand
                .createEmailList(new MailXDescriptorBuilder().withName(p.getName().toString()).build(), model),
                new String[] { p.getEmail().toString() });
    }

    @Test
    public void handleModAdditionTest_success() throws CommandException {
        MailXCommand mailXCommand = new MailXCommand(new MailXCommand.MailXDescriptor());

        Mod mod = model.getFilteredPersonList().get(INDEX_FIRST.getOneBased()).getMod();

        List<String> l = model.getFilteredPersonList()
                .stream()
                .filter(p -> p.getMod().equals(mod))
                .map(p -> p.getEmail().toString())
                .collect(Collectors.toList());

        String[] expectedArr = new String[l.size()];
        expectedArr = l.toArray(expectedArr);

        assertArrayEquals(mailXCommand
                        .createEmailList(new MailXDescriptorBuilder().withMod(mod.toString()).build(), model),
                expectedArr);
    }

    @Test
    public void handleGroupAdditionTest_success() throws CommandException {
        MailXCommand mailXCommand = new MailXCommand(new MailXCommand.MailXDescriptor());

        Group group = model.getFilteredPersonList().get(INDEX_FIRST.getOneBased()).getGroup();

        List<String> l = model.getFilteredPersonList()
                .stream()
                .filter(p -> p.getGroup().equals(group))
                .map(p -> p.getEmail().toString())
                .collect(Collectors.toList());

        String[] expectedArr = new String[l.size()];
        expectedArr = l.toArray(expectedArr);

        assertArrayEquals(mailXCommand
                        .createEmailList(new MailXDescriptorBuilder().withGroup(group.toString()).build(), model),
                expectedArr);
    }

    @Test
    public void handleEmailAdditionTest_success() throws CommandException {
        MailXCommand mailXCommand = new MailXCommand(new MailXCommand.MailXDescriptor());

        Person p = model.getFilteredPersonList().get(INDEX_FIRST.getOneBased());

        assertArrayEquals(mailXCommand
                        .createEmailList(new MailXDescriptorBuilder().withEmail(p.getEmail().toString())
                                .build(), model),
                new String[] { p.getEmail().toString() });
    }

    @Test
    public void equals() {
        final MailXCommand standardCommand = new MailXCommand(MAIL_X_DESC_AMY);

        // same values -> returns true
        MailXCommand.MailXDescriptor copyDescriptor = new MailXCommand.MailXDescriptor(MAIL_X_DESC_AMY);
        MailXCommand commandWithSameValues = new MailXCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST, DESC_BOB));
    }
}
