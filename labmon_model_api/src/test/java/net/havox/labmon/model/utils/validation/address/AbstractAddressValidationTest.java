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

import net.havox.labmon.model.api.address.Address;
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
 * Abstract implementation of {@link AddressValidator} test.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractAddressValidationTest {
    @Mock private City mockedCity;
    @Mock private CityValidator mockedCityValidator;

    /**
     * Provides an {@link Address} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Provides an {@link City} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides an {@link Country} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Provides an {@link AddressValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract AddressValidator getAddressValidator() throws Exception;

    /**
     * Provides an {@link CityValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract CityValidator getCityValidator() throws Exception;

    /**
     * Tests if a valid {@link Address} entity is valid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: having all necessary attributes
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidAddressInstanceIsValid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Address} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Address} entity
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullAddressIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Address} entity missing the street is invalid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: missing a street attribute
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testAddressWithoutStreetIsInvalid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        instanceUnderTest.setStreet(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Address} entity with empty the street is invalid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: having an empty street attribute
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testAddressWithEmptyStreetIsInvalid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        instanceUnderTest.setStreet("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Address} entity missing the city is invalid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: missing a city attribute
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testAddressWithoutCityIsInvalid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        Mockito.when(mockedCityValidator.validate(Mockito.any())).thenReturn(getCityValidator().validate(null));
        Mockito.when(mockedCity.getValidator()).thenReturn(mockedCityValidator);

        instanceUnderTest.setCity(mockedCity);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Address} entity with empty the city is invalid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: having an invalid city attribute
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testAddressWithInvalidCityIsInvalid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        Mockito.when(mockedCityValidator.validate(Mockito.any())).thenReturn(List.of("Mocked error validation result"));
        Mockito.when(mockedCity.getValidator()).thenReturn(mockedCityValidator);

        instanceUnderTest.setCity(mockedCity);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Address} entity missing the city is invalid.
     * <p>
     * Given: a randomized {@link Address} entity
     * And: missing a city attribute
     * When: validating the {@link Address} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testAddressWithNullCityIsInvalid() throws Exception {
        Address instanceUnderTest = getValidAddressInstance();

        instanceUnderTest.setCity(null);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Address} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Address instanceUnderTest, Boolean expectedValid) throws Exception {
        AddressValidator validator = getAddressValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the address '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
    }

    /**
     * Provides a valid {@link Address} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Address getValidAddressInstance() throws Exception {
        Address instance = getAddress();

        instance.setStreet(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setHouseNumber(Integer.toString(ModelRandomGenerator.randomInt(9999)));
        instance.setCity(getValidCityInstance());

        return instance;
    }

    /**
     * Provides a valid {@link City} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
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
     * @throws Exception in case of an exception
     */
    private Country getValidCountryInstance() throws Exception {
        Country instance = getCountry();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
