package seedu.ibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_A;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.logic.commands.ClearCommand;
import seedu.ibook.logic.commands.DeleteCommand;
import seedu.ibook.logic.commands.ExitCommand;
import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.logic.commands.ListCommand;
import seedu.ibook.logic.commands.UpdateCommand;
import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.CategoryFilter;
import seedu.ibook.model.product.filters.NameFilter;
import seedu.ibook.model.product.filters.ProductFulfillsFiltersPredicate;
import seedu.ibook.testutil.ProductBuilder;
import seedu.ibook.testutil.ProductUtil;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;


public class IBookParserTest {
    private final IBookParser parser = new IBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Product product = new ProductBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ProductUtil.getAddCommand(product));
        assertEquals(new AddCommand(product), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PRODUCT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PRODUCT), command);
    }

    @Test
    public void parseCommand_update() throws Exception {
        Product product = new ProductBuilder().withPrice(VALID_PRICE_A).build();
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(product).build();
        UpdateCommand command = (UpdateCommand) parser.parseCommand(UpdateCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PRODUCT.getOneBased() + " " + ProductUtil.getUpdateProductDescriptorDetails(descriptor));
        assertEquals(new UpdateCommand(INDEX_FIRST_PRODUCT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + NAME_FULL_A + CATEGORY_FULL_A);

        ProductFulfillsFiltersPredicate predicate = new ProductFulfillsFiltersPredicate();
        predicate.addFilter(new NameFilter(new Name(VALID_NAME_A)));
        predicate.addFilter(new CategoryFilter(new Category(VALID_CATEGORY_A)));

        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + "  ") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

}
