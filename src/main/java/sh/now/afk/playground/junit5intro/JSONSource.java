package sh.now.afk.playground.junit5intro;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JSONArgumentsProvider.class)
public @interface JSONSource {
    String value();
    Class type() default  String.class;
}
