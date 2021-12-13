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

import net.havox.labmon.model.api.ChangeAware;

import static net.havox.labmon.model.api.permissions.PermissionStatus.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This represents a role in the permission management of this application.
 *
 * @author Christian Otto
 */
public interface Role extends ChangeAware, Serializable {
    /**
     * Returns the role name.
     *
     * @return the role name
     */
    String getName();

    /**
     * Sets the role name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * Returns the roles included in this role.
     *
     * @return the roles
     */
    Set<Role> getIncludedRoles();

    /**
     * Adds a role to the included roles.
     *
     * @param role the role
     * @return true if all worked fine
     */
    boolean addRole(Role role);

    /**
     * Removes a role from the included roles.
     *
     * @param role the role
     * @return true if all worked fine
     */
    boolean removeRole(Role role);

    /**
     * Returns the permissions included in this role.
     *
     * @return the permissions.
     */
    Map<Permission, PermissionStatus> getIncludedPermissions();

    /**
     * Adds a permission to the role.
     *
     * @param permission the permission
     * @param status the permission status
     * @return true if all worked fine
     */
    boolean addPermission(Permission permission, PermissionStatus status);

    /**
     * Removes a permission from the role.
     *
     * @param permission the permission
     * @return true if all worked fine
     */
    boolean removePermission(Permission permission);

    /**
     * Returns the active permissions for this role and sub roles.
     *
     * @return the active permissions
     */
    default Set<Permission> getActivePermissionsForRole() {
        Set<Permission> allPermissions = new HashSet<>();

        for (Role role : getIncludedRoles()) {
            if(this.equals(role)) {
                continue;
            }
            allPermissions.addAll(role.getActivePermissionsForRole());
        }

        getIncludedPermissions().forEach((key, value) -> {
            if (ALLOW == value) {
                allPermissions.add(key);
            }
            if (DENY == value) {
                allPermissions.remove(key);
            }
        });

        return allPermissions;
    }
}
