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
     * @throws Exception
     */
    public abstract Phone getPhone() throws Exception;

    /**
     * Provides an {@link PhoneValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract PhoneValidator getPhoneValidator() throws Exception;



    /**
     * Checks if an {@link Phone} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Phone instanceUnderTest, Boolean expectedValid) throws Exception {
        PhoneValidator validator = getPhoneValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link Phone} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Phone getValidPhoneInstance() throws Exception {
        Phone instance = getPhone();

        instance.setPhoneNumber(PhoneNumberUtil.getInstance().getExampleNumber("DE"));

        return instance;
    }
}
