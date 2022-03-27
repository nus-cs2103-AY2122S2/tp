package seedu.trackbeau.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.ClearCommand;
import seedu.trackbeau.logic.commands.ExitCommand;
import seedu.trackbeau.logic.commands.HelpCommand;
import seedu.trackbeau.logic.commands.customer.AddCustomerCommand;
import seedu.trackbeau.logic.commands.customer.DeleteCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand.EditCustomerDescriptor;
import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.commands.customer.ListCustomersCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;
import seedu.trackbeau.testutil.CustomerBuilder;
import seedu.trackbeau.testutil.CustomerUtil;
import seedu.trackbeau.testutil.EditCustomerDescriptorBuilder;

public class TrackBeauParserTest {

    private final TrackBeauParser parser = new TrackBeauParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCustomerCommand command = (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
            DeleteCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST_CUSTOMER.getOneBased());
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
            }
        };
        assertEquals(new DeleteCustomerCommand(firstCustomer), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command = (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
            + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        EditCustomerCommand editCustomerCommand = new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor);
        assertEquals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, keywords);
        FindCustomerCommand command = (FindCustomerCommand) parser.parseCommand(
            FindCustomerCommand.COMMAND_WORD + " /n" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCustomerCommand(new SearchContainsKeywordsPredicate(prefixArr)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD) instanceof ListCustomersCommand);
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD + " 3") instanceof ListCustomersCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
