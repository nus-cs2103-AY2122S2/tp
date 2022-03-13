package seedu.contax.ui.onboarding;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides logic to handle animation for onboarding window
 */
public class OnboardingAnimator {

    private static final double ANIMATION_DURATION = 0.5;

    public static void fadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public static void fadeIn(Node node, Runnable r) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public static void fadeOut(Node node) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), node);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
    }

    public static void fadeOut(Node node, Runnable r) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), node);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(e -> {r.run();});
        ft.play();
    }

    public static void fadeInOut(Node node) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), node);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), node);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
    }
}
