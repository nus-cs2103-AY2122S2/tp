package woofareyou.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import woofareyou.logic.commands.AddCommand;
import woofareyou.logic.commands.ClearCommand;
import woofareyou.logic.commands.DeleteCommand;
import woofareyou.logic.commands.EditCommand;
import woofareyou.logic.commands.ExitCommand;
import woofareyou.logic.commands.FindCommand;
import woofareyou.logic.commands.HelpCommand;
import woofareyou.logic.commands.ListCommand;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.NameContainsKeywordsPredicate;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.EditPetDescriptorBuilder;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.PetUtil;
import woofareyou.commons.core.Messages;
import woofareyou.testutil.*;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Pet pet = new PetBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PetUtil.getAddCommand(pet));
        assertEquals(new AddCommand(pet), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PET.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_PET), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Pet pet = new PetBuilder().build();
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(pet).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PET.getOneBased() + " " + PetUtil.getEditPetDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_PET, descriptor), command);
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
        Assert.assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
