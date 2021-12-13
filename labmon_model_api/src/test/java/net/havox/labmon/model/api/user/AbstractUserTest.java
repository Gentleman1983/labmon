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

package net.havox.labmon.model.api.user;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Abstract implementation of API test of {@link User}.
 *
 * @author Christian Otto
 */
public abstract class AbstractUserTest {
    /**
     * Provides an {@link User} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract User getUser() throws Exception;

    /**
     * Provides an {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Tests if changes on the first name property work properly.
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserFirstName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getFirstName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getFirstName());

        instanceUnderTest.setFirstName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getFirstName());
        Assertions.assertEquals(name, instanceUnderTest.getFirstName());
    }

    /**
     * Tests if changes on the middle name property work properly.
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserMiddleName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getMiddleName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getMiddleName());

        instanceUnderTest.setMiddleName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getMiddleName());
        Assertions.assertEquals(name, instanceUnderTest.getMiddleName());
    }

    /**
     * Tests if changes on the last name property work properly.
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserLastName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getLastName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getLastName());

        instanceUnderTest.setLastName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getLastName());
        Assertions.assertEquals(name, instanceUnderTest.getLastName());
    }

    /**
     * User Story BM015 acceptance criteria 01 ("An employer has a name, address and contact options.").
     *
     * @throws Exception
     */
    @RepeatedTest( 25 )
    public void testModifyAddress() throws Exception
    {
        Address address = getAddress();

        User objectUnderTest = getUser();
        objectUnderTest.setAddress( address );
        Assertions.assertEquals( address, objectUnderTest.getAddress() );
    }
}
