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
     * @throws Exception
     */
    public abstract PasswordAuthentication getPasswordAuthentication() throws Exception;

    /**
     * Tests if changes on the pass phrase property work properly.
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserFirstName() throws Exception {
        PasswordAuthentication instanceUnderTest = getPasswordAuthentication();
        String instanceOriginalPassPhrase = instanceUnderTest.getPassPhrase();

        String newPassPhrase;
        do {
            newPassPhrase = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (newPassPhrase.equals(instanceOriginalPassPhrase));

        Assertions.assertEquals(instanceOriginalPassPhrase, instanceUnderTest.getPassPhrase());

        instanceUnderTest.setPassPhrase(newPassPhrase);

        Assertions.assertNotEquals(instanceOriginalPassPhrase, instanceUnderTest.getPassPhrase());
        Assertions.assertEquals(newPassPhrase, instanceUnderTest.getPassPhrase());
    }

    /**
     * Tests if authentication fits with corresponding passwords.
     *
     * @throws Exception
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
     *
     * @throws Exception
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
     *
     * @throws Exception
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
     * @throws Exception
     */
    private PasswordAuthentication getRandomPassphraseAuthentication() throws Exception {
        PasswordAuthentication instance = getPasswordAuthentication();

        instance.setPassPhrase(ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                ModelRandomGenerator.ALPHANUMERIC_STRING));

        return instance;
    }

    /**
     * Simple class needed for {@link AbstractPasswordAuthenticationTest#testPasswordAuthenticationInvalidWrongType()}.
     */
    private class InvalidAuthenticationMethod extends AbstractChangeAwareAndIdentifiableClass implements AuthenticationMethod {

        @Override
        public boolean authenticate(AuthenticationMethod authenticationMethod) {
            return false;
        }
    }
}
