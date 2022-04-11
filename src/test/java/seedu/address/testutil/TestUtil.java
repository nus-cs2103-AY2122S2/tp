package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the person in the {@code model}'s person list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredRecipeList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s person list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredRecipeList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Recipe getRecipe(Model model, Index index) {
        return model.getFilteredRecipeList().get(index.getZeroBased());
    }

    /**
     * Helper function to check the equality of {@code filteredList}. <br>
     * This method exists as current list equality checks also consider matching the index of each element, this
     * means that element at index 3 of one list has to be the same at index 3 on the other list. There are
     * workarounds to this by using {@link List#containsAll(Collection)} but this method also allows checking for the
     * uniqueness of its elements.
     *
     * @param modelList {@code filteredList} from {@code Model} after its predicate has been updated.
     * @param expectedList {@code List} of unique elements that {@code modelList} is expected to have.
     * @return true if the uniqueness and size of both lists are equal.
     */
    public static <T> boolean filteredListEquality(List<T> modelList, List<T> expectedList) {
        Set<T> modelSet = new HashSet<>(modelList);
        if (modelSet.size() != expectedList.size()) {
            return false;
        }

        for (T t : expectedList) {
            if (!modelSet.contains(t)) {
                return false;
            }
        }
        return true;
    }
}
