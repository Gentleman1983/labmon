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

import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Abstract implementation of API test of {@link Credentials}.
 *
 * @author Christian Otto
 */
public abstract class AbstractCredentialTest {
    /**
     * Provides an {@link Credentials} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Credentials getCredentials() throws Exception;

    /**
     * Provides an {@link PasswordAuthentication} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract PasswordAuthentication getPasswordAuthentication() throws Exception;

    /**
     * Tests if changes on the username property work properly.
     *
     * Given:
     * When:
     * Then:
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserName() throws Exception {
        Credentials instanceUnderTest = getCredentials();
        String instanceOriginalName = instanceUnderTest.getUserName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getUserName());

        instanceUnderTest.setUserName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getUserName());
        Assertions.assertEquals(name, instanceUnderTest.getUserName());
    }

    /**
     * Tests if changes on the authentication method property work properly.
     *
     * Given:
     * When:
     * Then:
     *
     * @throws Exception
     */
    @RepeatedTest( 25 )
    public void testModifyAuthenticationMethod() throws Exception
    {
        AuthenticationMethod auth = getPasswordAuthentication();

        Credentials objectUnderTest = getCredentials();
        objectUnderTest.setAuthentication(auth);
        Assertions.assertEquals( auth, objectUnderTest.getAuthentication() );
    }
}
