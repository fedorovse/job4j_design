package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/whenPairWithoutComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    public void whenCommentsAndEmptyLines() {
        String path = "./data/whenCommentsAndEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user1")).isEqualTo("Sergey Fedorov");
        assertThat(config.value("user2")).isEqualTo("Petr Arsentev");
    }

    @Test
    public void whenNoKey() {
        String path = "./data/whenNoKey.properties";
        String message = "No key and/or value in line: {=Sergey Fedorov}";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Test
    public void whenNoValue() {
        String path = "./data/whenNoValue.properties";
        String message = "No key and/or value in line: {name=}";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Test
    public void whenNoEquals() {
        String path = "./data/whenNoEquals.properties";
        String message = "String does not contain a separator \"=\" {nameSergey Fedorov}";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Test
    public void whenValuesIsEmpty() {
        String path = "./data/whenPairWithoutComment.properties";
        String message = "Don't impl this method yet!";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.value("name"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining(message);
    }
}