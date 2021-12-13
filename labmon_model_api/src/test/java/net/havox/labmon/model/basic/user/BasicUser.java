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

package net.havox.labmon.model.basic.user;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.api.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Basic implementation of {@link User} interface.
 *
 * @author Christian Otto
 */
public class BasicUser extends AbstractChangeAwareAndIdentifiableClass implements User {
    private String firstName;
    private String middleName;
    private String lastName;
    private Address address;
    private Credentials credentials;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

        builder.append("id", getId());
        builder.append("firstName", getFirstName());
        builder.append("middleName", getMiddleName());
        builder.append("lastName", getLastName());
        builder.append("address", getAddress());
        builder.append("credentials", getCredentials());
        builder.append("version", getVersion());

        return builder.build();
    }
}
