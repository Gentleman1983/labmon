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
 * This represents a user credential of this application.
 *
 * @author Christian Otto
 */
public interface Credentials extends ChangeAware, Serializable {
    /**
     * Returns the username.
     *
     * @return the username
     */
    String getUserName();

    /**
     * Sets the username.
     *
     * @param userName the username
     */
    void setUserName(String userName);

    /**
     * Returns the authentication method.
     *
     * @return the authentication method
     */
    AuthenticationMethod getAuthentication();

    /**
     * Sets the authentication method.
     *
     * @param authentication the authentication method
     */
    void setAuthentication(AuthenticationMethod authentication);
}
