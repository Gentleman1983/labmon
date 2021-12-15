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

package net.havox.labmon.model.api.permissions;

import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Abstract implementation of API test of {@link Permission}.
 *
 * @author Christian Otto
 */
public abstract class AbstractPermissionTest {

    /**
     * Provides a {@link Permission} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Permission getPermission() throws Exception;

    /**
     * Tests if changes on the name property work properly.
     * <p>
     * Given: A {@link Permission} instance
     * When: modifying the name attribute ({@link Permission#setName(String)})
     * Then: than the name attribute ({@link Permission#getName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyPermissionName() throws Exception {
        Permission instanceUnderTest = getPermission();
        String instanceOriginalName = instanceUnderTest.getName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getName());

        instanceUnderTest.setName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getName());
        Assertions.assertEquals(name, instanceUnderTest.getName());
    }
}
