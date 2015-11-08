package com.ninegame.bird;

/**
 * Created by lvrh on 15/11/8.
 */
public class Tool {
    public static String getTag(Object o) {
        return "Bird#"+o.getClass().getSimpleName();
    }
}
