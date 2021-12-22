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

package net.havox.labmon.model.utils.validation.employment;

import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link EmployerValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractEmployerValidationTest {
    /**
     * Provides an {@link Employer} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Employer getEmployer() throws Exception;

    /**
     * Provides an {@link EmployerValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract EmployerValidator getEmployerValidator() throws Exception;

    /**
     * Tests if a valid {@link Employer} entity is valid.
     * <p>
     * Given: a randomized {@link Employer} entity
     * And: having all necessary attributes
     * When: validating the {@link Employer} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidEmployerInstanceIsValid() throws Exception {
        Employer instanceUnderTest = getValidEmployerInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Employer} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Employer} entity
     * When: validating the {@link Employer} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullEmployerIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Employer} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link Employer} entity
     * And: missing a name attribute
     * When: validating the {@link Employer} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmployerWithoutNameIsInvalid() throws Exception {
        Employer instanceUnderTest = getValidEmployerInstance();

        instanceUnderTest.setName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employer} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link Employer} entity
     * And: having an empty name attribute
     * When: validating the {@link Employer} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmployerWithEmptyNameIsInvalid() throws Exception {
        Employer instanceUnderTest = getValidEmployerInstance();

        instanceUnderTest.setName("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Employer} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Employer instanceUnderTest, Boolean expectedValid) throws Exception {
        EmployerValidator validator = getEmployerValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the employer '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
    }

    /**
     * Provides a valid {@link Employer} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Employer getValidEmployerInstance() throws Exception {
        Employer instance = getEmployer();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
