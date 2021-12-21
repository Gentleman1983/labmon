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

package net.havox.labmon.model.utils.validation.contact.messenger;

import net.havox.labmon.model.api.contact.messenger.Skype;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

/**
 * Abstract implementation of {@link SkypeValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractSkypeValidationTest {
    /**
     * Provides an {@link Skype} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Skype getSkype() throws Exception;

    /**
     * Provides an {@link SkypeValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract SkypeValidator getSkypeValidator() throws Exception;

    /**
     * - A skype username consists of 6 to 22 characters
     *  * - It starts with a letter
     */


    /**
     * Checks if an {@link Skype} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Skype instanceUnderTest, Boolean expectedValid) throws Exception {
        SkypeValidator validator = getSkypeValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link Skype} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Skype getValidSkypeInstance() throws Exception {
        Skype instance = getSkype();

        do {
            instance.setUserName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(6, 22),
                    ModelRandomGenerator.ALPHANUMERIC_STRING));
        } while (StringUtils.isAlpha(instance.getUserName().substring(0, 1)));

        return instance;
    }
}
