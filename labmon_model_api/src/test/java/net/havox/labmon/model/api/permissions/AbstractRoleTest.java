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

package net.havox.labmon.model.api.permissions;

import net.havox.labmon.testutils.random.ModelRandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Abstract implementation of API test of {@link Role}.
 *
 * @author Christian Otto
 */
public abstract class AbstractRoleTest {
    private long currentId = 1;

    /**
     * Provides a {@link Permission} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Permission getPermission() throws Exception;

    /**
     * Provides a {@link Role} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Role getRole() throws Exception;

    /**
     * Tests if changes on the name property work properly.
     * <p>
     * Given: A {@link Role} instance
     * When: modifying the name attribute ({@link Role#setName(String)})
     * Then: than the name attribute ({@link Role#getName()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testModifyRoleName() throws Exception {
        Role instanceUnderTest = getRole();
        String instanceOriginalName = instanceUnderTest.getName();

        String name;
        do {
            name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING);
        }
        while (name.equals(instanceOriginalName));

        Assertions.assertEquals(instanceOriginalName, instanceUnderTest.getName());

        instanceUnderTest.setName(name);

        Assertions.assertNotEquals(instanceOriginalName, instanceUnderTest.getName());
        Assertions.assertEquals(name, instanceUnderTest.getName());
    }

    /**
     * Tests if a sub role can be added properly.
     * <p>
     * Given: A {@link Role} instance
     * When: adding a sub role ({@link Role#addRole(Role)})
     * Then: than the sub roles ({@link Role#getIncludedRoles()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddRole() throws Exception {
        Role instanceUnderTest = getRandomRole();

        Role subRole;
        do {
            subRole = getRandomRole();
        }
        while (instanceUnderTest.getIncludedRoles().contains(subRole));

        Assertions.assertFalse(instanceUnderTest.getIncludedRoles().contains(subRole));

        instanceUnderTest.addRole(subRole);

        Assertions.assertTrue(instanceUnderTest.getIncludedRoles().contains(subRole));
    }

    /**
     * Tests if a sub role can be removed properly.
     *
     * <p>
     * Given: A {@link Role} instance
     * And: containing a sub {@link Role}
     * When: removing the sub role ({@link Role#removeRole(Role)})
     * Then: than the roles ({@link Role#getIncludedRoles()}) should not contain the value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemoveRole() throws Exception {
        Role instanceUnderTest = getRandomRole();

        Role subRole;
        do {
            subRole = getRandomRole();
        }
        while (instanceUnderTest.getIncludedRoles().contains(subRole));

        Assertions.assertFalse(instanceUnderTest.getIncludedRoles().contains(subRole));

        instanceUnderTest.addRole(subRole);

        Assertions.assertTrue(instanceUnderTest.getIncludedRoles().contains(subRole));

        boolean removalResult = instanceUnderTest.removeRole(subRole);

        Assertions.assertFalse(instanceUnderTest.getIncludedRoles().contains(subRole));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removeRole(subRole);

        Assertions.assertFalse(instanceUnderTest.getIncludedRoles().contains(subRole));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if a sub permission can be added properly.
     * <p>
     * Given: A {@link Role} instance
     * When: adding a permission ({@link Role#addPermission(Permission, PermissionStatus)})
     * Then: than the permission ({@link Role#getIncludedPermissions()}) should contain the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testAddPermission() throws Exception {
        Role instanceUnderTest = getRandomRole();

        Permission subPermission;
        do {
            subPermission = getRandomPermission();
        }
        while (instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        Assertions.assertFalse(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        PermissionStatus permissionStatus = ModelRandomGenerator.randomBoolean() ? PermissionStatus.ALLOW : PermissionStatus.DENY;
        instanceUnderTest.addPermission(subPermission, permissionStatus);

        Assertions.assertTrue(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));
        Assertions.assertEquals(permissionStatus, instanceUnderTest.getIncludedPermissions().get(subPermission));
    }

    /**
     * Tests if a sub permission can be removed properly.
     * <p>
     * Given: A {@link Role} instance
     * And: containing a {@link Permission}
     * When: removing the permission ({@link Role#removePermission(Permission)})
     * Then: than the permissions ({@link Role#getIncludedPermissions()} ) should contain not the new value
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testRemovePermission() throws Exception {
        Role instanceUnderTest = getRandomRole();

        Permission subPermission;
        do {
            subPermission = getRandomPermission();
        }
        while (instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        Assertions.assertFalse(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        PermissionStatus permissionStatus = ModelRandomGenerator.randomBoolean() ? PermissionStatus.ALLOW : PermissionStatus.DENY;
        instanceUnderTest.addPermission(subPermission, permissionStatus);

        Assertions.assertTrue(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));
        Assertions.assertEquals(permissionStatus, instanceUnderTest.getIncludedPermissions().get(subPermission));

        boolean removalResult = instanceUnderTest.removePermission(subPermission);

        Assertions.assertFalse(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));
        Assertions.assertTrue(removalResult);

        // Deleting another time should not change anything...
        removalResult = instanceUnderTest.removePermission(subPermission);

        Assertions.assertFalse(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));
        Assertions.assertTrue(removalResult);
    }

    /**
     * Tests if changes to the permissions are provided properly.
     * <p>
     * Given: A {@link Role} instance
     * When: modifying a {@link Permission} or changing the {@link PermissionStatus}
     * Then: the changes shall be correctly mapped to the permissions ({@link Role#getIncludedPermissions()})
     *
     * @throws Exception
     */
    @RepeatedTest(25)
    public void testGetActivePermissionsForRole() throws Exception {
        Role instanceUnderTest = getRandomRole();

        Permission subPermission;
        do {
            subPermission = getRandomPermission();
        }
        while (instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        Assertions.assertFalse(instanceUnderTest.getIncludedPermissions().containsKey(subPermission));

        Set<Permission> includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertNotNull(includedPermissions);
        Assertions.assertFalse(includedPermissions.contains(subPermission));

        instanceUnderTest.addPermission(subPermission, PermissionStatus.DENY);
        includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertFalse(includedPermissions.contains(subPermission));

        instanceUnderTest.removePermission(subPermission);
        instanceUnderTest.addPermission(subPermission, PermissionStatus.ALLOW);
        includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertTrue(includedPermissions.contains(subPermission));
    }

    /**
     * Test if an {@link PermissionStatus#ALLOW} {@link Permission} on a sub {@link Role} results in an entry in the
     * active permissions.
     * <p>
     * Given: A {@link Role} instance
     * And: containing a sub role having {@link Permission}s in status {@link PermissionStatus#ALLOW}
     * When: calling the active permissions ({@link Role#getActivePermissionsForRole()})
     * Then: than the transitive permissions in status {@link PermissionStatus#ALLOW} shall be contained in the result
     *
     * @throws Exception
     */
    @Test
    public void testGetActivePermissionsForRoleContainsTransitiveAllowPermissions() throws Exception {
        // Create role with sub role that contains an ALLOW permission.
        Permission permission = getRandomPermission();
        Role subRole = getRole();
        subRole.addPermission(permission, PermissionStatus.ALLOW);
        Role instanceUnderTest = getRole();
        instanceUnderTest.addRole(subRole);

        // This permission should be contained in the result set.
        Set<Permission> includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertTrue(includedPermissions.contains(permission));
    }

    /**
     * Test if an {@link PermissionStatus#DENY} {@link Permission} on a sub {@link Role} does not result in an entry in
     * the active permissions.
     * <p>
     * Given: A {@link Role} instance
     * And: containing a sub role having {@link Permission}s in status {@link PermissionStatus#DENY}
     * When: calling the active permissions ({@link Role#getActivePermissionsForRole()})
     * Then: than the transitive permissions in status {@link PermissionStatus#DENY} shall not be contained in the result
     *
     * @throws Exception
     */
    @Test
    public void testGetActivePermissionsForRoleNotContainsTransitiveDenyPermissions() throws Exception {
        // Create role with sub role that contains an DENY permission.
        Permission permission = getRandomPermission();
        Role subRole = getRole();
        subRole.addPermission(permission, PermissionStatus.DENY);
        Role instanceUnderTest = getRole();
        instanceUnderTest.addRole(subRole);

        // This permission should not be contained in the result set.
        Set<Permission> includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertFalse(includedPermissions.contains(permission));
    }

    /**
     * Test if a loop in {@link Role} dependencies results in never ending recursion on
     * {@link Role#getActivePermissionsForRole()}.
     * <p>
     * Given: A {@link Role} instance
     * And: loops in the graph resulting from the included roles ({@link Role#getIncludedRoles()})
     * When: calling the active permissions ({@link Role#getActivePermissionsForRole()})
     * Then: the call should not result in a never ending recursion
     *
     * @throws Exception
     */
    @Test
    public void testGetActivePermissionsForRoleCheckRecursionLoops() throws Exception {
        // Create role with sub role that contains an ALLOW permission.
        Permission permission = getRandomPermission();
        Role subRole = getRole();
        subRole.addPermission(permission, PermissionStatus.ALLOW);
        Role instanceUnderTest = getRole();
        instanceUnderTest.addRole(subRole);
        subRole.addRole(instanceUnderTest);

        // This permission should be contained in the result set.
        Set<Permission> includedPermissions = instanceUnderTest.getActivePermissionsForRole();
        Assertions.assertTrue(includedPermissions.contains(permission));
    }

    /**
     * Creates a random role instance with up to two random sub roles and up to ten permissions.
     *
     * @return the instance
     * @throws Exception
     */
    private Role getRandomRole() throws Exception {
        Role instance = getRole();
        instance.setId(currentId++);
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING);
        instance.setName(name);

        int numberOfSubRoles = ModelRandomGenerator.randomInt(2);
        for (int i = 0; i < numberOfSubRoles; i++) {
            Role subRole = getRandomRole();
            instance.addRole(subRole);
        }

        int numberOfPermissions = ModelRandomGenerator.randomInt(10);
        for (int i = 0; i < numberOfPermissions; i++) {
            Permission permission = getRandomPermission();
            PermissionStatus permissionStatus = ModelRandomGenerator.randomBoolean() ? PermissionStatus.ALLOW : PermissionStatus.DENY;
            instance.addPermission(permission, permissionStatus);
        }

        return instance;
    }

    /**
     * Creates a random permission.
     *
     * @return the instance
     * @throws Exception
     */
    private Permission getRandomPermission() throws Exception {
        Permission instance = getPermission();
        String name = ModelRandomGenerator.randomString(ModelRandomGenerator.randomInt(100), ModelRandomGenerator.ALPHANUMERIC_STRING);
        instance.setId(currentId++);
        instance.setName(name);
        return instance;
    }
}
