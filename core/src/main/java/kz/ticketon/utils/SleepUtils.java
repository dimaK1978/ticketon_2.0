package kz.ticketon.utils;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    static public void sleepSeconds(final long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
