package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        int index = findIndById(id);
        if (index >= 0) {
            this.mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = findIndById(id);
        if (index >= 0) {
            this.mem.remove(index);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = findIndById(id);
        if (index >= 0) {
            result = this.mem.get(index);
        }
        return result;
    }

    private int findIndById(String id) {
        int resultIndex = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                resultIndex = i;
                break;
            }
        }
        return resultIndex;
    }
}
