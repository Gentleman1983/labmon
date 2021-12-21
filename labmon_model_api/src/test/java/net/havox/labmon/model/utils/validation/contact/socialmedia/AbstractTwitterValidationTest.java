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

package net.havox.labmon.model.utils.validation.contact.socialmedia;

import net.havox.labmon.model.api.contact.socialmedia.Twitter;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link TwitterValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractTwitterValidationTest {
    private final static String usernameAlphabet = ModelRandomGenerator.ALPHANUMERIC_STRING + "_";

    /**
     * Provides an {@link Twitter} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Twitter getTwitter() throws Exception;

    /**
     * Provides an {@link TwitterValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract TwitterValidator getTwitterValidator() throws Exception;

    /**
     * Tests if a valid {@link Twitter} entity is valid.
     * <p>
     * Given: a randomized {@link Twitter} entity
     * And: having all necessary attributes
     * When: validating the {@link Twitter} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidTwitterInstanceIsValid() throws Exception {
        Twitter instanceUnderTest = getValidTwitterInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Twitter} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Twitter} entity
     * When: validating the {@link Twitter} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testNullTwitterIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Twitter} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link Twitter} entity
     * And: missing a username attribute
     * When: validating the {@link Twitter} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testTwitterWithoutUserNameIsInvalid() throws Exception {
        Twitter instanceUnderTest = getValidTwitterInstance();

        instanceUnderTest.setUserName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Twitter} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Twitter} entity
     * And: having an empty username attribute
     * When: validating the {@link Twitter} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testTwitterWithEmptyUserNameIsInvalid() throws Exception {
        Twitter instanceUnderTest = getValidTwitterInstance();

        instanceUnderTest.setUserName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Twitter} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Twitter} entity
     * And: having an sixteen letter username attribute
     * When: validating the {@link Twitter} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testTwitterWithSixteenLetterUserNameIsInvalid() throws Exception {
        Twitter instanceUnderTest = getValidTwitterInstance();

        instanceUnderTest.setUserName(ModelRandomGenerator.randomString(16, usernameAlphabet));

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Twitter} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Twitter instanceUnderTest, Boolean expectedValid) throws Exception {
        TwitterValidator validator = getTwitterValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(),
                    "Expected the user" + (expectedValid ? "" : " not") +
                            "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link Twitter} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Twitter getValidTwitterInstance() throws Exception {
        Twitter instance = getTwitter();

        instance.setUserName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 15),
                usernameAlphabet));

        return instance;
    }
}
