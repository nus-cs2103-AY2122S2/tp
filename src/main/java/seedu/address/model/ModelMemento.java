package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.person.Person;


public class ModelMemento {
    private Model modelState;
    private Optional<Predicate<Person>> modelPredicate;

    public Model getModel() {
        return this.modelState;
    }

    public Predicate<Person> getPredicate() {
        return this.modelPredicate.orElse(PREDICATE_SHOW_ALL_PERSONS);
    }

    public void setModel(Model newModelState) {
        requireNonNull(newModelState);
        if (this.modelState == null) {
            this.modelState = newModelState;
        }
    }

    public void setPredicate(Predicate<Person> newPredicate) {
        if (this.modelPredicate == null) {
            this.modelPredicate = Optional.ofNullable(newPredicate);
        }
    }
}
