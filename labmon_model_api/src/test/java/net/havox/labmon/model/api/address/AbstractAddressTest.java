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

import net.havox.labmon.model.utils.validation.address.AddressValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link Address}.
 *
 * @author Christian Otto
 */
public abstract class AbstractAddressTest {
    /**
     * Provides an {@link Address} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Provides a {@link City} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Address} instance
     * When: modifying the street attribute ({@link Address#setStreet(String)})
     * Then: than the street attribute ({@link Address#getStreet()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyStreet() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String streetName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Address objectUnderTest = getAddress();
        String oldStreet = objectUnderTest.getStreet();

        while (streetName.equals(oldStreet)) {
            streetName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(streetName, oldStreet);

        objectUnderTest.setStreet(streetName);
        Assertions.assertEquals(streetName, objectUnderTest.getStreet());
        Assertions.assertNotEquals(oldStreet, objectUnderTest.getStreet());
    }

    /**
     * Tests the modification of the house number.
     * <p>
     * Given: An {@link Address} instance
     * When: modifying the house number attribute ({@link Address#setHouseNumber(String)})
     * Then: than the street attribute ({@link Address#getHouseNumber()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyHouseNumber() throws Exception {
        int number = ModelRandomGenerator.randomIntInRange(1, 9999);
        String letter = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(0, 1), ModelRandomGenerator.ALPHABETIC_STRING);
        String houseNumber = "" + number + letter;

        Address objectUnderTest = getAddress();
        String oldHouseNumber = objectUnderTest.getHouseNumber();

        while (houseNumber.equals(oldHouseNumber)) {
            number = ModelRandomGenerator.randomIntInRange(1, 9999);
            letter = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(0, 1), ModelRandomGenerator.ALPHABETIC_STRING);
            houseNumber = "" + number + letter;
        }
        Assertions.assertNotEquals(oldHouseNumber, houseNumber);

        objectUnderTest.setHouseNumber(houseNumber);
        Assertions.assertEquals(houseNumber, objectUnderTest.getHouseNumber());
        Assertions.assertNotEquals(oldHouseNumber, objectUnderTest.getHouseNumber());
    }

    /**
     * Tests the modification of the city.
     * <p>
     * Given: An {@link Address} instance
     * When: modifying the house number attribute ({@link Address#setCity(City)})
     * Then: than the street attribute ({@link Address#getCity()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyCity() throws Exception {
        City city = getCity();

        Address objectUnderTest = getAddress();
        City oldCity = objectUnderTest.getCity();
        while (city.equals(oldCity)) {
            city = getCity();
        }
        Assertions.assertNotEquals(oldCity, city);

        objectUnderTest.setCity(city);
        Assertions.assertEquals(city, objectUnderTest.getCity());
        Assertions.assertNotEquals(oldCity, objectUnderTest.getCity());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link Address} instance
     * When: calling {@link Address#getValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link AddressValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Address objectUnderTest = getAddress();

        Assertions.assertNotNull(objectUnderTest.getValidator());
        Assertions.assertTrue(objectUnderTest.getValidator() instanceof AddressValidator);
    }
}
