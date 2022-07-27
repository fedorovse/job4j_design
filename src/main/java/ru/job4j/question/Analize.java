package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        int equalUsers = 0;
        Map<Integer, String> currentMap = new HashMap<>(current.size());
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (currentMap.containsKey(user.getId())) {
                if (Objects.equals(user.getName(), currentMap.get(user.getId()))) {
                    equalUsers++;
                } else {
                    changed++;
                }
            } else {
                deleted++;
            }
        }
        return new Info(currentMap.size() - equalUsers - changed, changed, deleted);
    }

}