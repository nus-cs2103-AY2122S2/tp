package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.CompletionTime;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.ServingSize;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditRecipeDescriptor objects.
 */
public class EditRecipeDescriptorBuilder {

    private EditRecipeDescriptor descriptor;

    public EditRecipeDescriptorBuilder() {
        descriptor = new EditRecipeDescriptor();
    }

    public EditRecipeDescriptorBuilder(EditRecipeDescriptor descriptor) {
        this.descriptor = new EditRecipeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecipeDescriptor} with fields containing {@code recipe}'s details
     */
    public EditRecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new EditRecipeDescriptor();
        descriptor.setName(recipe.getName());
        descriptor.setCompletionTime(recipe.getCompletionTime());
        descriptor.setServingSize(recipe.getServingSize());
        descriptor.setIngredients(recipe.getIngredients());
        descriptor.setSteps(recipe.getSteps());
        descriptor.setTags(recipe.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code CompletionTime} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withCompletionTime(int completionTime) {
        descriptor.setCompletionTime(new CompletionTime(completionTime));
        return this;
    }

    /**
     * Sets the {@code ServingSize} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withServingSize(int servingSize) {
        descriptor.setServingSize(new ServingSize(servingSize));
        return this;
    }

    /**
     * Parses the {@code ingredients} into a {@code List<Ingredient>} and set it to the
     * {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withIngredients(String... ingredient) {
        try {
            descriptor.setIngredients(SampleDataUtil.getIngredientList(ingredient));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Parses the {@code steps} into a {@code List<Step>} and set it to the {@code EditRecipeDescriptor}
     * that we are building.
     */
    public EditRecipeDescriptorBuilder withSteps(String... steps) {
        descriptor.setSteps(SampleDataUtil.getStepList(steps));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRecipeDescriptor}
     * that we are building.
     */
    public EditRecipeDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditRecipeDescriptor build() {
        return descriptor;
    }
}
