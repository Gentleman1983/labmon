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

package net.havox.labmon.model.utils.validation.address;

import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link CountryValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractCountryValidationTest {
    /**
     * Provides an {@link Country} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Provides an {@link CountryValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract CountryValidator getCountryValidator() throws Exception;

    /**
     * Tests if a valid {@link Country} entity is valid.
     * <p>
     * Given: a randomized {@link Country} entity
     * And: having all necessary attributes
     * When: validating the {@link Country} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidCountryInstanceIsValid() throws Exception {
        Country instanceUnderTest = getValidCountryInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Country} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Country} entity
     * When: validating the {@link Country} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullCountryIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Country} entity missing the name is invalid.
     * <p>
     * Given: a randomized {@link Country} entity
     * And: missing a name attribute
     * When: validating the {@link Country} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testCountryWithoutNameIsInvalid() throws Exception {
        Country instanceUnderTest = getValidCountryInstance();

        instanceUnderTest.setName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Country} entity with empty the name is invalid.
     * <p>
     * Given: a randomized {@link Country} entity
     * And: having an empty name attribute
     * When: validating the {@link Country} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testCountryWithEmptyNameIsInvalid() throws Exception {
        Country instanceUnderTest = getValidCountryInstance();

        instanceUnderTest.setName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Country} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Country instanceUnderTest, Boolean expectedValid) throws Exception {
        CountryValidator validator = getCountryValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest), "Expected the country '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") + "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link Country} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Country getValidCountryInstance() throws Exception {
        Country instance = getCountry();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
