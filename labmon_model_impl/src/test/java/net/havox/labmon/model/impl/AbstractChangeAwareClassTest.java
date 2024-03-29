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

package net.havox.labmon.model.impl;

import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract test class for {@link AbstractChangeAwareClass} classes.
 *
 * @author Christian Otto
 */
public abstract class AbstractChangeAwareClassTest<T extends AbstractChangeAwareClass<T>> {
    private static final Random randomGenerator = new Random(System.nanoTime());

    /**
     * Provides an {@link AbstractChangeAwareClass<T>} entity having a given id and version.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract T getNewInstance() throws Exception;

    /**
     * Given: A {@link AbstractChangeAwareClass}
     * And: having {@code null} id attribute
     * And: having 1 version attribute
     * When: modifying the id attribute ({@link AbstractChangeAwareClass#setId(Long)})
     * Then: the new id shall be set ({@link AbstractChangeAwareClass#getId()})
     *
     * @throws Exception in case of an exception
     */
    @RepeatedTest(25)
    public void testModifyId() throws Exception {
        Long newId = ModelRandomGenerator.randomBoolean() ? ModelRandomGenerator.randomLong() : null;

        T objectUnderTest = getNewInstance(null, 1);
        Long oldId = objectUnderTest.getId();
        boolean newIdMatchesOldId = (newId == oldId);

        objectUnderTest.setId(newId);
        Assertions.assertEquals(newId, objectUnderTest.getId());
        if (newIdMatchesOldId) {
            Assertions.assertEquals(oldId, objectUnderTest.getId());
        } else {
            Assertions.assertNotEquals(oldId, objectUnderTest.getId());
        }
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking for equality on the entities
     * Then: reflexive equality shall be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsReflexive() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());
        T instance3 = getNewInstance(null, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertNotNull(instance3);

        Assertions.assertEquals(instance1, instance1);
        Assertions.assertEquals(instance2, instance2);
        Assertions.assertEquals(instance3, instance3);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking for equality on the entities
     * Then: symmetric equality shall be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsSymmetric() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(1L, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);

        Assertions.assertEquals(instance1, instance2);
        Assertions.assertEquals(instance2, instance1);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking for equality on the entities
     * Then: transitive equality shall be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsTransitive() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(1L, randomGenerator.nextLong());
        T instance3 = getNewInstance(1L, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertNotNull(instance3);

        Assertions.assertEquals(instance1, instance2);
        Assertions.assertEquals(instance2, instance3);
        Assertions.assertEquals(instance1, instance3);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking repeatedly for equality on the entities
     * Then: there shall be no changes in equality
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsConsistence() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);

        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(instance1, instance1);
        }
        for (int j = 0; j < 100; j++) {
            Assertions.assertEquals(instance2, instance2);
        }
        for (int k = 0; k < 100; k++) {
            Assertions.assertEquals(instance1, instance1);
            Assertions.assertEquals(instance2, instance2);
        }
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * And: a {@code null} entity
     * When: checking for equality against {@code null entity}
     * Then: equality shall not be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsNotEqualsNull() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());
        T instance3 = getNewInstance(null, randomGenerator.nextLong());
        T nullInstance = null;

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertNotNull(instance3);
        Assertions.assertNull(nullInstance);

        Assertions.assertNotEquals(instance1, nullInstance);
        Assertions.assertNotEquals(instance2, nullInstance);
        Assertions.assertNotEquals(instance3, nullInstance);

        Assertions.assertNotEquals(nullInstance, instance1);
        Assertions.assertNotEquals(nullInstance, instance2);
        Assertions.assertNotEquals(nullInstance, instance3);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking for equality on the entities
     * And: one entity has {@code null} id value and the other not {@code null} value
     * Then: equality shall not be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsNoIdInequality() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(null, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);

        Assertions.assertNotEquals(instance1, instance2);
        Assertions.assertNotEquals(instance2, instance1);
    }

    /**
     * Given: An {@link AbstractChangeAwareClass} entity
     * And: an {@link Object} entity
     * When: checking for equality on the entities
     * Then: equality shall not be given
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testEqualsNewObject() throws Exception {
        T instance = getNewInstance(1L, randomGenerator.nextLong());

        Assertions.assertNotNull(instance);

        Object notEqualObject = new Object();

        Assertions.assertNotSame(instance, notEqualObject, "Both objects should not be equal.");
        Assertions.assertNotNull(notEqualObject, "The object should not be NULL.");
        Assertions.assertNotSame(instance.getClass(), notEqualObject.getClass(), "Both objects should not be of the same class.");

        Assertions.assertNotEquals(notEqualObject, instance);
        Assertions.assertNotEquals(instance, notEqualObject);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking hash code ({@link AbstractChangeAwareClass#hashCode()})
     * Then: a hash code shall be generated
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testHashCode() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());
        T instance3 = getNewInstance(null, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertNotNull(instance3);

        Integer hashCode1 = instance1.hashCode();
        Integer hashCode2 = instance2.hashCode();
        Integer hashCode3 = instance3.hashCode();

        Assertions.assertNotNull(hashCode1);
        Assertions.assertNotNull(hashCode2);
        Assertions.assertNotNull(hashCode3);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: checking repeatedly hash code ({@link AbstractChangeAwareClass#hashCode()})
     * Then: the hash code shall not change
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testHashCodeConsistency() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());
        T instance3 = getNewInstance(null, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertNotNull(instance3);

        Integer hashCode1 = instance1.hashCode();
        Integer hashCode2 = instance2.hashCode();
        Integer hashCode3 = instance3.hashCode();

        Assertions.assertNotNull(hashCode1);
        Assertions.assertNotNull(hashCode2);
        Assertions.assertNotNull(hashCode3);

        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(hashCode1, instance1.hashCode());
            Assertions.assertEquals(hashCode2, instance2.hashCode());
            Assertions.assertEquals(hashCode3, instance3.hashCode());
        }
    }

    /**
     * Given: Several equal {@link AbstractChangeAwareClass} entities
     * When: checking hash code ({@link AbstractChangeAwareClass#hashCode()})
     * Then: the same hash code shall be generated
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testHashCodeEqualObjects() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(1L, randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);

        Assertions.assertEquals(instance1, instance2);

        Integer hashCode1 = instance1.hashCode();
        Integer hashCode2 = instance2.hashCode();

        Assertions.assertNotNull(hashCode1);
        Assertions.assertNotNull(hashCode2);

        Assertions.assertEquals(hashCode1, hashCode2);
    }

    /**
     * Given: An {@link AbstractChangeAwareClass} entity
     * When: incrementing the version attribute ({@link AbstractChangeAwareClass#incrementVersion()})
     * Then: the version attribute shall be incremented by 1
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testIncrementVersion() throws Exception {
        T instance = getNewInstance(randomGenerator.nextLong(), randomGenerator.nextLong());

        Assertions.assertNotNull(instance);

        long versionInstance = instance.getVersion();

        instance.incrementVersion();

        Assertions.assertEquals(instance.getVersion(), versionInstance + 1);
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: repeatedly incrementing the version attribute ({@link AbstractChangeAwareClass#incrementVersion()})
     * Then: the version attribute shall be incremented by 1 per increment
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testIncrementVersionConsistence() throws Exception {
        T instance1 = getNewInstance(randomGenerator.nextLong(), randomGenerator.nextLong());
        T instance2 = getNewInstance(randomGenerator.nextLong(), randomGenerator.nextLong());

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);

        long versionInstance1 = instance1.getVersion();
        long versionInstance2 = instance2.getVersion();

        for (int i = 1; i < 100; i++) {
            instance1.incrementVersion();
            instance2.incrementVersion();

            Assertions.assertEquals(instance1.getVersion(), versionInstance1 + i);
            Assertions.assertEquals(instance2.getVersion(), versionInstance2 + i);
        }
    }

    /**
     * Given: An {@link AbstractChangeAwareClass} entity
     * When: incrementing the version attribute ({@link AbstractChangeAwareClass#incrementVersion()})
     * Then: No changes on class attributes occur except the version attribute
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testIncrementVersionSinglePointOfChange() throws Exception {
        T instance = getNewInstance(randomGenerator.nextLong(), randomGenerator.nextLong());

        Assertions.assertNotNull(instance);

        Map<String, Object> elementMap = new HashMap<>();

        String classPrefix = "Â§CLASSÂ§";
        String superclassPrefix = "Â§SUPERCLASSÂ§";

        // Read field values to elementMap...
        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue = field.get(instance);

            elementMap.put(classPrefix + fieldName, fieldValue);
        }
        for (Field field : instance.getClass().getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue = field.get(instance);

            elementMap.put(superclassPrefix + fieldName, fieldValue);
        }

        instance.incrementVersion();

        // Now check that no differences except the version did occur.
        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue = field.get(instance);

            Assertions.assertEquals(elementMap.get(classPrefix + fieldName), fieldValue, "Field '" + fieldName + "': Expected <" + elementMap.get(classPrefix + fieldName) + ">, but was <" + fieldValue + ">.");
        }
        for (Field field : instance.getClass().getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue = field.get(instance);

            // Only the superclass version will be changed.
            if (fieldName.equals("version")) {
                Long expectedValue = (Long) elementMap.get(superclassPrefix + fieldName) + 1;
                Assertions.assertEquals(expectedValue, fieldValue, "Field '" + fieldName + "': Expected <" + expectedValue + ">, but was <" + fieldValue + ">.");
            } else {
                Assertions.assertEquals(elementMap.get(superclassPrefix + fieldName), fieldValue, "Field '" + fieldName + "': Expected <" + elementMap.get(superclassPrefix + fieldName) + ">, but was <" + fieldValue + ">.");
            }
        }
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: calling {@link AbstractChangeAwareClass#toString()} method
     * Then: this shall not result in an {@link Exception}
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testToStringNoExceptionTest() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());
        T instance3 = getNewInstance(null, randomGenerator.nextLong());

        instance1.toString();
        instance2.toString();
        instance3.toString();

        Assertions.assertTrue(true, "If it has run to this moment, pass test.");
    }

    /**
     * Given: Several {@link AbstractChangeAwareClass} entities
     * When: comparing entities ({@link AbstractChangeAwareClass#compareTo(AbstractChangeAwareClass)})
     * Then: the result shall match expectations (check on id attribute value)
     *
     * @throws Exception in case of an exception
     */
    @Test
    public void testCompareToTest() throws Exception {
        T instance1 = getNewInstance(1L, randomGenerator.nextLong());
        T instance2 = getNewInstance(2L, randomGenerator.nextLong());

        Assertions.assertEquals(instance1, instance1); // NOSONAR This self-assertion is intended.
        Assertions.assertEquals(0, instance1.compareTo(instance1), "An instance should always be equal to itself.");

        Assertions.assertNotEquals(instance1, instance2);
        Assertions.assertTrue(instance1.compareTo(instance2) < 0, "Instance 1 should have the lower ID.");
        Assertions.assertTrue(instance2.compareTo(instance1) > 0, "Instance 1 should have the lower ID.");
    }

    /**
     * Provides an {@link AbstractChangeAwareClass} entity having a given id and version.
     *
     * @param id      the id
     * @param version the version
     * @return the entity
     * @throws Exception in case of an exception
     */
    private T getNewInstance(Long id, long version) throws Exception {
        T clazz = getNewInstance();

        clazz.setId(id);

        // For several tests we need to set the version parameter.
        Field versionField = clazz.getClass().getSuperclass().getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(clazz, version);

        return clazz;
    }
}
