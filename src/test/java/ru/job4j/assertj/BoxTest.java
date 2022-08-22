package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 2);
        String name = box.whatsThis();
        assertThat(name).startsWith("Tetra");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String name = box.whatsThis();
        assertThat(name).containsIgnoringCase("cube");
    }

    @Test
    void when5VertexThenUnknown() {
        Box box = new Box(5, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenEdgeZeroThenUnknown() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenTetrahedronThen4() {
        Box box = new Box(4, 6);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4);
    }

    @Test
    void whenUnknownShapeThenMinus1() {
        Box box = new Box(5, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isLessThan(0).isEqualTo(-1);
    }

    @Test
    void whenEdgeZeroThenMinus1() {
        Box box = new Box(4, 0);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void whenCubeThenExist() {
        Box box = new Box(8, 2);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenUnknownShapeThenFalse() {
        Box box = new Box(1, 3);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenEdgeZeroThenFalse() {
        Box box = new Box(8, 0);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }
}