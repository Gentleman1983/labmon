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

import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link PasswordAuthentication}.
 *
 * @author Christian Otto
 */
public abstract class AbstractPasswordAuthenticationTest {
    /**
     * Provides an {@link PasswordAuthentication} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract PasswordAuthentication getPasswordAuthentication() throws Exception;

    /**
     * Tests if changes on the pass phrase property work properly.
     * <p>
     * Given: A {@link PasswordAuthentication} instance
     * When: modifying the pass phrase attribute ({@link PasswordAuthentication#setPassPhrase(String)})
     * Then: than the pass phrase attribute ({@link PasswordAuthentication#getPassPhrase()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyUserPassPhrase() throws Exception {
        PasswordAuthentication instanceUnderTest = getPasswordAuthentication();
        String instanceOriginalPassPhrase = instanceUnderTest.getPassPhrase();

        String newPassPhrase;
        do {
            newPassPhrase = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING);
        } while (newPassPhrase.equals(instanceOriginalPassPhrase));

        Assertions.assertEquals(instanceOriginalPassPhrase, instanceUnderTest.getPassPhrase());

        instanceUnderTest.setPassPhrase(newPassPhrase);

        Assertions.assertNotEquals(instanceOriginalPassPhrase, instanceUnderTest.getPassPhrase());
        Assertions.assertEquals(newPassPhrase, instanceUnderTest.getPassPhrase());
    }

    /**
     * Tests if authentication fits with corresponding passwords.
     * <p>
     * Given: A {@link PasswordAuthentication} instance
     * And: using a valid authentication
     * When: trying to authenticate ({@link PasswordAuthentication#authenticate(AuthenticationMethod)})
     * Then: than the authentication request shall be valid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testPasswordAuthenticationValid() throws Exception {
        PasswordAuthentication instanceUnderTest = getRandomPassphraseAuthentication();

        PasswordAuthentication other = getPasswordAuthentication();
        Assertions.assertNotEquals(instanceUnderTest.getPassPhrase(), other.getPassPhrase());

        other.setPassPhrase(instanceUnderTest.getPassPhrase());
        Assertions.assertEquals(instanceUnderTest.getPassPhrase(), other.getPassPhrase());
        Assertions.assertTrue(instanceUnderTest.authenticate(other));
    }

    /**
     * Tests if authentication does not fit with different passwords.
     * <p>
     * Given: A {@link PasswordAuthentication} instance
     * And: using an invalid authentication
     * When: trying to authenticate ({@link PasswordAuthentication#authenticate(AuthenticationMethod)})
     * Then: than the authentication request shall be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testPasswordAuthenticationInvalid() throws Exception {
        PasswordAuthentication instanceUnderTest = getRandomPassphraseAuthentication();

        PasswordAuthentication other = getRandomPassphraseAuthentication();
        while (other.getPassPhrase().equals(instanceUnderTest.getPassPhrase())) {
            other = getRandomPassphraseAuthentication();
        }
        Assertions.assertNotEquals(instanceUnderTest.getPassPhrase(), other.getPassPhrase());
        Assertions.assertFalse(instanceUnderTest.authenticate(other));
    }

    /**
     * Tests if authentication does not fit with different {@link AuthenticationMethod}s.
     * <p>
     * Given: A {@link PasswordAuthentication} instance
     * And: using a wrong {@link AuthenticationMethod}
     * When: trying to authenticate ({@link PasswordAuthentication#authenticate(AuthenticationMethod)})
     * Then: than the authentication request shall be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testPasswordAuthenticationInvalidWrongType() throws Exception {
        PasswordAuthentication instanceUnderTest = getRandomPassphraseAuthentication();
        AuthenticationMethod other = new InvalidAuthenticationMethod();

        Assertions.assertFalse(instanceUnderTest.authenticate(other));
    }

    /**
     * Method providing a {@link PasswordAuthentication} instance with randomized pass phrase.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    private PasswordAuthentication getRandomPassphraseAuthentication() throws Exception {
        PasswordAuthentication instance = getPasswordAuthentication();

        instance.setPassPhrase(ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING));

        return instance;
    }

    /**
     * Simple class needed for {@link AbstractPasswordAuthenticationTest#testPasswordAuthenticationInvalidWrongType()}.
     */
    private static class InvalidAuthenticationMethod extends AbstractChangeAwareAndIdentifiableClass implements AuthenticationMethod {

        @Override
        public boolean authenticate(AuthenticationMethod authenticationMethod) {
            return false;
        }
    }
}
