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
 * Abstract implementation of API test of {@link Country}.
 *
 * @author Christian Otto
 */
public abstract class AbstractCountryTest {
    /**
     * Provides a {@link Country} instance.
     *
     * @return the instance
     * @throws Exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Tests the modification of the country name.
     *
     * @throws Exception
     */
    @RepeatedTest( 25 )
    public void testModifyName() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + " -";
        String name = ModelRandomGenerator.randomString( ModelRandomGenerator.randomIntInRange( 1, 50 ), alphabet );

        Country objectUnderTest = getCountry();
        objectUnderTest.setName( name );
        Assertions.assertEquals( name, objectUnderTest.getName() );
    }
}
