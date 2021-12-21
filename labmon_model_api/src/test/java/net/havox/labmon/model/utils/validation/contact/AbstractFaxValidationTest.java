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
import net.havox.labmon.model.api.contact.Fax;
import net.havox.labmon.model.api.contact.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link FaxValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractFaxValidationTest {
    /**
     * Provides an {@link Fax} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Fax getFax() throws Exception;

    /**
     * Provides an {@link FaxValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract FaxValidator getFaxValidator() throws Exception;

    /**
     * Tests if a valid {@link Fax} entity is valid.
     * <p>
     * Given: a randomized {@link Fax} entity
     * And: having all necessary attributes
     * When: validating the {@link Fax} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidFaxInstanceIsValid() throws Exception {
        Fax instanceUnderTest = getValidFaxInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link Fax} entity is invalid.
     * <p>
     * Given: a {@code null} {@link Fax} entity
     * When: validating the {@link Fax} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testNullFaxIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link Fax} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link Fax} entity
     * And: missing a phone number attribute
     * When: validating the {@link Fax} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testFaxWithoutPhoneNumberIsInvalid() throws Exception {
        Fax instanceUnderTest = getValidFaxInstance();

        instanceUnderTest.setPhoneNumber(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link Fax} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link Fax} entity
     * And: having a invalid phone number attribute
     * When: validating the {@link Fax} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testFaxWithInvalidPhoneNumberIsInvalid() throws Exception {
        Fax instanceUnderTest = getValidFaxInstance();

        instanceUnderTest.setPhoneNumber(PhoneNumberUtil.getInstance().getInvalidExampleNumber("DE"));
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link Fax} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Fax instanceUnderTest, Boolean expectedValid) throws Exception {
        FaxValidator validator = getFaxValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the fax '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(),
                    "Expected the fax '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                            "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link Fax} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Fax getValidFaxInstance() throws Exception {
        Fax instance = getFax();

        instance.setPhoneNumber(PhoneNumberUtil.getInstance().getExampleNumber("DE"));

        return instance;
    }
}
