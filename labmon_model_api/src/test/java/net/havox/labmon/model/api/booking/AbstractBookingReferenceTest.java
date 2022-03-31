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

package net.havox.labmon.model.api.booking;

import net.havox.labmon.model.utils.validation.booking.BookingReferenceValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.net.URL;

/**
 * Abstract implementation of API test of {@link BookingReference}.
 *
 * @author Christian Otto
 */
public abstract class AbstractBookingReferenceTest {

    /**
     * Provides a {@link BookingReference} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract BookingReference getBookingReference() throws Exception;

    /**
     * Tests the modification of the name.
     * <p>
     * Given: A {@link BookingReference} instance
     * When: modifying the multiplier attribute ({@link BookingReference#setName(String)} )
     * Then: than the name attribute ({@link BookingReference#getName()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyMultiplier() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING;
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        BookingReference objectUnderTest = getBookingReference();
        String oldName = objectUnderTest.getName();

        while (name.equals(oldName)) {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(oldName, name);

        objectUnderTest.setName(name);
        Assertions.assertEquals(name, objectUnderTest.getName());
        Assertions.assertNotEquals(oldName, objectUnderTest.getName());
    }

    /**
     * Tests the modification of the booking reference regex.
     * <p>
     * Given: A {@link BookingReference} instance
     * When: modifying the multiplier attribute ({@link BookingReference#setBookingReferenceRegex(String)} )
     * Then: than the name attribute ({@link BookingReference#getBookingReferenceRegex()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyBookingReferenceRegex() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + "#${}";
        String regex = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(5, 50), alphabet);

        BookingReference objectUnderTest = getBookingReference();
        String oldRegex = objectUnderTest.getBookingReferenceRegex();

        while (regex.equals(oldRegex)) {
            regex = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(5, 50), alphabet);
        }
        Assertions.assertNotEquals(oldRegex, regex);

        objectUnderTest.setBookingReferenceRegex(regex);
        Assertions.assertEquals(regex, objectUnderTest.getBookingReferenceRegex());
        Assertions.assertNotEquals(oldRegex, objectUnderTest.getBookingReferenceRegex());
    }

    /**
     * Tests the modification of the booking reference base url.
     * <p>
     * Given: A {@link BookingReference} instance
     * When: modifying the multiplier attribute ({@link BookingReference#setBookingReferenceBaseUrl(URL)} )
     * Then: than the name attribute ({@link BookingReference#getBookingReferenceBaseUrl()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyBookingReferenceBaseUrl() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING;
        URL url = new URL("https://www." + ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 25), alphabet) + ".com");

        BookingReference objectUnderTest = getBookingReference();
        URL oldUrl = objectUnderTest.getBookingReferenceBaseUrl();

        while (url.equals(oldUrl)) {
            url = new URL("https://www." + ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 25), alphabet) + ".com");
        }
        Assertions.assertNotEquals(oldUrl, url);

        objectUnderTest.setBookingReferenceBaseUrl(url);
        Assertions.assertEquals(url, objectUnderTest.getBookingReferenceBaseUrl());
        Assertions.assertNotEquals(oldUrl, objectUnderTest.getBookingReferenceBaseUrl());
    }

    /**
     * Tests the modification of the booking reference sub url.
     * <p>
     * Given: A {@link BookingReference} instance
     * When: modifying the multiplier attribute ({@link BookingReference#setBookingReferenceSubUrl(String)}  )
     * Then: than the name attribute ({@link BookingReference#getBookingReferenceSubUrl()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyBookingReferenceSubUrl() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + "?=#&{}";
        String subUrl = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(10, 50), alphabet);

        BookingReference objectUnderTest = getBookingReference();
        String oldSubUrl = objectUnderTest.getBookingReferenceSubUrl();

        while (subUrl.equals(oldSubUrl)) {
            subUrl = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(10, 50), alphabet);
        }
        Assertions.assertNotEquals(oldSubUrl, subUrl);

        objectUnderTest.setBookingReferenceSubUrl(subUrl);
        Assertions.assertEquals(subUrl, objectUnderTest.getBookingReferenceSubUrl());
        Assertions.assertNotEquals(oldSubUrl, objectUnderTest.getBookingReferenceSubUrl());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link BookingReference} instance
     * When: calling {@link BookingReference#getValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link BookingReferenceValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        BookingReference objectUnderTest = getBookingReference();

        Assertions.assertNotNull(objectUnderTest.getValidator());
        Assertions.assertTrue(objectUnderTest.getValidator() instanceof BookingReferenceValidator);
    }
}
