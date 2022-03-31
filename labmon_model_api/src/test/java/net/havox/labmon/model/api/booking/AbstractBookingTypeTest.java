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

import net.havox.labmon.model.utils.validation.booking.BookingTypeValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Abstract implementation of API test of {@link BookingType}.
 *
 * @author Christian Otto
 */
public abstract class AbstractBookingTypeTest {
    /**
     * Provides a {@link BookingType} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract BookingType getBookingType() throws Exception;

    /**
     * Tests the modification of the name.
     * <p>
     * Given: A {@link BookingType} instance
     * When: modifying the name attribute ({@link BookingType#setName(String)})
     * Then: than the name attribute ({@link BookingType#getName()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyName() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + " -";
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        BookingType objectUnderTest = getBookingType();
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
     * Tests the modification of the multiplier.
     * <p>
     * Given: A {@link BookingType} instance
     * When: modifying the multiplier attribute ({@link BookingType#setMultiplier(BigDecimal)} )
     * Then: than the name attribute ({@link BookingType#getMultiplier()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyMultiplier() throws Exception {
        BigDecimal multiplier = BigDecimal.valueOf(ModelRandomGenerator.randomDouble());

        BookingType objectUnderTest = getBookingType();
        BigDecimal oldMultiplier = objectUnderTest.getMultiplier();

        while (multiplier.equals(oldMultiplier)) {
            multiplier = BigDecimal.valueOf(ModelRandomGenerator.randomDouble());
        }
        Assertions.assertNotEquals(oldMultiplier, multiplier);

        objectUnderTest.setMultiplier(multiplier);
        Assertions.assertEquals(multiplier, objectUnderTest.getMultiplier());
        Assertions.assertNotEquals(oldMultiplier, objectUnderTest.getMultiplier());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link BookingType} instance
     * When: calling {@link BookingType#getValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link BookingTypeValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        BookingType objectUnderTest = getBookingType();

        Assertions.assertNotNull(objectUnderTest.getValidator());
        Assertions.assertTrue(objectUnderTest.getValidator() instanceof BookingTypeValidator);
    }
}
