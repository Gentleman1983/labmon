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

package net.havox.labmon.model.utils.validation.contact;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import net.havox.labmon.model.api.contact.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link PhoneValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractPhoneValidationTest {
    /**
     * Provides an {@link Phone} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Phone getPhone() throws Exception;

    /**
     * Provides an {@link PhoneValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract PhoneValidator getPhoneValidator() throws Exception;

    /**
     * Tests if a valid {@link Phone} entity is valid.
     * <p>
     * Given: a randomized {@link Phone} entity
     * And: having all necessary attributes
     * When: validating the {@link Phone} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testValidPhoneInstanceIsValid() throws Exception {
        Phone instanceUnderTest = getValidPhoneInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Phone} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Phone} entity
     * When: validating the {@link Phone} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testNullPhoneIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Phone} entity missing the phone number is invalid.
     * <p>
     * Given: a randomized {@link Phone} entity
     * And: missing a phone number attribute
     * When: validating the {@link Phone} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testPhoneWithoutPhoneNumberIsInvalid() throws Exception {
        Phone instanceUnderTest = getValidPhoneInstance();

        instanceUnderTest.setPhoneNumber(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Phone} entity having an invalid phone number is invalid.
     * <p>
     * Given: a randomized {@link Phone} entity
     * And: having an invalid phone number attribute
     * When: validating the {@link Phone} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(5)
    public void testPhoneWithInvalidPhoneNumberIsInvalid() throws Exception {
        Phone instanceUnderTest = getValidPhoneInstance();

        instanceUnderTest.setPhoneNumber(PhoneNumberUtil.getInstance().getInvalidExampleNumber("DE"));
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Phone} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception in case of an exception
     */
    private void checkValidInstance(Phone instanceUnderTest, Boolean expectedValid) throws Exception {
        PhoneValidator validator = getPhoneValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest), "Expected the phone '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") + "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) + ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(), "Expected the phone '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") + "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link Phone} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    private Phone getValidPhoneInstance() throws Exception {
        Phone instance = getPhone();

        instance.setPhoneNumber(PhoneNumberUtil.getInstance().getExampleNumber("DE"));

        return instance;
    }
}
