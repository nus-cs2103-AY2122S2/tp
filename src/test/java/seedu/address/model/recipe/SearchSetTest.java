package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchSetTest {

    private SearchSet ss1 = new SearchSet();
    private final List<String> sampleInput = List.of("Western", "Korean", "Aglio Olio", "Bibimbap");
    private final StringBuilder sb = new StringBuilder();

    @BeforeEach
    void initialise() {
        ss1 = new SearchSet();
        for (String s : sampleInput) {
            ss1.add(s);
            appendString(sb, s);
        }
    }

    @Test
    void add_test_success() {
        String newValue = "Halal";
        ss1.add(newValue);
        appendString(sb, newValue);
        assertEquals(ss1.toString(), sb.toString());
    }

    @Test
    void add_value_exists() {
        String existingValue = "Western";
        ss1.add(existingValue);
        appendString(sb, existingValue);
        assertNotEquals(ss1.toString(), sb.toString());
    }

    @Test
    void equals_test() {
        SearchSet ss1Copy = new SearchSet();
        SearchSet ss2 = new SearchSet();
        sampleInput.forEach(s -> {
            ss1Copy.add(s);
            ss2.add(s);
        });
        ss2.add("NEW ENTRY");

        assertNotEquals(ss1, ss1.getSearchValues());
        assertEquals(ss1, ss1);
        assertEquals(ss1, ss1Copy);
        assertNotEquals(ss1, ss2);
    }

    @Test
    void hashCode_test() {
        SearchSet ss1Copy = new SearchSet();
        SearchSet ss2 = new SearchSet();
        sampleInput.forEach(s -> {
            ss1Copy.add(s);
            ss2.add(s);
        });
        ss2.add("NEW ENTRY");

        assertEquals(ss1.hashCode(), ss1Copy.hashCode());
        assertNotEquals(ss1.hashCode(), ss2.hashCode());
    }

    private void appendString(StringBuilder sb, String s) {
        sb.append(s).append(" ");
    }

}
