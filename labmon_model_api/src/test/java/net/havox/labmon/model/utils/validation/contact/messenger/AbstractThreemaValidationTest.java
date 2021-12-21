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

import net.havox.labmon.model.api.contact.messenger.Threema;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;

/**
 * Abstract implementation of {@link ThreemaValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractThreemaValidationTest {
    /**
     * Provides an {@link Threema} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Threema getThreema() throws Exception;

    /**
     * Provides an {@link ThreemaValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract ThreemaValidator getThreemaValidator() throws Exception;

    /**
     * - A Threema Id is an 8-digit alphanumeric string
     */


    /**
     * Checks if an {@link Threema} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Threema instanceUnderTest, Boolean expectedValid) throws Exception {
        ThreemaValidator validator = getThreemaValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link Threema} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Threema getValidThreemaInstance() throws Exception {
        Threema instance = getThreema();

        instance.setThreemaId(ModelRandomGenerator.randomString(8, ModelRandomGenerator.ALPHANUMERIC_STRING));

        return instance;
    }
}
