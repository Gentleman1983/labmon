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

package net.havox.labmon.model.api.user;

import net.havox.labmon.model.api.ChangeAware;

import java.io.Serializable;

/**
 * This represents a user of this application.
 *
 * @author Christian Otto
 */
public interface User extends ChangeAware, Serializable {
    /**
     * Returns the first name.
     *
     * @return the first name
     */
    String getFirstName();

    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    void setFirstName(String firstName);

    /**
     * Returns the middle name.
     *
     * @return the middle name
     */
    String getMiddleName();

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name
     */
    void setMiddleName(String middleName);

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    String getLastName();

    /**
     * Sets tge last name.
     *
     * @param lastName the last name
     */
    void setLastName(String lastName);
}
