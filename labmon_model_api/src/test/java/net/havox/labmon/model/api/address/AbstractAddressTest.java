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

import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

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
     * @throws Exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Provides a {@link City} instance.
     *
     * @return the instance
     * @throws Exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Test modification of the street.
     *
     * Given:
     * When:
     * Then:
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyStreet() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String streetName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Address objectUnderTest = getAddress();
        objectUnderTest.setStreet(streetName);
        Assertions.assertEquals(streetName, objectUnderTest.getStreet());
    }

    /**
     * Tests the modification of the house number.
     *
     * Given:
     * When:
     * Then:
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyHouseNumber() throws Exception {
        int number = ModelRandomGenerator.randomIntInRange(1, 9999);
        String letter = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(0, 1),
                ModelRandomGenerator.ALPHABETIC_STRING);
        String houseNumber = "" + number + letter;

        Address objectUnderTest = getAddress();
        objectUnderTest.setHouseNumber(houseNumber);
        Assertions.assertEquals(houseNumber, objectUnderTest.getHouseNumber());
    }

    /**
     * Tests the modification of the city.
     *
     * Given:
     * When:
     * Then:
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyCity() throws Exception {
        City city = getCity();

        Address objectUnderTest = getAddress();
        objectUnderTest.setCity(city);
        Assertions.assertEquals(city, objectUnderTest.getCity());
    }
}
