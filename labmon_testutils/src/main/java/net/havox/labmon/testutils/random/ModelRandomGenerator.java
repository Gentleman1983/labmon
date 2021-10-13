/*
 * Copyright (C) 2021 [haVox] Design
 * Created by The_G
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.havox.labmon.testutils.random;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simple utility class providing some randomized test data.
 */
public class ModelRandomGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * A simple alphanumeric {@link String} containing all letters in small and capital case.
     */
    public static final String ALPHANUMERIC_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * A simple alphabetic {@link String} containing all letters in small and capital case.
     */
    public static final String ALPHABETIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * This class is not meant to be instantiated.
     */
    private ModelRandomGenerator() {
        super();
    }

    /**
     * Creates a randomized {@link String} of a given length.
     *
     * @param length the desired length
     * @return the string
     */
    public static String randomString(int length) {
        return randomString(length, ALPHANUMERIC_STRING);
    }

    /**
     * Creates a randomized {@link String} of a given length using a dedicated alphabet.
     *
     * @param length the desired length
     * @param alphabet the desired alphabet
     * @return the string
     */
    public static String randomString(int length, String alphabet) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(alphabet.charAt(RANDOM.nextInt(alphabet.length())));
        }
        return builder.toString();
    }

    /**
     * Creates a randomized {@code int} value.
     *
     * @return the value
     */
    public static int randomInt() {
        return RANDOM.nextInt();
    }

    /**
     * Creates a randomized {@code int} value between 0 and a given {@code bound}.
     *
     * @param bound the upper bound
     * @return the value
     */
    public static int randomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * Creates a randomized {@code int} value between a given {@code min} and {@code max} bound.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return the value
     */
    public static int randomIntInRange(int min, int max) {
        int result;
        int bound = (max - min + 1);
        if (bound > 0) {
            result = randomInt(bound) + min;
        } else { // Bound calculation caused int overflow.
            do {
                result = randomInt();
            }
            while (result < min || result > max);
        }

        return result;
    }

    /**
     * Creates a randomized {@code long} value.
     *
     * @return the value
     */
    public static long randomLong() {
        return RANDOM.nextLong();
    }

    /**
     * Creates a randomized {@code long} value between 0 and a given {@code bound}.
     *
     * @param bound the upper bound
     * @return the value
     */
    public static long randomLong(long bound) {
        return RANDOM.nextLong(bound);
    }

    /**
     * Creates a randomized {@code long} value between a given {@code min} and {@code max} bound.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return the value
     */
    public static long randomLongInRange(long min, long max) {
        long result;
        long bound = (max - min + 1);
        if (bound > 0) {
            result = randomLong(bound) + min;
        } else { // Bound calculation caused int overflow.
            do {
                result = randomLong();
            }
            while (result < min || result > max);
        }

        return result;
    }

    /**
     * Creates a randomized {@code boolean} value.
     *
     * @return the value
     */
    public static boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * Creates a randomized {@code double} value.
     *
     * @return the value
     */
    public static double randomDouble() {
        return RANDOM.nextDouble();
    }

    /**
     * Creates a randomized {@code float} value.
     *
     * @return the value
     */
    public static float randomFloat() {
        return RANDOM.nextFloat();
    }

    /**
     * Creates a randomized {@link LocalDate} between January 1st, 1970 and today.
     *
     * @return the date
     */
    public static LocalDate randomLocalDate() {
        return randomLocalDate(LocalDate.of(1970, Month.JANUARY, 1), LocalDate.now());
    }

    /**
     * Creates a randomized {@link LocalDate} between a given {@code min} and {@code max} date.
     *
     * @param min the start date
     * @param max the end date
     * @return the date
     */
    public static LocalDate randomLocalDate(LocalDate min, LocalDate max) {
        long minDay = min.toEpochDay();
        long maxDay = max.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Creates a randomized {@link LocalDateTime} between January 1st, 1970 00:00 and now.
     *
     * @return the date time
     */
    public static LocalDateTime randomLocalDateTime() {
        return randomLocalDateTime(LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0), LocalDateTime.now());
    }

    /**
     * Creates a randomized {@link LocalDateTime} between a given {@code min} and {@code max} time.
     *
     * @param min the start date time
     * @param max the end date time
     * @return the date time
     */
    public static LocalDateTime randomLocalDateTime(LocalDateTime min, LocalDateTime max) {
        long minSecond = min.toEpochSecond(ZoneOffset.UTC);
        long maxSecond = max.toEpochSecond(ZoneOffset.UTC);
        long randomSecond = ThreadLocalRandom.current().nextLong(minSecond, maxSecond);

        return LocalDateTime.ofEpochSecond(randomSecond, 0, ZoneOffset.UTC);
    }
}
