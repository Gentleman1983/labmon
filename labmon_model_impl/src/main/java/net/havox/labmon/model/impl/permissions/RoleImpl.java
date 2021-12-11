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

package net.havox.labmon.model.impl.permissions;

import net.havox.labmon.model.api.permissions.Permission;
import net.havox.labmon.model.api.permissions.PermissionStatus;
import net.havox.labmon.model.api.permissions.Role;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * This represents the functional entity of a permission role of this project.
 */
public class RoleImpl implements Role {
    private String name;
    private Set<Role> includedRoles = new ConcurrentSkipListSet<>();
    private Map<Permission, PermissionStatus> includedPermissions = new ConcurrentHashMap<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<Role> getIncludedRoles() {
        return Collections.unmodifiableSet(includedRoles);
    }

    @Override
    public boolean addRole(Role role) {
        includedRoles.add(role);
        return includedRoles.contains(role);
    }

    @Override
    public boolean removeRole(Role role) {
        includedRoles.remove(role);
        return !includedRoles.contains(role);
    }

    @Override
    public Map<Permission, PermissionStatus> getIncludedPermissions() {
        return Collections.unmodifiableMap(includedPermissions);
    }

    @Override
    public boolean addPermission(Permission permission, PermissionStatus status) {
        includedPermissions.put(permission, status);
        return includedPermissions.containsKey(permission);
    }

    @Override
    public boolean removePermission(Permission permission) {
        includedPermissions.remove(permission);
        return !includedPermissions.containsKey(permission);
    }
}
