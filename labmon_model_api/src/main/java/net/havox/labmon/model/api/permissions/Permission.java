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

import java.io.Serializable;

/**
 * This represents a single permission on this application. It may be granted or forbidden for a given user.
 *
 * @author Christian Otto
 */
public interface Permission extends ChangeAware, Serializable {
    /**
     * Returns the name of the permission.
     *
     * @return the name
     */
    String getName();

    /**
     * Allows to set the permission name.
     *
     * @param name the permission name
     */
    void setName(String name);
}
