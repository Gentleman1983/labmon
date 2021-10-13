/*
 * Copyright (C) 2021 [haVox] Design
 * Created by  Christian Otto
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

import static org.junit.Assert.*;

import java.security.SecureRandom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import net.havox.labmon.testutils.junit.junit4.ExtendedJunit4Runner;
import net.havox.labmon.testutils.junit.junit4.Repeat;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ExtendedJunit4Runner.class)
public class ModelRandomGeneratorTest {

    private static final SecureRandom RANDOM = new SecureRandom();

    @Test
    public void testIsAlphanumericStringAlphanumeric() {
        String regexString = "[A-Za-z0-9]*";
        assertTrue("An alphanumeric string should match the regex '" + regexString + "'.", ModelRandomGenerator.ALPHANUMERIC_STRING.matches(regexString));
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            assertTrue("Expected alphanumeric alphabet to contain letter '" + alphabet + "'.", ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)));
        }
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            assertTrue("Expected alphanumeric alphabet to contain letter '" + alphabet + "'.", ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)));
        }
        for (char alphabet = '0'; alphabet <= '9'; alphabet++) {
            assertTrue("Expected alphanumeric alphabet to contain letter '" + alphabet + "'.", ModelRandomGenerator.ALPHANUMERIC_STRING.contains(Character.toString(alphabet)));
        }
    }

    @Test
    public void testIsAlphabeticStringAlphabetic() {
        String regexString = "[A-Za-z]*";
        assertTrue("An alphabetic string should match the regex '" + regexString + "'.", ModelRandomGenerator.ALPHABETIC_STRING.matches(regexString));
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            assertTrue("Expected alphabetic alphabet to contain letter '" + alphabet + "'.", ModelRandomGenerator.ALPHABETIC_STRING.contains(Character.toString(alphabet)));
        }
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            assertTrue("Expected alphabetic alphabet to contain letter '" + alphabet + "'.", ModelRandomGenerator.ALPHABETIC_STRING.contains(Character.toString(alphabet)));
        }
    }

    @Test
    @Repeat(100)
    public void testRandomString() {
        int expectedLength = RANDOM.nextInt(100) + 1;
        String expectedAlphabet = ModelRandomGenerator.ALPHANUMERIC_STRING;
        String randomString = ModelRandomGenerator.randomString(expectedLength);

        this.checkRandomString(randomString, expectedLength, expectedAlphabet);
    }

    @Test
    @Repeat(100)
    public void testRandomStringAlphabet() {
        int expectedLength = RANDOM.nextInt(100) + 1;
        String expectedAlphabet = ModelRandomGenerator.randomString(10);
        String randomString = ModelRandomGenerator.randomString(expectedLength, expectedAlphabet);

        this.checkRandomString(randomString, expectedLength, expectedAlphabet);
    }

    @Test
    @Repeat(100)
    public void testRandomInt() {
        int expectedMinValue = Integer.MIN_VALUE;
        int expectedMaxValue = Integer.MAX_VALUE;
        int randomInt = ModelRandomGenerator.randomInt();

        this.checkRandomInt(randomInt, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomIntUpperBound() {
        int expectedMinValue = Integer.MIN_VALUE;
        int expectedMaxValue = 0; // expected max value >= 1.
        while (expectedMaxValue <= 0) {
            expectedMaxValue = RANDOM.nextInt();
        }
        assertTrue("expMax=" + expectedMaxValue, expectedMaxValue >= 1);
        int randomInt = ModelRandomGenerator.randomInt(expectedMaxValue);

        this.checkRandomInt(randomInt, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomIntInRange() {
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

    @Test
    @Repeat(100)
    public void testRandomLong() {
        long expectedMinValue = Long.MIN_VALUE;
        long expectedMaxValue = Long.MAX_VALUE;
        long randomLong = ModelRandomGenerator.randomLong();

        this.checkRandomLong(randomLong, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLongUpperBound() {
        long expectedMinValue = Long.MIN_VALUE;
        long expectedMaxValue = 0; // expected max value >= 1.
        while (expectedMaxValue <= 0) {
            expectedMaxValue = RANDOM.nextLong();
        }
        assertTrue("expMax=" + expectedMaxValue, expectedMaxValue >= 1);
        long randomLong = ModelRandomGenerator.randomLong(expectedMaxValue);

        this.checkRandomLong(randomLong, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLongInRange() {
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

    @Test
    @Repeat(100)
    public void testRandomBoolean() {
        boolean randomBoolean = ModelRandomGenerator.randomBoolean();

        // The boolean value should be generated.
        assertNotNull(Boolean.valueOf(randomBoolean));
    }

    @Test
    @Repeat(100)
    public void testRandomFloat() {
        float expectedMinValue = 0.0f;
        float expectedMaxValue = 1.0f;
        float randomFloat = ModelRandomGenerator.randomFloat();

        this.checkRandomFloat(randomFloat, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomDouble() {
        double expectedMinValue = 0.0d;
        double expectedMaxValue = 1.0d;
        double randomDouble = ModelRandomGenerator.randomDouble();

        this.checkRandomDouble(randomDouble, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLocalDate() {
        LocalDate expectedMinValue = LocalDate.of(1970, Month.JANUARY, 1);
        LocalDate expectedMaxValue = LocalDate.now();
        LocalDate randomLocalDate = ModelRandomGenerator.randomLocalDate();

        this.checkRandomLocalDate(randomLocalDate, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLocalDateInRange() {
        LocalDate expectedMinValue = ModelRandomGenerator.randomLocalDate();
        LocalDate expectedMaxValue = ModelRandomGenerator.randomLocalDate();
        while (!expectedMaxValue.isAfter(expectedMinValue)) {
            expectedMaxValue = ModelRandomGenerator.randomLocalDate();
        }
        LocalDate randomLocalDate = ModelRandomGenerator.randomLocalDate(expectedMinValue, expectedMaxValue);

        this.checkRandomLocalDate(randomLocalDate, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLocalDateTime() {
        LocalDateTime expectedMinValue = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0);
        LocalDateTime expectedMaxValue = LocalDateTime.now();
        LocalDateTime randomLocalDateTime = ModelRandomGenerator.randomLocalDateTime();

        this.checkRandomLocalDateTime(randomLocalDateTime, expectedMinValue, expectedMaxValue);
    }

    @Test
    @Repeat(100)
    public void testRandomLocalDateTimeInRange() {
        LocalDateTime expectedMinValue = ModelRandomGenerator.randomLocalDateTime();
        LocalDateTime expectedMaxValue = ModelRandomGenerator.randomLocalDateTime();
        while (!expectedMaxValue.isAfter(expectedMinValue)) {
            expectedMaxValue = ModelRandomGenerator.randomLocalDateTime();
        }
        LocalDateTime randomLocalDateTime = ModelRandomGenerator.randomLocalDateTime(expectedMinValue, expectedMaxValue);

        this.checkRandomLocalDateTime(randomLocalDateTime, expectedMinValue, expectedMaxValue);
    }

    private void checkRandomInt(int randomInt, int expectedMinValue, int expectedMaxValue) {
        // The integer value should be generated.
        assertNotNull(Integer.valueOf(randomInt));

        // The integer value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomInt).append(".");
        assertTrue(msg.toString(), expectedMinValue <= randomInt);
        assertTrue(msg.toString(), randomInt <= expectedMaxValue);
    }

    private void checkRandomLong(long randomLong, long expectedMinValue, long expectedMaxValue) {
        // The long value should be generated.
        assertNotNull(Long.valueOf(randomLong));

        // The long value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomLong).append(".");
        assertTrue(msg.toString(), expectedMinValue <= randomLong);
        assertTrue(msg.toString(), randomLong <= expectedMaxValue);
    }

    private void checkRandomFloat(float randomFloat, float expectedMinValue, float expectedMaxValue) {
        // The float value should be generated.
        assertNotNull(Float.valueOf(randomFloat));

        // The float value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomFloat).append(".");
        assertTrue(msg.toString(), expectedMinValue <= randomFloat);
        assertTrue(msg.toString(), randomFloat < expectedMaxValue);
    }

    private void checkRandomDouble(double randomDouble, double expectedMinValue, double expectedMaxValue) {
        // The double value should be generated.
        assertNotNull(Double.valueOf(randomDouble));

        // The double value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The value should be between ").append(expectedMinValue).append(" and ")
                .append(expectedMaxValue).append(" but was ").append(randomDouble).append(".");
        assertTrue(msg.toString(), expectedMinValue <= randomDouble);
        assertTrue(msg.toString(), randomDouble < expectedMaxValue);
    }

    private void checkRandomLocalDate(LocalDate randomLocalDate, LocalDate expectedMinLocalDate,
                                      LocalDate expectedMaxLocalDate) {
        // The local date value should be generated.
        assertNotNull(randomLocalDate);

        // The local date value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The date should be between ").append(expectedMinLocalDate).append(" and ")
                .append(expectedMaxLocalDate).append(" but was ").append(randomLocalDate).append(".");
        assertTrue(msg.toString(), expectedMinLocalDate.isBefore(randomLocalDate)
                || expectedMinLocalDate.isEqual(randomLocalDate));
        assertTrue(msg.toString(), randomLocalDate.isBefore(expectedMaxLocalDate)
                || randomLocalDate.isEqual(expectedMaxLocalDate));
    }

    private void checkRandomLocalDateTime(LocalDateTime randomLocalDateTime, LocalDateTime expectedMinLocalDateTime,
                                          LocalDateTime expectedMaxLocalDateTime) {
        // The local date value should be generated.
        assertNotNull(randomLocalDateTime);

        // The local date value should be in a given range
        StringBuilder msg = new StringBuilder();
        msg.append("The date should be between ").append(expectedMinLocalDateTime).append(" and ")
                .append(expectedMaxLocalDateTime).append(" but was ").append(randomLocalDateTime).append(".");
        assertTrue(msg.toString(), expectedMinLocalDateTime.isBefore(randomLocalDateTime)
                || expectedMinLocalDateTime.isEqual(randomLocalDateTime));
        assertTrue(msg.toString(), randomLocalDateTime.isBefore(expectedMaxLocalDateTime)
                || randomLocalDateTime.isEqual(expectedMaxLocalDateTime));
    }

    private void checkRandomString(String randomString, int expectedLength, String expectedAlphabet) {
        // A string should be generated.
        assertNotNull(randomString);

        // The string should have the correct length.
        String msgLengthTest = "The random string '" + randomString + "' should have length " +
                expectedLength + ". The lenght is " + randomString.length() + ".";
        assertEquals(msgLengthTest, randomString.length(), expectedLength);

        // The string should consist of letters of a given alphabet.
        for (int i = 0; i < randomString.length(); i++) {
            CharSequence letter = String.valueOf(randomString.charAt(i));

            String msgAlphabetTest = "The random string '" + randomString + "' should only consist of " +
                    "letters of the alphabet '" + expectedAlphabet + "'. We found the letter '" +
                    letter + "'.";
            assertTrue(msgAlphabetTest, expectedAlphabet.contains(letter));
        }
    }
}
