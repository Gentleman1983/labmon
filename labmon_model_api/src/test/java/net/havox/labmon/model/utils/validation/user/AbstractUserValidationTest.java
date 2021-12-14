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

package net.havox.labmon.model.utils.validation.user;

import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link UserValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractUserValidationTest {
    /**
     * Provides an {@link User} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract User getUser() throws Exception;

    /**
     * Provides an {@link UserValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract UserValidator getValidator() throws Exception;

    /**
     * Provides an {@link Credentials} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Credentials getCredentials() throws Exception;

    /**
     * Tests if a valid {@link User} entity is valid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidUserInstanceIsValid() throws Exception {
        User instanceUnderTest = getValidUser();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link User} entity is invalid.
     *
     * @throws Exception
     */
    @Test
    public void testNullUserIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link User} entity missing the first name is invalid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testUserWithoutFirstNameIsInvalid() throws Exception {
        User instanceUnderTest = getValidUser();

        instanceUnderTest.setFirstName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link User} entity with empty the first name is invalid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testUserWithEmptyFirstNameIsInvalid() throws Exception {
        User instanceUnderTest = getValidUser();

        instanceUnderTest.setFirstName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link User} entity missing the last name is invalid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testUserWithoutLastNameIsInvalid() throws Exception {
        User instanceUnderTest = getValidUser();

        instanceUnderTest.setLastName(null);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link User} entity with empty the last name is invalid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testUserWithEmptyLastNameIsInvalid() throws Exception {
        User instanceUnderTest = getValidUser();

        instanceUnderTest.setLastName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link User} entity with {@code null} credentials is invalid.
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testUserWithNullCredentialsIsInvalid() throws Exception {
        User instanceUnderTest = getValidUser();

        instanceUnderTest.setCredentials(null);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link User} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(User instanceUnderTest, Boolean expectedValid) throws Exception {
        UserValidator validator = getValidator();
        Assertions.assertEquals(expectedValid, validator.isUserValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validateUser(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link User} instance.
     *
     * @return a valid instance
     * @throws Exception
     */
    private User getValidUser() throws Exception {
        User user = getUser();

        user.setFirstName(getRandomName(1, 50));
        user.setLastName(getRandomName(1, 50));
        user.setCredentials(getCredentials());

        return user;
    }

    /**
     * Provides a random name of a given length.
     *
     * @param minLength minimal length
     * @param maxLength maximal length
     * @return the name
     */
    private String getRandomName(int minLength, int maxLength) {
        return ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(minLength, maxLength),
                ModelRandomGenerator.ALPHABETIC_STRING);
    }
}
