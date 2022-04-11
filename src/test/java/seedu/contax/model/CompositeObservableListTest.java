package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompositeObservableListTest {

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(null, null));
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(FXCollections.emptyObservableList(), null));
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(null, FXCollections.emptyObservableList()));
    }

    @Test
    public void testListChangeNotify() {
        ObservableList<Integer> list1 = FXCollections.observableArrayList();
        ObservableList<Integer> list2 = FXCollections.observableArrayList();
        CompositeObservableList<Integer> compositeList = new CompositeObservableList<>(list1, list2);
        ObservableList<Integer> watchList = FXCollections.observableList(compositeList.getUnmodifiableResultList());

        assertEquals(List.of(), watchList);

        list1.add(1);
        list2.add(10);
        assertEquals(List.of(1, 10), watchList);

        list1.add(3);
        list2.add(0, 9);
        assertEquals(List.of(1, 3, 9, 10), watchList);
    }

    @Test
    public void testCascadingNotify() {
        ObservableList<Integer> baseList = FXCollections.observableArrayList();
        ObservableList<Integer> intermediateList = baseList.filtered(x -> x < 60);
        ObservableList<Integer> finalList = intermediateList.filtered(x -> x > 30);

        CompositeObservableList<Integer> combinedList = new CompositeObservableList<>(intermediateList, finalList);

        baseList.setAll(IntStream.range(1, 100).boxed().collect(Collectors.toList()));
        baseList.setAll(IntStream.range(20, 200).boxed().collect(Collectors.toList()));
        baseList.setAll(IntStream.range(40, 400).boxed().collect(Collectors.toList()));
    }
}
