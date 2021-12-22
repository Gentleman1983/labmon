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
import net.havox.labmon.model.utils.validation.employment.ProjectValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Abstract implementation of API test of {@link Project}.
 *
 * @author Christian Otto
 */
public abstract class AbstractProjectTest {
    /**
     * Provides a {@link Project} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Project getProject() throws Exception;

    /**
     * Provides a {@link Employment} instance.
     *
     * @return the instance
     * @throws Exception in case of an exception
     */
    public abstract Employment getEmployment() throws Exception;

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
     * Given: A {@link Project} instance
     * When: modifying the name attribute ({@link Project#setName(String)})
     * Then: than the name attribute ({@link Project#getName()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyName() throws Exception {
        String name = ModelRandomGenerator.randomString(50);

        Project objectUnderTest = getProject();
        String oldName = objectUnderTest.getName();

        while (name.equals(oldName)) {
            name = ModelRandomGenerator.randomString(50);
        }
        Assertions.assertNotEquals(oldName, name);

        objectUnderTest.setName(name);
        Assertions.assertEquals(name, objectUnderTest.getName());
        Assertions.assertNotEquals(oldName, objectUnderTest.getName());
    }

    /**
     * Tests the modification of the user.
     * <p>
     * Given: A {@link Project} instance
     * When: modifying the name attribute ({@link Project#setEmployment(Employment)})
     * Then: than the name attribute ({@link Project#getEmployment()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyEmployment() throws Exception {
        Employment employment = getEmployment();

        Project objectUnderTest = getProject();
        Employment oldEmployment = objectUnderTest.getEmployment();

        while (employment.equals(oldEmployment)) {
            employment = getEmployment();
        }
        Assertions.assertNotEquals(oldEmployment, employment);

        objectUnderTest.setEmployment(employment);
        Assertions.assertEquals(employment, objectUnderTest.getEmployment());
        Assertions.assertNotEquals(oldEmployment, objectUnderTest.getEmployment());
    }

    /**
     * Tests the modification of the start date.
     * <p>
     * Given: A {@link Project} instance
     * When: modifying the name attribute ({@link Project#setStartDate(LocalDate)})
     * Then: than the name attribute ({@link Project#getStartDate()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyStartDate() throws Exception {
        LocalDate startDate = ModelRandomGenerator.randomLocalDate();

        Project objectUnderTest = getProject();
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
     * Given: A {@link Project} instance
     * When: modifying the name attribute ({@link Project#setEndDate(LocalDate)})
     * Then: than the name attribute ({@link Project#getEndDate()}) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyEndDate() throws Exception {
        LocalDate endDate = ModelRandomGenerator.randomLocalDate();

        Project objectUnderTest = getProject();
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
     * Given: A {@link Project} instance
     * When: adding a sub role ({@link Project#addBookingType(BookingType)})
     * Then: than the sub roles ({@link Project#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingType() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * When: adding a sub role ({@link Project#addBookingTypes(BookingType...)} )
     * Then: than the sub roles ({@link Project#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingTypesArray() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * When: adding a sub role ({@link Project#addBookingTypes(Collection)} )
     * Then: than the sub roles ({@link Project#getBookingTypes()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testAddBookingTypesCollection() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * And: containing a sub {@link Project}
     * When: removing the sub role ({@link Project#removeBookingType(BookingType)} )
     * Then: than the roles ({@link Project#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingType() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * And: containing a sub {@link Project}
     * When: removing the sub role ({@link Project#removeBookingTypes(BookingType...)}  )
     * Then: than the roles ({@link Project#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingTypesArray() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * And: containing a sub {@link Project}
     * When: removing the sub role ({@link Project#removeBookingTypes(Collection)}  )
     * Then: than the roles ({@link Project#getBookingTypes()} ) should not contain the value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testRemoveBookingTypesCollection() throws Exception {
        Project instanceUnderTest = getProject();

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
     * Given: A {@link Project} instance
     * When: getting the validator ({@link Project#getValidator()}
     * Then: the validator shall be instantiated
     * And: the validator shall be of type {@link ProjectValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testGetValidator() throws Exception {
        Project instanceUnderTest = getProject();

        Assertions.assertNotNull(instanceUnderTest.getValidator());
        Assertions.assertTrue(instanceUnderTest.getValidator() instanceof ProjectValidator);
    }
}
