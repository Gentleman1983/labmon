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

package net.havox.labmon.model.basic.permissions;

import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.api.permissions.Permission;
import net.havox.labmon.model.api.permissions.PermissionStatus;
import net.havox.labmon.model.api.permissions.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Basic implementation of {@link Role} interface.
 *
 * @author Christian Otto
 */
public class BasicRole extends AbstractChangeAwareAndIdentifiableClass implements Role {
    private String name;
    private final Set<Role> roles = new HashSet<>();
    private final Map<Permission, PermissionStatus> permissions = new HashMap<>();

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
        return roles;
    }

    @Override
    public boolean addRole(Role role) {
        roles.add(role);
        return roles.contains(role);
    }

    @Override
    public boolean removeRole(Role role) {
        roles.remove(role);
        return !roles.contains(role);
    }

    @Override
    public Map<Permission, PermissionStatus> getIncludedPermissions() {
        return permissions;
    }

    @Override
    public boolean addPermission(Permission permission, PermissionStatus status) {
        permissions.put(permission, status);
        return permissions.containsKey(permission);
    }

    @Override
    public boolean removePermission(Permission permission) {
        permissions.remove(permission);
        return !permissions.containsKey(permission);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

        builder.append("id", getId());
        builder.append("name", getName());
        builder.append("includedRoles", getIncludedRoles());
        builder.append("includedPermissions", getIncludedPermissions());
        builder.append("version", getVersion());

        return builder.build();
    }
}
