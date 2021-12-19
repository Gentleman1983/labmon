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
import com.google.i18n.phonenumbers.Phonenumber;
import net.havox.labmon.model.utils.validation.contact.PhoneValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link Phone}.
 *
 * @author Christian Otto
 */
public abstract class AbstractPhoneTest {
    /**
     * Provides an {@link Phone} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Phone getPhone() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Phone} instance
     * When: modifying the street attribute ({@link Phone#setPhoneNumber(Phonenumber.PhoneNumber)} )
     * Then: than the street attribute ({@link Phone#getPhoneNumber()} ) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyPhoneNumber() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        Phonenumber.PhoneNumber number = PhoneNumberUtil.getInstance().getExampleNumber("DE");

        Phone objectUnderTest = getPhone();
        Phonenumber.PhoneNumber oldNumber = objectUnderTest.getPhoneNumber();

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
     *
     * Given: A {@link Phone} instance
     * When: calling {@link Phone#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link PhoneValidator}
     *
     * @throws Exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Phone objectUnderTest = getPhone();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof PhoneValidator);
    }
}
