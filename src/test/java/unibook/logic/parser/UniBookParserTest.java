package unibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unibook.logic.commands.AddCommand;
import unibook.logic.commands.ClearCommand;
import unibook.logic.commands.DeleteCommand;
import unibook.logic.commands.EditCommand;
import unibook.logic.commands.EditCommand.EditPersonDescriptor;
import unibook.logic.commands.ExitCommand;
import unibook.logic.commands.FindCommand;
import unibook.logic.commands.HelpCommand;
import unibook.logic.commands.ListCommand;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
import unibook.model.person.NameContainsKeywordsPredicate;
import unibook.model.person.Person;
import unibook.model.tag.Tag;
import unibook.testutil.Assert;
import unibook.testutil.EditPersonDescriptorBuilder;
import unibook.testutil.PersonBuilder;
import unibook.testutil.PersonUtil;
import unibook.testutil.StudentBuilder;
import unibook.testutil.TypicalIndexes;

public class UniBookParserTest {

    private final UniBookParser parser = new UniBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new StudentBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        Set<Module> modSet = new HashSet<>();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("friends"));
        ModuleCode moduleCode = new ModuleCode("CS2103");
        ModuleName moduleName = new ModuleName("Software Engineering");
        descriptor.setTags(tagSet);
        Module module = new Module(moduleName, moduleCode);
        modSet.add(module);
        descriptor.setModules(modSet);
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor, moduleCode);
        System.out.println(PersonUtil.getEditPersonDescriptorDetails(descriptor));
        System.out.println(EditCommand.COMMAND_WORD
                + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased()
                + " o/person "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD
            + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased()
            + " o/person "
            + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        EditCommand editCommand1 = command;

        assertEquals(editCommand1, command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
