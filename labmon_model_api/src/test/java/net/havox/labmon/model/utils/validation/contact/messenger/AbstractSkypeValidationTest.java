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

package net.havox.labmon.model.utils.validation.contact.messenger;

import net.havox.labmon.model.api.contact.messenger.Skype;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link SkypeValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractSkypeValidationTest {
    /**
     * Provides an {@link Skype} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Skype getSkype() throws Exception;

    /**
     * Provides an {@link SkypeValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract SkypeValidator getSkypeValidator() throws Exception;

    /**
     * Tests if a valid {@link Skype} entity is valid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: having all necessary attributes
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidSkypeInstanceIsValid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Skype} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Skype} entity
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullSkypeIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Skype} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: missing a username attribute
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testSkypeWithoutUserNameIsInvalid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        instanceUnderTest.setUserName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Skype} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: having an empty username attribute
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testSkypeWithEmptyUserNameIsInvalid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        instanceUnderTest.setUserName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Skype} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: having an 5 letter username attribute
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testSkypeWithFiveLetterUserNameIsInvalid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        String userName = ModelRandomGenerator.randomString(1, ModelRandomGenerator.ALPHABETIC_STRING) +
                ModelRandomGenerator.randomString(4, ModelRandomGenerator.ALPHANUMERIC_STRING);
        Assertions.assertEquals(5, userName.length());

        instanceUnderTest.setUserName(userName);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Skype} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: having an 23 letter username attribute
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testSkypeWithTwentyThreeLetterUserNameIsInvalid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        String userName = ModelRandomGenerator.randomString(1, ModelRandomGenerator.ALPHABETIC_STRING) +
                ModelRandomGenerator.randomString(22, ModelRandomGenerator.ALPHANUMERIC_STRING);
        Assertions.assertEquals(23, userName.length());

        instanceUnderTest.setUserName(userName);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Skype} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Skype} entity
     * And: having no letter as first letter of the username attribute
     * When: validating the {@link Skype} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testSkypeWithoutLetterAsFirstLetterUserNameIsInvalid() throws Exception {
        Skype instanceUnderTest = getValidSkypeInstance();

        String numbers = "0123456789";
        String userName = ModelRandomGenerator.randomString(1, numbers) +
                ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(5, 21),
                        ModelRandomGenerator.ALPHANUMERIC_STRING);

        instanceUnderTest.setUserName(userName);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Skype} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Skype instanceUnderTest, Boolean expectedValid) throws Exception {
        SkypeValidator validator = getSkypeValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the skype account '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(),
                    "Expected the skype account '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                            "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link Skype} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Skype getValidSkypeInstance() throws Exception {
        Skype instance = getSkype();

        do {
            String userName = ModelRandomGenerator.randomString(1, ModelRandomGenerator.ALPHABETIC_STRING) +
                    ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(5, 21),
                            ModelRandomGenerator.ALPHANUMERIC_STRING);
            instance.setUserName(userName);
        } while (!StringUtils.isAlpha(instance.getUserName().substring(0, 1)));

        return instance;
    }
}
