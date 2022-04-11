package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyRecipeBook;

class SampleDataUtilTest {

    @Test
    public void getSampleRecipeBook_test() {
        ReadOnlyRecipeBook sampleRB = SampleDataUtil.getSampleRecipeBook();
        assertFalse(sampleRB.getRecipeList().isEmpty());
    }
}
