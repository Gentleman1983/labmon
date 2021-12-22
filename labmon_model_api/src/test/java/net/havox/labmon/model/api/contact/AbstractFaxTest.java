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

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import net.havox.labmon.model.utils.validation.contact.FaxValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Abstract implementation of API test of {@link Fax}.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractFaxTest {
    @Mock
    private Fax mockedInstance;

    /**
     * Provides an {@link Fax} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Fax getFax() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Fax} instance
     * When: modifying the street attribute ({@link Fax#setPhoneNumber(PhoneNumber)} )
     * Then: than the street attribute ({@link Fax#getPhoneNumber()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyPhoneNumber() throws Exception {
        PhoneNumber number = PhoneNumberUtil.getInstance().getExampleNumber("DE");

        Fax objectUnderTest = getFax();
        PhoneNumber oldNumber = objectUnderTest.getPhoneNumber();

        while (number.equals(oldNumber)) {
            number = PhoneNumberUtil.getInstance().getExampleNumber("DE");
        }
        Assertions.assertNotEquals(number, oldNumber);

        objectUnderTest.setPhoneNumber(number);
        Assertions.assertEquals(number, objectUnderTest.getPhoneNumber());
        Assertions.assertNotEquals(oldNumber, objectUnderTest.getPhoneNumber());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link Fax} instance
     * When: calling {@link Fax#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link FaxValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Fax objectUnderTest = getFax();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof FaxValidator);
    }

    /**
     * Tests if {@link Fax#isValid()} results in an exception.
     */
    @Test
    public void testIsValidDoesNotResultInErrors() {
        Assertions.assertNotNull(mockedInstance);
        mockedInstance.isValid();
    }
}
