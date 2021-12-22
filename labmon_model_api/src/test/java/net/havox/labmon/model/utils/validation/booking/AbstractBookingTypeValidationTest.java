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

package net.havox.labmon.model.utils.validation.booking;

import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Abstract implementation of {@link BookingTypeValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractBookingTypeValidationTest {
    /**
     * Provides an {@link BookingType} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract BookingType getBookingType() throws Exception;

    /**
     * Provides an {@link BookingTypeValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract BookingTypeValidator getBookingTypeValidator() throws Exception;

    /**
     * Tests if a valid {@link BookingType} entity is valid.
     * <p>
     * Given: a randomized {@link BookingType} entity
     * And: having all necessary attributes
     * When: validating the {@link BookingType} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidBookingTypeInstanceIsValid() throws Exception {
        BookingType instanceUnderTest = getValidBookingTypeInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link BookingType} entity is invalid.
     * <p>
     * Given: a {@code null} {@link BookingType} entity
     * When: validating the {@link BookingType} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullBookingTypeIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link BookingType} entity missing the name is invalid.
     * <p>
     * Given: a randomized {@link BookingType} entity
     * And: missing a name attribute
     * When: validating the {@link BookingType} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testBookingTypeWithoutNameIsInvalid() throws Exception {
        BookingType instanceUnderTest = getValidBookingTypeInstance();

        instanceUnderTest.setName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link BookingType} entity with empty the name is invalid.
     * <p>
     * Given: a randomized {@link BookingType} entity
     * And: having an empty name attribute
     * When: validating the {@link BookingType} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testBookingTypeWithEmptyNameIsInvalid() throws Exception {
        BookingType instanceUnderTest = getValidBookingTypeInstance();

        instanceUnderTest.setName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link BookingType} entity missing the name is invalid.
     * <p>
     * Given: a randomized {@link BookingType} entity
     * And: missing the multiplier attribute
     * When: validating the {@link BookingType} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testBookingTypeWithoutMultiplierIsInvalid() throws Exception {
        BookingType instanceUnderTest = getValidBookingTypeInstance();

        instanceUnderTest.setMultiplier(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link BookingType} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(BookingType instanceUnderTest, Boolean expectedValid) throws Exception {
        BookingTypeValidator validator = getBookingTypeValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest), "Expected the country '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") + "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link BookingType} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private BookingType getValidBookingTypeInstance() throws Exception {
        BookingType instance = getBookingType();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setMultiplier(BigDecimal.valueOf(ModelRandomGenerator.randomDouble()));

        return instance;
    }
}
