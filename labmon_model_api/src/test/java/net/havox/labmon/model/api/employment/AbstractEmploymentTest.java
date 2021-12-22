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

package net.havox.labmon.model.api.employment;

import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.utils.validation.employment.EmploymentValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Abstract implementation of API test of {@link Employment}.
 *
 * @author Christian Otto
 */
public abstract class AbstractEmploymentTest {
    /**
     * Provides a {@link Employment} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Employment getEmployment() throws Exception;

    /**
     * Provides a {@link User} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract User getUser() throws Exception;

    /**
     * Provides a {@link Employer} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Employer getEmployer() throws Exception;

    /**
     * Provides a {@link BookingType} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract BookingType getBookingType() throws Exception;

    /**
     * Tests the modification of the user.
     * <p>
     * Given: A {@link Employment} instance
     * When: modifying the name attribute ({@link Employment#setUser(User)})
     * Then: than the name attribute ({@link Employment#getUser()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyUser() throws Exception {
        User user = getUser();

        Employment objectUnderTest = getEmployment();
        User oldUser = objectUnderTest.getUser();

        while (user.equals(oldUser)) {
            user = getUser();
        }
        Assertions.assertNotEquals(oldUser, user);

        objectUnderTest.setUser(user);
        Assertions.assertEquals(user, objectUnderTest.getUser());
        Assertions.assertNotEquals(oldUser, objectUnderTest.getUser());
    }

    /**
     * Tests the modification of the employer.
     * <p>
     * Given: A {@link Employment} instance
     * When: modifying the name attribute ({@link Employment#setEmployer(Employer)})
     * Then: than the name attribute ({@link Employment#getEmployer()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyEmployer() throws Exception {
        Employer employer = getEmployer();

        Employment objectUnderTest = getEmployment();
        Employer oldEmployer = objectUnderTest.getEmployer();

        while (employer.equals(oldEmployer)) {
            employer = getEmployer();
        }
        Assertions.assertNotEquals(oldEmployer, employer);

        objectUnderTest.setEmployer(employer);
        Assertions.assertEquals(employer, objectUnderTest.getEmployer());
        Assertions.assertNotEquals(oldEmployer, objectUnderTest.getEmployer());
    }

    /**
     * Tests the modification of the description.
     * <p>
     * Given: A {@link Employment} instance
     * When: modifying the name attribute ({@link Employment#setDescription(String)})
     * Then: than the name attribute ({@link Employment#getDescription()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyDescription() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + " -";
        String description = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Employment objectUnderTest = getEmployment();
        String oldDescription = objectUnderTest.getDescription();

        while (description.equals(oldDescription)) {
            description = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(oldDescription, description);

        objectUnderTest.setDescription(description);
        Assertions.assertEquals(description, objectUnderTest.getDescription());
        Assertions.assertNotEquals(oldDescription, objectUnderTest.getDescription());
    }

    /**
     * Tests the modification of the start date.
     * <p>
     * Given: A {@link Employment} instance
     * When: modifying the name attribute ({@link Employment#setStartDate(LocalDate)})
     * Then: than the name attribute ({@link Employment#getStartDate()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyStartDate() throws Exception {
        LocalDate startDate = ModelRandomGenerator.randomLocalDate();

        Employment objectUnderTest = getEmployment();
        LocalDate oldDate = objectUnderTest.getStartDate();

        while (startDate.equals(oldDate)) {
            startDate = ModelRandomGenerator.randomLocalDate();
        }
        Assertions.assertNotEquals(oldDate, startDate);

        objectUnderTest.setStartDate(startDate);
        Assertions.assertEquals(startDate, objectUnderTest.getStartDate());
        Assertions.assertNotEquals(oldDate, objectUnderTest.getStartDate());
    }

    /**
     * Tests the modification of the end date.
     * <p>
     * Given: A {@link Employment} instance
     * When: modifying the name attribute ({@link Employment#setEndDate(LocalDate)})
     * Then: than the name attribute ({@link Employment#getEndDate()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyEndDate() throws Exception {
        LocalDate endDate = ModelRandomGenerator.randomLocalDate();

        Employment objectUnderTest = getEmployment();
        LocalDate oldDate = objectUnderTest.getEndDate();

        while (endDate.equals(oldDate)) {
            endDate = ModelRandomGenerator.randomLocalDate();
        }
        Assertions.assertNotEquals(oldDate, endDate);

        objectUnderTest.setEndDate(endDate);
        Assertions.assertEquals(endDate, objectUnderTest.getEndDate());
        Assertions.assertNotEquals(oldDate, objectUnderTest.getEndDate());
    }

    /**
     * Tests if a booking type can be added properly.
     * <p>
     * Given: A {@link Employment} instance
     * When: adding a sub role ({@link Employment#addBookingType(BookingType)})
     * Then: than the sub roles ({@link Employment#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingType() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingType(bookingType);

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));
    }

    /**
     * Tests if a booking type can be added properly.
     * <p>
     * Given: A {@link Employment} instance
     * When: adding a sub role ({@link Employment#addBookingTypes(BookingType...)} )
     * Then: than the sub roles ({@link Employment#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingTypesArray() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingTypes(bookingType);

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));
    }

    /**
     * Tests if a booking type can be added properly.
     * <p>
     * Given: A {@link Employment} instance
     * When: adding a sub role ({@link Employment#addBookingTypes(Collection)} )
     * Then: than the sub roles ({@link Employment#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingTypesCollection() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingTypes(List.of(bookingType));

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));
    }

    /**
     * Tests if a booking type can be removed properly.
     *
     * <p>
     * Given: A {@link Employment} instance
     * And: containing a sub {@link Employment}
     * When: removing the sub role ({@link Employment#removeBookingType(BookingType)} )
     * Then: than the roles ({@link Employment#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingType() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingType(bookingType);

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));

        boolean removalResult = instanceUnderTest.removeBookingType(bookingType);

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeBookingType(bookingType);

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if a booking type can be removed properly.
     *
     * <p>
     * Given: A {@link Employment} instance
     * And: containing a sub {@link Employment}
     * When: removing the sub role ({@link Employment#removeBookingTypes(BookingType...)}  )
     * Then: than the roles ({@link Employment#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingTypesArray() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingType(bookingType);

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));

        boolean removalResult = instanceUnderTest.removeBookingTypes(bookingType);

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeBookingTypes(bookingType);

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if a booking type can be removed properly.
     *
     * <p>
     * Given: A {@link Employment} instance
     * And: containing a sub {@link Employment}
     * When: removing the sub role ({@link Employment#removeBookingTypes(Collection)}  )
     * Then: than the roles ({@link Employment#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingTypesCollection() throws Exception {
        Employment instanceUnderTest = getEmployment();

        BookingType bookingType;
        do {
            bookingType = getBookingType();
        } while (instanceUnderTest.getBookingTypes().contains(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));

        instanceUnderTest.addBookingType(bookingType);

        Assertions.assertTrue(instanceUnderTest.getBookingTypes().contains(bookingType));

        boolean removalResult = instanceUnderTest.removeBookingTypes(List.of(bookingType));

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(bookingType));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeBookingTypes(bookingType);

        Assertions.assertFalse(instanceUnderTest.getBookingTypes().contains(List.of(bookingType)));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Test if the validator is properly provided.
     * <p>
     * Given: A {@link Employment} instance
     * When: getting the validator ({@link Employment#getValidator()}
     * Then: the validator shall be instantiated
     * And: the validator shall be of type {@link EmploymentValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testGetValidator() throws Exception {
        Employment instanceUnderTest = getEmployment();

        Assertions.assertNotNull(instanceUnderTest.getValidator());
        Assertions.assertTrue(instanceUnderTest.getValidator() instanceof EmploymentValidator);
    }
}
