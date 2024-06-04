package net.java_rin.KillSound.utilities;

import java.util.List;
import java.util.Random;

public class RandomList {
    private static final Random RANDOM = new Random();

    /**
     * Selects a random item from the provided list.
     *
     * @param <T>  the type of elements in the list
     * @param list the list to select a random item from
     * @return a random item from the list, or null if the list is empty or null
     */
    public static <T> T random(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int index = RANDOM.nextInt(list.size());
        return list.get(index);
    }
}
