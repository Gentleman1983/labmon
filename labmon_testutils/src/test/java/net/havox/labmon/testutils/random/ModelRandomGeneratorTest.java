/*
 * Copyright (C) 2021 [haVox] Design
 * Created by Christian Otto
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

import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ModelRandomGeneratorTest {
    /**
     * The pseudo random generator for this test class.
     */
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#ALPHANUMERIC_STRING}
     * Then:  the given alphabet matches all alphanumeric letters and numbers ({@code [A-Za-z0-9]}).
     */
    @Test
    void testIsAlphanumericStringAlphanumeric() {
        String regexString = "[A-Za-z0-9]*";
        assertTrue(ModelRandomGenerator.ALPHANUMERIC_STRING.matches(regexString),
                "An alphanumeric string should match the regex '" + regexString + "'.");
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            assertTrue(ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)),
                    "Expected alphanumeric alphabet to contain letter '" + alphabet + "'.");
        }
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            assertTrue(ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)),
                    "Expected alphanumeric alphabet to contain letter '" + alphabet + "'.");
        }
        for (char alphabet = '0'; alphabet <= '9'; alphabet++) {
            assertTrue(ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)),
                    "Expected alphanumeric alphabet to contain letter '" + alphabet + "'.");
        }
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#ALPHABETIC_STRING}
     * Then:  the given alphabet matches all alphabet letters ({@code [A-Za-z]}).
     */
    @Test
    void testIsAlphabeticStringAlphabetic() {
        String regexString = "[A-Za-z]*";
        assertTrue(ModelRandomGenerator.ALPHABETIC_STRING.matches(regexString),
                "An alphabetic string should match the regex '" + regexString + "'.");
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            assertTrue(ModelRandomGenerator.ALPHABETIC_STRING.contains(Character.toString(alphabet)),
                    "Expected alphabetic alphabet to contain letter '" + alphabet + "'.");
        }
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            assertTrue(ModelRandomGenerator.ALPHABETIC_STRING.contains(Character.toString(alphabet)),
                    "Expected alphabetic alphabet to contain letter '" + alphabet + "'.");
        }
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomString(int)}
     * Then:  a randomized alphanumeric {@link String} of a random length is created.
     */
    @RepeatedTest(100)
    void testRandomString() {
        int expectedLength = RANDOM.nextInt(100) + 1;
        String expectedAlphabet = ModelRandomGenerator.ALPHANUMERIC_STRING;
        String randomString = ModelRandomGenerator.randomString(expectedLength);

        this.checkRandomString(randomString, expectedLength, expectedAlphabet);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomString(int, String)}
     * Then:  a randomized {@link String} of a given alphabet of a random length is created.
     */
    @RepeatedTest(100)
    void testRandomStringAlphabet() {
        int expectedLength = RANDOM.nextInt(100) + 1;
        String expectedAlphabet = ModelRandomGenerator.randomString(10);
        String randomString = ModelRandomGenerator.randomString(expectedLength, expectedAlphabet);

        this.checkRandomString(randomString, expectedLength, expectedAlphabet);
    }

    /**
     * Checks if a given random {@link String} has an expected length and consists of letters of a given alphabet.
     *
     * @param randomString     the random string
     * @param expectedLength   the expected string length
     * @param expectedAlphabet the expected alphabet
     */
    private void checkRandomString(String randomString, int expectedLength, String expectedAlphabet) {
        // A string should be generated.
        assertNotNull(randomString);

        // The string should have the correct length.
        String msgLengthTest = "The random string '" + randomString + "' should have length " +
                expectedLength + ". The length is " + randomString.length() + ".";
        assertEquals(randomString.length(), expectedLength, msgLengthTest);

        // The string should consist of letters of a given alphabet.
        for (int i = 0; i < randomString.length(); i++) {
            CharSequence letter = String.valueOf(randomString.charAt(i));

            String msgAlphabetTest = "The random string '" + randomString + "' should only consist of " +
                    "letters of the alphabet '" + expectedAlphabet + "'. We found the letter '" +
                    letter + "'.";
            assertTrue(expectedAlphabet.contains(letter), msgAlphabetTest);
        }
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomInt()}
     * Then:  a random {@link Integer} value between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE} is created.
     */
    @RepeatedTest(100)
    void testRandomInt() {
        int expectedMinValue = Integer.MIN_VALUE;
        int expectedMaxValue = Integer.MAX_VALUE;
        int randomInt = ModelRandomGenerator.randomInt();

        this.checkRandomInt(randomInt, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomInt(int)}
     * Then:  a random {@link Integer} value between {@link Integer#MIN_VALUE} and a given maximum value is created.
     */
    @RepeatedTest(100)
    void testRandomIntUpperBound() {
        int expectedMinValue = Integer.MIN_VALUE;
        int maxValueLowerBoundary = 0;
        int expectedMaxValue = 0; // expected max value >= 1.
        while (expectedMaxValue <= 0) {
            expectedMaxValue = RANDOM.nextInt();
        }
        int randomInt = ModelRandomGenerator.randomInt(expectedMaxValue);

        this.checkRandomInt(randomInt, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomIntInRange(int, int)}
     * Then:  a random {@link Integer} value in a range between two given values is created.
     */
    @RepeatedTest(100)
    void testRandomIntInRange() {
        int expectedMinValue = RANDOM.nextInt();
        int expectedMaxValue = RANDOM.nextInt();
        if (expectedMinValue > expectedMaxValue) // expected max value >= expected min value.
        {
            int temp = expectedMaxValue;
            expectedMaxValue = expectedMinValue;
            expectedMinValue = temp;
        }
        int randomInt = ModelRandomGenerator.randomIntInRange(expectedMinValue, expectedMaxValue);

        this.checkRandomInt(randomInt, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link Integer} value is between a given minimal and maximal value.
     *
     * @param randomInt the random value
     * @param expectedMinValue the expected minimal value
     * @param expectedMaxValue the expected maximal value
     */
    private void checkRandomInt(Integer randomInt, int expectedMinValue, int expectedMaxValue) {
        // The integer value should be generated.
        assertNotNull(randomInt);

        // The integer value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomInt).append(".");
        assertTrue(expectedMinValue <= randomInt, msg.toString());
        assertTrue(randomInt <= expectedMaxValue, msg.toString());
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLong()}
     * Then:  a random {@link Long} value between {@link Long#MIN_VALUE} and {@link Long#MAX_VALUE} is created.
     */
    @RepeatedTest(100)
    void testRandomLong() {
        long expectedMinValue = Long.MIN_VALUE;
        long expectedMaxValue = Long.MAX_VALUE;
        long randomLong = ModelRandomGenerator.randomLong();

        this.checkRandomLong(randomLong, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLong(long)}
     * Then:  a random {@link Long} value between {@link Long#MIN_VALUE} and a given maximum value is created.
     */
    @RepeatedTest(100)
    void testRandomLongUpperBound() {
        long expectedMinValue = Long.MIN_VALUE;
        long expectedMaxValue = 0; // expected max value >= 1.
        while (expectedMaxValue <= 0) {
            expectedMaxValue = RANDOM.nextLong();
        }
        long randomLong = ModelRandomGenerator.randomLong(expectedMaxValue);

        this.checkRandomLong(randomLong, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLongInRange(long, long)}
     * Then:  a random {@link Long} value in a range between two given values is created.
     */
    @RepeatedTest(100)
    void testRandomLongInRange() {
        long expectedMinValue = RANDOM.nextLong();
        long expectedMaxValue = RANDOM.nextLong();
        if (expectedMinValue > expectedMaxValue) // expected max value >= expected min value.
        {
            long temp = expectedMaxValue;
            expectedMaxValue = expectedMinValue;
            expectedMinValue = temp;
        }
        long randomLong = ModelRandomGenerator.randomLongInRange(expectedMinValue, expectedMaxValue);

        this.checkRandomLong(randomLong, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link Long} value is between a given minimal and maximal value.
     *
     * @param randomLong the random value
     * @param expectedMinValue the expected minimal value
     * @param expectedMaxValue the expected maximal value
     */
    private void checkRandomLong(Long randomLong, long expectedMinValue, long expectedMaxValue) {
        // The long value should be generated.
        assertNotNull(randomLong);

        // The long value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomLong).append(".");
        assertTrue(expectedMinValue <= randomLong, msg.toString());
        assertTrue(randomLong <= expectedMaxValue, msg.toString());
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomBoolean()}
     * Then:  a random {@link Boolean} value is created.
     */
    @RepeatedTest(100)
    void testRandomBoolean() {
        Boolean randomBoolean = ModelRandomGenerator.randomBoolean();

        // The boolean value should be generated.
        assertNotNull(randomBoolean);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomFloat()}
     * Then:  a random {@link Float} value in a range between 0.0 and 1.0 is created.
     */
    @RepeatedTest(100)
    void testRandomFloat() {
        float expectedMinValue = 0.0f;
        float expectedMaxValue = 1.0f;
        float randomFloat = ModelRandomGenerator.randomFloat();

        this.checkRandomFloat(randomFloat, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link Float} value is between a given minimal and maximal value.
     *
     * @param randomFloat the random value
     * @param expectedMinValue the expected minimal value
     * @param expectedMaxValue the expected maximal value
     */
    private void checkRandomFloat(Float randomFloat, float expectedMinValue, float expectedMaxValue) {
        // The float value should be generated.
        assertNotNull(randomFloat);

        // The float value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomFloat).append(".");
        assertTrue(expectedMinValue <= randomFloat, msg.toString());
        assertTrue(randomFloat < expectedMaxValue, msg.toString());
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomDouble()}
     * Then:  a random {@link Double} value in a range between 0.0 and 1.0 is created.
     */
    @RepeatedTest(100)
    void testRandomDouble() {
        double expectedMinValue = 0.0d;
        double expectedMaxValue = 1.0d;
        double randomDouble = ModelRandomGenerator.randomDouble();

        this.checkRandomDouble(randomDouble, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link Double} value is between a given minimal and maximal value.
     *
     * @param randomDouble the random value
     * @param expectedMinValue the expected minimal value
     * @param expectedMaxValue the expected maximal value
     */
    private void checkRandomDouble(Double randomDouble, double expectedMinValue, double expectedMaxValue) {
        // The double value should be generated.
        assertNotNull(randomDouble);

        // The double value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomDouble).append(".");
        assertTrue(expectedMinValue <= randomDouble, msg.toString());
        assertTrue(randomDouble < expectedMaxValue, msg.toString());
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLocalDate()}
     * Then:  a random {@link LocalDate} between January, 1st 1970 and today is created.
     */
    @RepeatedTest(100)
    void testRandomLocalDate() {
        LocalDate expectedMinValue = LocalDate.of(1970, Month.JANUARY, 1);
        LocalDate expectedMaxValue = LocalDate.now();
        LocalDate randomLocalDate = ModelRandomGenerator.randomLocalDate();

        this.checkRandomLocalDate(randomLocalDate, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLocalDate(LocalDate, LocalDate)}
     * Then:  a random {@link LocalDate} between two given dates is created.
     */
    @RepeatedTest(100)
    void testRandomLocalDateInRange() {
        LocalDate expectedMinValue = ModelRandomGenerator.randomLocalDate();
        LocalDate expectedMaxValue = ModelRandomGenerator.randomLocalDate();
        while (!expectedMaxValue.isAfter(expectedMinValue)) {
            expectedMaxValue = ModelRandomGenerator.randomLocalDate();
        }
        LocalDate randomLocalDate = ModelRandomGenerator.randomLocalDate(expectedMinValue, expectedMaxValue);

        this.checkRandomLocalDate(randomLocalDate, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link LocalDate} value is between a given dates value.
     *
     * @param randomLocalDate a random date
     * @param expectedMinLocalDate a start date
     * @param expectedMaxLocalDate an end date
     */
    private void checkRandomLocalDate(LocalDate randomLocalDate, LocalDate expectedMinLocalDate,
                                      LocalDate expectedMaxLocalDate) {
        // The local date value should be generated.
        assertNotNull(randomLocalDate);

        // The local date value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The date should be between ").append(expectedMinLocalDate).append(" and ")
                .append(expectedMaxLocalDate).append(" but was ").append(randomLocalDate).append(".");
        assertTrue(expectedMinLocalDate.isBefore(randomLocalDate)
                || expectedMinLocalDate.isEqual(randomLocalDate), msg.toString());
        assertTrue(randomLocalDate.isBefore(expectedMaxLocalDate)
                || randomLocalDate.isEqual(expectedMaxLocalDate), msg.toString());
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLocalDateTime()}
     * Then:  a random {@link LocalDateTime} between January, 1st 1970, 00:00 and now is created.
     */
    @RepeatedTest(100)
    void testRandomLocalDateTime() {
        LocalDateTime expectedMinValue = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0);
        LocalDateTime expectedMaxValue = LocalDateTime.now();
        LocalDateTime randomLocalDateTime = ModelRandomGenerator.randomLocalDateTime();

        this.checkRandomLocalDateTime(randomLocalDateTime, expectedMinValue, expectedMaxValue);
    }

    /**
     * Given: {@link ModelRandomGenerator}
     * When:  calling {@link ModelRandomGenerator#randomLocalDateTime(LocalDateTime, LocalDateTime)}
     * Then:  a random {@link LocalDateTime} between two given {@link LocalDateTime}s is created.
     */
    @RepeatedTest(100)
    void testRandomLocalDateTimeInRange() {
        LocalDateTime expectedMinValue = ModelRandomGenerator.randomLocalDateTime();
        LocalDateTime expectedMaxValue = ModelRandomGenerator.randomLocalDateTime();
        while (!expectedMaxValue.isAfter(expectedMinValue)) {
            expectedMaxValue = ModelRandomGenerator.randomLocalDateTime();
        }
        LocalDateTime randomLocalDateTime = ModelRandomGenerator.randomLocalDateTime(expectedMinValue, expectedMaxValue);

        this.checkRandomLocalDateTime(randomLocalDateTime, expectedMinValue, expectedMaxValue);
    }

    /**
     * Checks if a random {@link LocalDateTime} value is between two given {@link LocalDateTime}s.
     *
     * @param randomLocalDateTime a random date time
     * @param expectedMinLocalDateTime a start date time
     * @param expectedMaxLocalDateTime an end date time
     */
    private void checkRandomLocalDateTime(LocalDateTime randomLocalDateTime, LocalDateTime expectedMinLocalDateTime,
                                          LocalDateTime expectedMaxLocalDateTime) {
        // The local date value should be generated.
        assertNotNull(randomLocalDateTime);

        // The local date value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The date should be between ").append(expectedMinLocalDateTime).append(" and ")
                .append(expectedMaxLocalDateTime).append(" but was ").append(randomLocalDateTime).append(".");
        assertTrue(expectedMinLocalDateTime.isBefore(randomLocalDateTime)
                || expectedMinLocalDateTime.isEqual(randomLocalDateTime), msg.toString());
        assertTrue(randomLocalDateTime.isBefore(expectedMaxLocalDateTime)
                || randomLocalDateTime.isEqual(expectedMaxLocalDateTime), msg.toString());
    }
}
