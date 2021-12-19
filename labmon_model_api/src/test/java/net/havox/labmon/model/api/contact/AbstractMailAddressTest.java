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

package net.havox.labmon.model.api.contact;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.utils.validation.contact.MailAddressValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link MailAddress}.
 *
 * @author Christian Otto
 */
public abstract class AbstractMailAddressTest {
    /**
     * Provides an {@link MailAddress} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract MailAddress getMailAddress() throws Exception;

    /**
     * Provides an {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link MailAddress} instance
     * When: modifying the street attribute ({@link MailAddress#setReceiver(String)})
     * Then: than the street attribute ({@link MailAddress#getReceiver()} ) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyReceiver() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String receiver = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        MailAddress objectUnderTest = getMailAddress();
        String oldReceiver = objectUnderTest.getReceiver();

        while (receiver.equals(oldReceiver)) {
            receiver = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(receiver, oldReceiver);

        objectUnderTest.setReceiver(receiver);
        Assertions.assertEquals(receiver, objectUnderTest.getReceiver());
        Assertions.assertNotEquals(oldReceiver, objectUnderTest.getReceiver());
    }

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link MailAddress} instance
     * When: modifying the street attribute ({@link MailAddress#setAddress(Address)} )
     * Then: than the street attribute ({@link MailAddress#getAddress()} ) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyAddress() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        Address address = getAddress();

        MailAddress objectUnderTest = getMailAddress();
        Address oldAddress = objectUnderTest.getAddress();

        while (address.equals(oldAddress)) {
            address = getAddress();
        }
        Assertions.assertNotEquals(address, oldAddress);

        objectUnderTest.setAddress(address);
        Assertions.assertEquals(address, objectUnderTest.getAddress());
        Assertions.assertNotEquals(oldAddress, objectUnderTest.getAddress());
    }

    /**
     * Tests if a proper validator is provided.
     *
     * Given: A {@link MailAddress} instance
     * When: calling {@link MailAddress#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link MailAddressValidator}
     *
     * @throws Exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        MailAddress objectUnderTest = getMailAddress();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof MailAddressValidator);
    }
}
