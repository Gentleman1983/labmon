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

package net.havox.labmon.model.api.address;

import net.havox.labmon.model.utils.validation.address.CityValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link City}.
 *
 * @author Christian Otto
 */
public abstract class AbstractCityTest {
    /**
     * Provides a {@link City} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides a {@link Country} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Tests the modification of the ZIP code.
     * <p>
     * Given: A {@link City} instance
     * When: modifying the ZIP code attribute ({@link City#setZipCode(String)})
     * Then: than the ZIP code attribute ({@link City#getZipCode()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyZipCode() throws Exception {
        String zipCode = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(4, 8), ModelRandomGenerator.ALPHANUMERIC_STRING);

        City objectUnderTest = getCity();
        String oldZipCode = objectUnderTest.getZipCode();

        while (zipCode.equals(oldZipCode)) {
            zipCode = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(4, 8), ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        Assertions.assertNotEquals(oldZipCode, zipCode);

        objectUnderTest.setZipCode(zipCode);
        Assertions.assertEquals(zipCode, objectUnderTest.getZipCode());
        Assertions.assertNotEquals(oldZipCode, objectUnderTest.getZipCode());
    }

    /**
     * Tests the modification of the city name.
     * <p>
     * Given: A {@link City} instance
     * When: modifying the name attribute ({@link City#setName(String)})
     * Then: than the name attribute ({@link City#getName()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyName() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + " -";
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        City objectUnderTest = getCity();
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
     * Tests the modifications of the country.
     * <p>
     * Given: A {@link City} instance
     * When: modifying the country attribute ({@link City#setCountry(Country)})
     * Then: than the country attribute ({@link City#getCountry()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyCounty() throws Exception {
        Country country = getCountry();

        City objectUnderTest = getCity();
        Country oldCountry = objectUnderTest.getCountry();

        while (country.equals(oldCountry)) {
            country = getCountry();
        }
        Assertions.assertNotEquals(oldCountry, country);

        objectUnderTest.setCountry(country);
        Assertions.assertEquals(country, objectUnderTest.getCountry());
        Assertions.assertNotEquals(oldCountry, objectUnderTest.getCountry());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link City} instance
     * When: calling {@link City#getValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link CityValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        City objectUnderTest = getCity();

        Assertions.assertNotNull(objectUnderTest.getValidator());
        Assertions.assertTrue(objectUnderTest.getValidator() instanceof CityValidator);
    }
}
