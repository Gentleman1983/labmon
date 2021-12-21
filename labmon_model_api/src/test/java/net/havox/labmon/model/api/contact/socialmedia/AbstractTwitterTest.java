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

package net.havox.labmon.model.api.contact.socialmedia;

import net.havox.labmon.model.utils.validation.contact.socialmedia.TwitterValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Abstract implementation of API test of {@link Twitter}.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractTwitterTest {
    @Mock private Twitter mockedInstance;

    /**
     * Provides an {@link Twitter} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Twitter getTwitter() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Twitter} instance
     * When: modifying the street attribute ({@link Twitter#setUserName(String)})
     * Then: than the street attribute ({@link Twitter#getUserName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserName() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Twitter objectUnderTest = getTwitter();
        String oldUserName = objectUnderTest.getUserName();

        while (userName.equals(oldUserName)) {
            userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(userName, oldUserName);

        objectUnderTest.setUserName(userName);
        Assertions.assertEquals(userName, objectUnderTest.getUserName());
        Assertions.assertNotEquals(oldUserName, objectUnderTest.getUserName());
    }

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Twitter} instance
     * And: having a username
     * When: getting the profile link ({@link Twitter#getLinkToProfile()} )
     * Then: than the expected link should equal "{@code https://twitter.com/$username}"
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testGetLinkToProfile() throws Exception {
        String userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), ModelRandomGenerator.ALPHABETIC_STRING);
        Twitter objectUnderTest = getTwitter();
        objectUnderTest.setUserName(userName);
        Assertions.assertEquals(userName, objectUnderTest.getUserName());

        String profileLink = "https://twitter.com/" + userName;
        Assertions.assertEquals(profileLink, objectUnderTest.getLinkToProfile());
    }

    /**
     * Tests if a proper validator is provided.
     *
     * Given: A {@link Twitter} instance
     * When: calling {@link Twitter#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link TwitterValidator}
     *
     * @throws Exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Twitter objectUnderTest = getTwitter();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof TwitterValidator);
    }

    /**
     * Tests if {@link Twitter#isValid()} results in an exception.
     *
     * @throws Exception
     */
    @Test
    public void testIsValidDoesNotResultInErrors() throws Exception {
        Assertions.assertNotNull(mockedInstance);
        mockedInstance.isValid();
    }
}
