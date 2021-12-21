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

package net.havox.labmon.model.utils.validation.contact.socialmedia;

import net.havox.labmon.model.api.contact.socialmedia.Twitter;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;

/**
 * Abstract implementation of {@link TwitterValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractTwitterValidationTest {
    /**
     * Provides an {@link Twitter} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Twitter getTwitter() throws Exception;

    /**
     * Provides an {@link TwitterValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract TwitterValidator getTwitterValidator() throws Exception;

/**
 * - Twitter usernames are 1 to 15 letters
 *  * - They consist of alphanumeric letters and underscores
 */

    /**
     * Checks if an {@link Twitter} instance has the expected validation status.
     *
     * @param instanceUnderTest the instance
     * @param expectedValid     is the instance expected valid?
     * @throws Exception
     */
    private void checkValidInstance(Twitter instanceUnderTest, Boolean expectedValid) throws Exception {
        TwitterValidator validator = getTwitterValidator();
        Assertions.assertEquals(expectedValid, validator.isValid(instanceUnderTest),
                "Expected the user" + (expectedValid ? "" : " not") +
                        "to be a valid instance. The validation result was " +
                        validator.validate(instanceUnderTest) + ".");
    }

    /**
     * Provides a valid {@link Twitter} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Twitter getValidTwitterInstance() throws Exception {
        Twitter instance = getTwitter();

        String twitterUserNameAlphabet = ModelRandomGenerator.ALPHABETIC_STRING + "_";

        instance.setUserName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 15),
                twitterUserNameAlphabet));

        return instance;
    }
}
