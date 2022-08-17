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
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoValue() {
        String path = "./data/whenNoValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoEquals() {
        String path = "./data/whenNoEquals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}