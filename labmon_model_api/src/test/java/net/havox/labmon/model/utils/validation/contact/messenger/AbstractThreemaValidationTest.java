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

import net.havox.labmon.model.api.contact.messenger.Threema;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link ThreemaValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractThreemaValidationTest {
    /**
     * Provides an {@link Threema} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Threema getThreema() throws Exception;

    /**
     * Provides an {@link ThreemaValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract ThreemaValidator getThreemaValidator() throws Exception;

    /**
     * Tests if a valid {@link Threema} entity is valid.
     * <p>
     * Given: a randomized {@link Threema} entity
     * And: having all necessary attributes
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidThreemaInstanceIsValid() throws Exception {
        Threema instanceUnderTest = getValidThreemaInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Threema} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Threema} entity
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullThreemaIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Threema} entity missing the Threema id is invalid.
     * <p>
     * Given: a randomized {@link Threema} entity
     * And: missing the Threema id attribute
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testThreemaWithoutThreemaIdIsInvalid() throws Exception {
        Threema instanceUnderTest = getValidThreemaInstance();

        instanceUnderTest.setThreemaId(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Threema} entity with empty the Threema id is invalid.
     * <p>
     * Given: a randomized {@link Threema} entity
     * And: having an empty Threema id attribute
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testThreemaWithEmptyThreemaIdIsInvalid() throws Exception {
        Threema instanceUnderTest = getValidThreemaInstance();

        instanceUnderTest.setThreemaId("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Threema} entity with 7 digit Threema id is invalid.
     * <p>
     * Given: a randomized {@link Threema} entity
     * And: having an 7 digit Threema id attribute
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testThreemaWithSevenDigitThreemaIdIsInvalid() throws Exception {
        Threema instanceUnderTest = getValidThreemaInstance();

        instanceUnderTest.setThreemaId(ModelRandomGenerator.randomString(7,
                ModelRandomGenerator.ALPHANUMERIC_STRING));

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Threema} entity with 9 digit Threema id is invalid.
     * <p>
     * Given: a randomized {@link Threema} entity
     * And: having an 9 digit Threema id attribute
     * When: validating the {@link Threema} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testThreemaWithNineDigitThreemaIdIsInvalid() throws Exception {
        Threema instanceUnderTest = getValidThreemaInstance();

        instanceUnderTest.setThreemaId(ModelRandomGenerator.randomString(9,
                ModelRandomGenerator.ALPHANUMERIC_STRING));

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Threema} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Threema instanceUnderTest, Boolean expectedValid) throws Exception {
        ThreemaValidator validator = getThreemaValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the Threema account '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(),
                    "Expected the Threema account '" + instanceUnderTest + "' " +
                            (expectedValid ? "" : " not") + "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link Threema} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Threema getValidThreemaInstance() throws Exception {
        Threema instance = getThreema();

        instance.setThreemaId(ModelRandomGenerator.randomString(8, ModelRandomGenerator.ALPHANUMERIC_STRING));

        return instance;
    }
}
