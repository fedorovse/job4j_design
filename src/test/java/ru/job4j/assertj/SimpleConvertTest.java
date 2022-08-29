package ru.job4j.assertj;

import org.assertj.core.data.Index;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    private SimpleConvert simpleConvert;

    @BeforeEach
    void iniAll() {
        simpleConvert = new SimpleConvert();
    }

    @Test
    void checkArray() {
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isInstanceOf(List.class)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .containsAnyOf("lock", "first")
                .containsExactly("first", "second", "three", "four", "five")
                .contains("three", Index.atIndex(2))
                .first()
                .isEqualTo("first");
    }

    @Test
    void checkSet() {
        Set<String> stringSet = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(stringSet)
                .hasSize(5)
                .containsExactlyInAnyOrder("second", "four", "first", "five", "three")
                .doesNotContain("frog", "zero");
    }

    @Test
    void checkMap() {
        Map<String, Integer> map
                = simpleConvert.toMap("first", "second", "second", "second", "three", "four", "five");
        assertThat(map)
                .hasSize(5)
                .containsEntry("second", 1)
                .containsEntry("three", 4)
                .containsOnlyKeys("second", "four", "first", "five", "three")
                .doesNotContainValue(2)
                .containsValues(0, 1, 4, 5, 6);
    }
}