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

package net.havox.labmon.model.api.user;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.Collection;

/**
 * Abstract implementation of API test of {@link User}.
 *
 * @author Christian Otto
 */
public abstract class AbstractUserTest {
    /**
     * Provides an {@link User} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract User getUser() throws Exception;

    /**
     * Provides an {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Provides an {@link Credentials} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Credentials getCredentials() throws Exception;

    /**
     * Provides a {@link ContactOption} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract ContactOption getContactOption() throws Exception;

    /**
     * Tests if changes on the first name property work properly.
     * <p>
     * Given: A {@link User} instance
     * When: modifying the first name attribute ({@link User#setFirstName(String)})
     * Then: than the first name attribute ({@link User#getFirstName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserFirstName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getFirstName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getFirstName());

        instanceUnderTest.setFirstName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getFirstName());
        Assertions.assertEquals(name, instanceUnderTest.getFirstName());
    }

    /**
     * Tests if changes on the middle name property work properly.
     * <p>
     * Given: A {@link User} instance
     * When: modifying the middle name attribute ({@link User#setMiddleName(String)})
     * Then: than the middle name attribute ({@link User#getMiddleName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserMiddleName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getMiddleName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getMiddleName());

        instanceUnderTest.setMiddleName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getMiddleName());
        Assertions.assertEquals(name, instanceUnderTest.getMiddleName());
    }

    /**
     * Tests if changes on the last name property work properly.
     * <p>
     * Given: A {@link User} instance
     * When: modifying the last name attribute ({@link User#setLastName(String)})
     * Then: than the last name attribute ({@link User#getLastName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserLastName() throws Exception {
        User instanceUnderTest = getUser();
        String instanceOriginalName = instanceUnderTest.getLastName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100),
                    ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getLastName());

        instanceUnderTest.setLastName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getLastName());
        Assertions.assertEquals(name, instanceUnderTest.getLastName());
    }

    /**
     * Tests if changes on the address property work properly.
     * <p>
     * Given: A {@link User} instance
     * When: modifying the address attribute ({@link User#setAddress(Address)})
     * Then: than the address attribute ({@link User#getAddress()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyAddress() throws Exception {
        Address address = getAddress();

        User objectUnderTest = getUser();
        objectUnderTest.setAddress(address);
        Assertions.assertEquals(address, objectUnderTest.getAddress());
    }

    /**
     * Tests if changes on the credentials property work properly.
     * <p>
     * Given: A {@link User} instance
     * When: modifying the credentials attribute ({@link User#setCredentials(Credentials)})
     * Then: than the credentials attribute ({@link User#getCredentials()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyCredentials() throws Exception {
        Credentials credentials = getCredentials();

        User objectUnderTest = getUser();
        objectUnderTest.setCredentials(credentials);
        Assertions.assertEquals(credentials, objectUnderTest.getCredentials());
    }

    /**
     * Tests if a sub role can be added properly.
     * <p>
     * Given: A {@link User} instance
     * When: adding a contact option ({@link User#addContactOption(ContactOption)})
     * Then: than the contact options ({@link User#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOption() throws Exception {
        User instanceUnderTest = getUser();

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
     * Tests if a sub role can be added properly.
     * <p>
     * Given: A {@link User} instance
     * When: adding a contact option ({@link User#addContactOptions(ContactOption[])})
     * Then: than the contact options ({@link User#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOptionsArray() throws Exception {
        User instanceUnderTest = getUser();

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
     * Tests if a sub role can be added properly.
     * <p>
     * Given: A {@link User} instance
     * When: adding a contact option ({@link User#addContactOptions(Collection)})
     * Then: than the contact options ({@link User#getContactOptions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddContactOptionsCollection() throws Exception {
        User instanceUnderTest = getUser();

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
     * Tests if a sub role can be removed properly.
     *
     * <p>
     * Given: A {@link User} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link User#removeContactOption(ContactOption)})
     * Then: than the contact options ({@link User#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOption() throws Exception {
        User instanceUnderTest = getUser();

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
     * Tests if a sub role can be removed properly.
     *
     * <p>
     * Given: A {@link User} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link User#removeContactOptions(ContactOption[])})
     * Then: than the contact options ({@link User#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOptionsArray() throws Exception {
        User instanceUnderTest = getUser();

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
     * Tests if a sub role can be removed properly.
     *
     * <p>
     * Given: A {@link User} instance
     * And: containing a {@link ContactOption}
     * When: removing the contact option ({@link User#removeContactOptions(Collection)})
     * Then: than the contact options ({@link User#getContactOptions()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveContactOptionsCollection() throws Exception {
        User instanceUnderTest = getUser();

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
