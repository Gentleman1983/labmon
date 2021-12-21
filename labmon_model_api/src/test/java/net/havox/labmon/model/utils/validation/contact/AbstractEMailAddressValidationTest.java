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

import net.havox.labmon.model.api.contact.EMailAddress;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of {@link EMailAddressValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractEMailAddressValidationTest {
    /**
     * Provides an {@link EMailAddress} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract EMailAddress getEMailAddress() throws Exception;

    /**
     * Provides an {@link EMailAddressValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract EMailAddressValidator getEMailAddressValidator() throws Exception;

    /**
     * Tests if a valid {@link EMailAddress} entity is valid.
     * <p>
     * Given: a randomized {@link EMailAddress} entity
     * And: having all necessary attributes
     * When: validating the {@link EMailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testValidEMailAddressInstanceIsValid() throws Exception {
        EMailAddress instanceUnderTest = getValidEMailAddressInstance();

        checkValidInstance(instanceUnderTest, true);
    }

    /**
     * Tests if a {@code null} {@link EMailAddress} entity is invalid.
     * <p>
     * Given: a {@code null} {@link EMailAddress} entity
     * When: validating the {@link EMailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @Test
    public void testNullEMailAddressIsInvalid() throws Exception {
        checkValidInstance(null, false);
    }

    /**
     * Tests if a {@link EMailAddress} entity missing the first name is invalid.
     * <p>
     * Given: a randomized {@link EMailAddress} entity
     * And: missing a email address attribute
     * When: validating the {@link EMailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testEMailAddressWithoutEmailAddressIsInvalid() throws Exception {
        EMailAddress instanceUnderTest = getValidEMailAddressInstance();

        instanceUnderTest.setEMailAddress(null);
        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Tests if a {@link EMailAddress} entity with empty the first name is invalid.
     * <p>
     * Given: a randomized {@link EMailAddress} entity
     * And: having an empty email address attribute
     * When: validating the {@link EMailAddress} entity
     * Then: the validation result should be invalid
     *
     * @throws Exception
     */
    @RepeatedTest(5)
    public void testEMailAddressWithEmptyEmailAddressIsInvalid() throws Exception {
        EMailAddress instanceUnderTest = getValidEMailAddressInstance();

        instanceUnderTest.setEMailAddress("");

        checkValidInstance(instanceUnderTest, false);
    }

    /**
     * Checks if an {@link EMailAddress} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(EMailAddress instanceUnderTest, Boolean expectedValid) throws Exception {
        EMailAddressValidator validator = getEMailAddressValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link EMailAddress} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private EMailAddress getValidEMailAddressInstance() throws Exception {
        EMailAddress instance = getEMailAddress();

        String emailPrefix = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),
                ModelRandomGenerator.ALPHANUMERIC_STRING);
        String emailSuffix = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 20),
                ModelRandomGenerator.ALPHABETIC_STRING) + ".example.de";
        String email = emailPrefix + "@" + emailSuffix;

        boolean allowLocalDomains = false;
        boolean allowTopLevelDomains = false;

        DomainValidator domainValidator = DomainValidator.getInstance(allowLocalDomains);
        EmailValidator emailValidator = new EmailValidator(allowLocalDomains, allowTopLevelDomains, domainValidator);

        Assertions.assertTrue(emailValidator.isValid(email));

        instance.setEMailAddress(email);

        return instance;
    }
}
