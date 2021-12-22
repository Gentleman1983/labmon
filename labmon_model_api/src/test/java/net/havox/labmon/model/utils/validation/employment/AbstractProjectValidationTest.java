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
import net.havox.labmon.model.api.employment.Project;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Abstract implementation of {@link ProjectValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractProjectValidationTest {
    /**
     * Provides an {@link Project} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Project getProject() throws Exception;

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
     * Provides an {@link ProjectValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract ProjectValidator getProjectValidator() throws Exception;

    /**
     * Tests if a valid {@link Project} entity is valid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having all necessary attributes
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidProjectInstanceIsValid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Project} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Project} entity
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullProjectIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Project} entity missing the user is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: missing the name attribute
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithoutNameIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        instanceUnderTest.setName(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with invalid user is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having an empty name attribute
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithEmptyNameIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        instanceUnderTest.setName("");
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity missing the employer is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: missing the employment attribute
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithoutEmploymentIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        instanceUnderTest.setEmployment(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with invalid employer is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having an invalid employment attribute
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithInvalidEmploymentIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        Employment invalidEmployment = getValidEmploymentInstance();
        invalidEmployment.setUser(null);
        invalidEmployment.setEmployer(null);
        invalidEmployment.setEmploymentStartDate(null);
        Assertions.assertFalse(invalidEmployment.getValidator().isValid(invalidEmployment));

        instanceUnderTest.setEmployment(invalidEmployment);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity missing the start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: missing the start date attribute
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithoutStartDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        instanceUnderTest.setStartDate(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having a start date attribute
     * And: having an end date before the start date
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testProjectWithEndDateBeforeStartDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(1);
        Assertions.assertTrue(endDate.isBefore(startDate));

        instanceUnderTest.setStartDate(startDate);
        instanceUnderTest.setEndDate(endDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having a start date attribute
     * And: having an end date before the start date
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testProjectWithRandomEndDateBeforeStartDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        Assertions.assertTrue(endDate.isBefore(startDate));

        instanceUnderTest.setStartDate(startDate);
        instanceUnderTest.setEndDate(endDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having a start date attribute
     * And: having an employment
     * And: the employment having a start date
     * And: the start date has to be before the employment start date
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testProjectWithStartDateBeforeEmploymentStartDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        LocalDate startDate = LocalDate.now();
        LocalDate employmentStartDate = startDate.plusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        Assertions.assertTrue(startDate.isBefore(employmentStartDate));

        instanceUnderTest.setStartDate(startDate);
        instanceUnderTest.getEmployment().setEmploymentStartDate(employmentStartDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having a start date attribute
     * And: having an employment
     * And: the employment having an end date
     * And: the start date has to be after the employment end date
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testProjectWithStartDateAfterEmploymentEndDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        LocalDate employmentEndDate = instanceUnderTest.getStartDate();
        LocalDate startDate = employmentEndDate.plusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        Assertions.assertTrue(startDate.isAfter(employmentEndDate));

        instanceUnderTest.setStartDate(startDate);
        instanceUnderTest.getEmployment().setEmploymentEndDate(employmentEndDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Project} entity with end date before start date is invalid.
     * <p>
     * Given: a randomized {@link Project} entity
     * And: having an end date attribute
     * And: having an employment
     * And: the employment having an end date
     * And: the end date has to be after the employment end date
     * When: validating the {@link Project} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testProjectWithEndDateAfterEmploymentEndDateIsInvalid() throws Exception {
        Project instanceUnderTest = getValidProjectInstance();

        LocalDate employmentEndDate = instanceUnderTest.getStartDate().plusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        LocalDate endDate = employmentEndDate.plusDays(ModelRandomGenerator.randomLongInRange(1, 255));
        Assertions.assertTrue(endDate.isAfter(employmentEndDate));

        instanceUnderTest.setEndDate(endDate);
        instanceUnderTest.getEmployment().setEmploymentEndDate(employmentEndDate);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Project} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Project instanceUnderTest, Boolean expectedValid) throws Exception {
        ProjectValidator validator = getProjectValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the project '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
    }

    /**
     * Provides a valid {@link Project} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Project getValidProjectInstance() throws Exception {
        Project instance = getProject();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50)));
        instance.setEmployment(getValidEmploymentInstance());
        do {
            instance.setStartDate(ModelRandomGenerator.randomLocalDate());
        } while (instance.getStartDate().isBefore(instance.getEmployment().getEmploymentStartDate()));

        return instance;
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
