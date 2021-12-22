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

import net.havox.labmon.model.utils.validation.contact.EMailAddressValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Abstract implementation of API test of {@link EMailAddress}.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractEMailAddressTest {
    @Mock
    private EMailAddress mockedInstance;

    /**
     * Provides an {@link EMailAddress} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract EMailAddress getEMailAddress() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link EMailAddress} instance
     * When: modifying the street attribute ({@link EMailAddress#setEMailAddress(String)} )
     * Then: than the street attribute ({@link EMailAddress#getEMailAddress()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyEMailAddress() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        EMailAddress objectUnderTest = getEMailAddress();
        String oldUserName = objectUnderTest.getEMailAddress();

        while (userName.equals(oldUserName)) {
            userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(userName, oldUserName);

        objectUnderTest.setEMailAddress(userName);
        Assertions.assertEquals(userName, objectUnderTest.getEMailAddress());
        Assertions.assertNotEquals(oldUserName, objectUnderTest.getEMailAddress());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link EMailAddress} instance
     * When: calling {@link EMailAddress#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link EMailAddressValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        EMailAddress objectUnderTest = getEMailAddress();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof EMailAddressValidator);
    }

    /**
     * Tests if {@link EMailAddress#isValid()} results in an exception.
     */
    @Test
    public void testIsValidDoesNotResultInErrors() {
        Assertions.assertNotNull(mockedInstance);
        mockedInstance.isValid();
    }
}
