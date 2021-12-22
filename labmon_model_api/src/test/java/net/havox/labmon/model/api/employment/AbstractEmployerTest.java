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

import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Abstract implementation of API test of {@link Employer}.
 *
 * @author Christian Otto
 */
public abstract class AbstractEmployerTest {
    /**
     * Provides a {@link Employer} instance.
     *
     * @return the instance
     * @throws Exception
     */
    public abstract Employer getEmployer() throws Exception;

    /**
     * Provides a {@link ContactOption} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract ContactOption getContactOption() throws Exception;

    /**
     * Tests the modification of the country name.
     * <p>
     * Given: A {@link Employer} instance
     * When: modifying the name attribute ({@link Employer#setName(String)})
     * Then: than the name attribute ({@link Employer#getName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyName() throws Exception {
        String alphabet = ModelRandomGenerator.ALPHABETIC_STRING + " -";
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Employer objectUnderTest = getEmployer();
        String oldName = objectUnderTest.getName();

        while (name.equals(oldName)) {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(oldName, name);

        objectUnderTest.setName(name);
        Assertions.assertEquals(name, objectUnderTest.getName());
        Assertions.assertNotEquals(oldName, objectUnderTest.getName());
    }

    /**
     * Tests if the contact options can be called.
     * <p>
     * Given: A {@link Employer} instance
     * When: getting the contact options ({@link Employer#getContactOptions()})
     * Then: the result {@link Set} is not {@code null}
     *
     * @throws Exception
     */
    @Test
    public void testGetContactOptions() throws Exception {
        Employer instanceUnderTest = getEmployer();

        Assertions.assertNotNull(instanceUnderTest.getContactOptions());
        Assertions.assertTrue(instanceUnderTest.getContactOptions() instanceof Set<ContactOption<?>>);
    }

    /**
     * Tests if a contact option can be added properly.
     * <p>
     * Given: A {@link Employer} instance
     * When: adding a contact option ({@link Employer#addContactOption(ContactOption)})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOption() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option;
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOption(option);

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));
    }

    /**
     * Tests if a contact option can be added properly.
     * <p>
     * Given: A {@link Employer} instance
     * When: adding a contact option ({@link Employer#addContactOptions(ContactOption[])})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOptionsArray() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option = getContactOption();
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOptions(option);

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));
    }

    /**
     * Tests if a contact option can be added properly.
     * <p>
     * Given: A {@link Employer} instance
     * When: adding a contact option ({@link Employer#addContactOptions(Collection)})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOptionsCollection() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option = getContactOption();
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOptions(Arrays.asList(option));

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));
    }

    /**
     * Tests if a contact option can be removed properly.
     *
     * <p>
     * Given: A {@link Employer} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link Employer#removeContactOption(ContactOption)})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOption() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option;
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOption(option);

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));

        boolean removalResult = instanceUnderTest.removeContactOption(option);

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeContactOption(option);

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if a contact option can be removed properly.
     *
     * <p>
     * Given: A {@link Employer} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link Employer#removeContactOptions(ContactOption[])})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOptionsArray() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option;
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOption(option);

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));

        boolean removalResult = instanceUnderTest.removeContactOptions(option);

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeContactOptions(option);

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if a contact option can be removed properly.
     *
     * <p>
     * Given: A {@link Employer} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link Employer#removeContactOptions(Collection)})
     * Then: than the contact options ({@link Employer#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOptionsCollection() throws Exception {
        Employer instanceUnderTest = getEmployer();

        ContactOption<?> option;
        do {
            option = getContactOption();
        }
        while (instanceUnderTest.getContactOptions().contains(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));

        instanceUnderTest.addContactOption(option);

        Assertions.assertTrue(instanceUnderTest.getContactOptions().contains(option));

        boolean removalResult = instanceUnderTest.removeContactOptions(Arrays.asList(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeContactOptions(Arrays.asList(option));

        Assertions.assertFalse(instanceUnderTest.getContactOptions().contains(option));
        Assertions.assertTrue(removalResult);
    }
}
