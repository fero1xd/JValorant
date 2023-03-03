package me.fero;

public class Utils {
    public static boolean isError(int code) {
        return (code >= 400 && code <= 429) || code == 500;
    }


}
