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

import net.havox.labmon.model.api.address.City;
import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * Abstract implementation of {@link CityValidator} test.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractCityValidationTest {
    @Mock private Country mockedCountry;
    @Mock private CountryValidator mockedCountryValidator;

    /**
     * Provides an {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides an {@link CityValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract CityValidator getCityValidator() throws Exception;

    /**
     * Provides an {@link CountryValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract CountryValidator getCountryValidator() throws Exception;

    /**
     * Provides an {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Tests if a valid {@link City} entity is valid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: having all necessary attributes
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidCountryInstanceIsValid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link City} entity is invalid.
     * <p>
     * Given: a {@code null} {@link City} entity
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testNullCityIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link City} entity missing the name is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: missing a name attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithoutNameIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        instanceUnderTest.setName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity with empty the name is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: having an empty name attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithEmptyNameIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        instanceUnderTest.setName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity missing the ZIP code is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: missing a ZIP code attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithoutZipCodeIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        instanceUnderTest.setZipCode(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity with empty the ZIP code is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: having an empty ZIP code attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithEmptyZipCodeIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        instanceUnderTest.setZipCode("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity missing the country is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: missing a country attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithoutCountryIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        Mockito.when(mockedCountryValidator.validate(Mockito.any())).thenReturn(getCountryValidator().validate(null));
        Mockito.when(mockedCountry.getValidator()).thenReturn(mockedCountryValidator);

        instanceUnderTest.setCountry(mockedCountry);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity with empty the country is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: having an invalid country attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testCountryWithInvalidCountryIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        Mockito.when(mockedCountryValidator.validate(Mockito.any())).thenReturn(List.of("Mocked error validation result"));
        Mockito.when(mockedCountry.getValidator()).thenReturn(mockedCountryValidator);

        instanceUnderTest.setCountry(mockedCountry);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link City} entity missing the country is invalid.
     * <p>
     * Given: a randomized {@link City} entity
     * And: missing a country attribute
     * When: validating the {@link City} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testCityWithNullCountryIsInvalid() throws Exception {
        City instanceUnderTest = getValidCityInstance();

        instanceUnderTest.setCountry(null);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link City} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(City instanceUnderTest, Boolean expectedValid) throws Exception {
        CityValidator validator = getCityValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the city '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
    }

    /**
     * Provides a valid {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private City getValidCityInstance() throws Exception {
        City instance = getCity();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setZipCode(Integer.toString(ModelRandomGenerator.randomIntInRange(10000, 99999)));
        instance.setCountry(getValidCountryInstance());

        return instance;
    }

    /**
     * Provides a valid {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Country getValidCountryInstance() throws Exception {
        Country instance = getCountry();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
