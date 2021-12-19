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

package net.havox.labmon.model.api.contact.messenger;

import net.havox.labmon.model.utils.validation.contact.messenger.SkypeValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract implementation of API test of {@link Skype}.
 *
 * @author Christian Otto
 */
public abstract class AbstractSkypeTest {

    /**
     * Provides an {@link Skype} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Skype getSkype() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Skype} instance
     * When: modifying the street attribute ({@link Skype#setUserName(String)})
     * Then: than the street attribute ({@link Skype#getUserName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyUserName() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String userName = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Skype objectUnderTest = getSkype();
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
     * Tests if a proper validator is provided.
     *
     * Given: A {@link Skype} instance
     * When: calling {@link Skype#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link SkypeValidator}
     *
     * @throws Exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Skype objectUnderTest = getSkype();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof SkypeValidator);
    }
}
