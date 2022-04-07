package seedu.contax.ui;

/**
 * Specifies a factory interface for creating {@code RecyclableCard} objects.
 *
 * @param <T> The type of model the cards created by this factory is for.
 */
@FunctionalInterface
public interface CardFactory<T> {

    /** Creates a {@link seedu.contax.ui.RecyclableCard} instance of type {@code T>} */
    RecyclableCard<T> createCard();
}
