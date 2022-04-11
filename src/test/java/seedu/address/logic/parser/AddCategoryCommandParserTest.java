package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADD_EXPENSE_CATEGORY_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.model.expense.ExpenseCategory;

class AddCategoryCommandParserTest {
    private AddCategoryCommandParser parser = new AddCategoryCommandParser();

    //    @Test
    //    public void parse_allFieldsPresent_success() {
    //        AddCategoryCommand expectedExpenseCategory =
    //                new AddCategoryCommand(new ExpenseCategoryBuilder()
    //                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT).build());
    //        // multiple descriptions - last description accepted
    //        assertParseSuccess(parser, ADD_EXPENSE_CATEGORY_DESC,
    //                expectedExpenseCategory);
    //    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE);

        // missing amount expense Category
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid expenseCategory
        assertParseFailure(parser, INVALID_ADD_EXPENSE_CATEGORY_DESC, ExpenseCategory.MESSAGE_CONSTRAINTS);
    }

}
