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

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.address.City;
import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.model.api.contact.MailAddress;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link MailAddressValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractMailAddressValidationTest {
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
     * Provides an {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides an {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Provides an {@link MailAddressValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract MailAddressValidator getMailAddressValidator() throws Exception;

    /**
     * Tests if a valid {@link MailAddress} entity is valid.
     * <p>
     * Given: a randomized {@link MailAddress} entity
     * And: having all necessary attributes
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidMailAddressInstanceIsValid() throws Exception {
        MailAddress instanceUnderTest = getValidMailAddressInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link MailAddress} entity is invalid.
     * <p>
     * Given: a {@code null} {@link MailAddress} entity
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testNullMailAddressIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link MailAddress} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link MailAddress} entity
     * And: missing a receiver attribute
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testMailAddressWithoutReceiverIsInvalid() throws Exception {
        MailAddress instanceUnderTest = getValidMailAddressInstance();

        instanceUnderTest.setReceiver(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link MailAddress} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link MailAddress} entity
     * And: having an empty receiver attribute
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testMailAddressWithEmptyReceiverIsInvalid() throws Exception {
        MailAddress instanceUnderTest = getValidMailAddressInstance();

        instanceUnderTest.setReceiver("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link MailAddress} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link MailAddress} entity
     * And: missing an address attribute
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testMailAddressWithoutAddressIsInvalid() throws Exception {
        MailAddress instanceUnderTest = getValidMailAddressInstance();

        instanceUnderTest.setAddress(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link MailAddress} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link MailAddress} entity
     * And: having an invalid address attribute
     * When: validating the {@link MailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testMailAddressWithInvalidAddressIsInvalid() throws Exception {
        MailAddress instanceUnderTest = getValidMailAddressInstance();

        Address invalidAddress = getValidAddressInstance();
        invalidAddress.setStreet("");

        instanceUnderTest.setAddress(invalidAddress);

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link MailAddress} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(MailAddress instanceUnderTest, Boolean expectedValid) throws Exception {
        MailAddressValidator validator = getMailAddressValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the mail address '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " + validator.validate(instanceUnderTest) +
                        ".");
        if (null != instanceUnderTest) {
            Assertions.assertEquals(expectedValid, instanceUnderTest.isValid(),
                    "Expected the mail address '" + instanceUnderTest + "' " + (expectedValid ? "" : " not") +
                            "to be a valid instance.");
        }
    }

    /**
     * Provides a valid {@link MailAddress} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private MailAddress getValidMailAddressInstance() throws Exception {
        MailAddress instance = getMailAddress();

        instance.setReceiver(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setAddress(getValidAddressInstance());

        return instance;
    }

    /**
     * Provides a valid {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Address getValidAddressInstance() throws Exception {
        Address instance = getAddress();

        instance.setStreet(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setHouseNumber(Integer.toString(ModelRandomGenerator.randomInt(9999)));
        instance.setCity(getValidCityInstance());

        return instance;
    }

    /**
     * Provides a valid {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private City getValidCityInstance() throws Exception {
        City instance = getCity();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setZipCode(Integer.toString(ModelRandomGenerator.randomIntInRange(10000, 99999)));
        instance.setCountry(getValidCountryInstance());

        return instance;
    }

    /**
     * Provides a valid {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Country getValidCountryInstance() throws Exception {
        Country instance = getCountry();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
