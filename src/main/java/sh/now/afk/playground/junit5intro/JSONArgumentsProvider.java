package sh.now.afk.playground.junit5intro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class JSONArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<JSONSource> {
    String jsonFileName;
    Class type;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        ClassPathResource resource = new ClassPathResource(jsonFileName);
        File file = resource.getFile();
        List<?> list = new ObjectMapper().readerForListOf(type).readValue(file);
        return list.stream().map(Arguments::of);
    }

    @Override
    public void accept(JSONSource jsonSource) {
        jsonFileName = jsonSource.value();
        type = jsonSource.type();
    }
}
