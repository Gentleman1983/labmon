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

import net.havox.labmon.model.utils.validation.contact.messenger.ThreemaValidator;
import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Abstract implementation of API test of {@link Threema}.
 *
 * @author Christian Otto
 */
@ExtendWith(MockitoExtension.class)
public abstract class AbstractThreemaTest {
    @Mock
    private Threema mockedInstance;

    /**
     * Provides an {@link Threema} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract Threema getThreema() throws Exception;

    /**
     * Test modification of the street.
     * <p>
     * Given: An {@link Threema} instance
     * When: modifying the street attribute ({@link Threema#setThreemaId(String)} )
     * Then: than the street attribute ({@link Threema#getThreemaId()} ) should contain the new value
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyThreemaId() throws Exception {
        String alphabet = " -" + ModelRandomGenerator.ALPHABETIC_STRING;
        String threemaId = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);

        Threema objectUnderTest = getThreema();
        String oldThreemaId = objectUnderTest.getThreemaId();

        while (threemaId.equals(oldThreemaId)) {
            threemaId = ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50), alphabet);
        }
        Assertions.assertNotEquals(threemaId, oldThreemaId);

        objectUnderTest.setThreemaId(threemaId);
        Assertions.assertEquals(threemaId, objectUnderTest.getThreemaId());
        Assertions.assertNotEquals(oldThreemaId, objectUnderTest.getThreemaId());
    }

    /**
     * Tests if a proper validator is provided.
     * <p>
     * Given: A {@link Threema} instance
     * When: calling {@link Threema#getContactOptionValidator()}
     * Then: the result is not {@code null}
     * And: the result is of type {@link ThreemaValidator}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testInstanceValidator() throws Exception {
        Threema objectUnderTest = getThreema();

        Assertions.assertNotNull(objectUnderTest.getContactOptionValidator());
        Assertions.assertTrue(objectUnderTest.getContactOptionValidator() instanceof ThreemaValidator);
    }

    /**
     * Tests if {@link Threema#isValid()} results in an exception.
     */
    @Test
    public void testIsValidDoesNotResultInErrors() {
        Assertions.assertNotNull(mockedInstance);
        mockedInstance.isValid();
    }
}
