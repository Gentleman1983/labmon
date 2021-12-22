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
import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Abstract implementation of {@link EmploymentValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractEmploymentValidationTest {
    /**
     * Provides an {@link Employment} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Employment getEmployment() throws Exception;

    /**
     * Provides an {@link Employer} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Employer getEmployer() throws Exception;

    /**
     * Provides an {@link User} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract User getUser() throws Exception;

    /**
     * Provides an {@link Credentials} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Credentials getCredentials() throws Exception;

    /**
     * Provides an {@link EmploymentValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract EmploymentValidator getEmploymentValidator() throws Exception;

    /**
     * Tests if a valid {@link Employment} entity is valid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: having all necessary attributes
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidEmploymentInstanceIsValid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Employment} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Employment} entity
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullEmploymentIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Employment} entity missing the user is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: missing the user attribute
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithoutUserIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        instanceUnderTest.setUser(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity with invalid user is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: having an invalid user attribute
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithInvalidUserIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        User invalidUser = getValidUserInstance();
        invalidUser.setFirstName(null);
        invalidUser.setLastName(null);
        Assertions.assertFalse(invalidUser.getValidator().isUserValid(invalidUser));

        instanceUnderTest.setUser(invalidUser);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity missing the employer is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: missing the employer attribute
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithoutEmployerIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        instanceUnderTest.setEmployer(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity with invalid employer is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: having an invalid employer attribute
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithInvalidEmployerIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        Employer invalidEmployer = getValidEmployerInstance();
        invalidEmployer.setName(null);
        Assertions.assertFalse(invalidEmployer.getValidator().isValid(invalidEmployer));

        instanceUnderTest.setEmployer(invalidEmployer);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity missing the start date is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: missing the start date attribute
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithoutStartDateIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        instanceUnderTest.setEmploymentStartDate(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: having a start date attribute
     * And: having an end date before the start date
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testEmploymentWithEndDateBeforeStartDateIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(1);
        Assertions.assertTrue(endDate.isBefore(startDate));

        instanceUnderTest.setEmploymentStartDate(startDate);
        instanceUnderTest.setEmploymentEndDate(endDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Employment} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Employment} entity
     * And: having a start date attribute
     * And: having an end date before the start date
     * When: validating the {@link Employment} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testEmploymentWithRandomEndDateBeforeStartDateIsInvalid() throws Exception {
        Employment instanceUnderTest = getValidEmploymentInstance();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        Assertions.assertTrue(endDate.isBefore(startDate));

        instanceUnderTest.setEmploymentStartDate(startDate);
        instanceUnderTest.setEmploymentEndDate(endDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Employment} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Employment instanceUnderTest, Boolean expectedValid) throws Exception {
        EmploymentValidator validator = getEmploymentValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the employment '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
    }

    /**
     * Provides a valid {@link Employment} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Employment getValidEmploymentInstance() throws Exception {
        Employment instance = getEmployment();

        instance.setUser(getValidUserInstance());
        instance.setEmployer(getValidEmployerInstance());
        instance.setDescription(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setEmploymentStartDate(ModelRandomGenerator.randomLocalDate());

        return instance;
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

    /**
     * Provides a valid {@link User} instance.
     *
     * @return a valid instance
     * @throws Exception in case of an exception
     */
    private User getValidUserInstance() throws Exception {
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
