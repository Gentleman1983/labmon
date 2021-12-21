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

package net.havox.labmon.model.impl.user;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This represents the functional entity of an {@link User}.
 *
 * @author Christian Otto
 */
public class UserImpl extends AbstractChangeAwareClass<UserImpl> implements User {
    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's middle name(s).
     */
    private String middleName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The user's address.
     */
    private Address address;

    /**
     * The user's credentials.
     */
    private Credentials credentials;

    /**
     * The user's contact options.
     */
    private Set<ContactOption<?>> contactOptions = new CopyOnWriteArraySet<>();

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
    public Set<ContactOption<? extends ContactOption>> getContactOptions() {
        return Collections.unmodifiableSet(contactOptions);
    }

    @Override
    public boolean addContactOptions(Collection<ContactOption<?>> options) {
        contactOptions.addAll(options);
        return true;
    }

    @Override
    public boolean removeContactOptions(Collection<ContactOption<?>> options) {
        contactOptions.removeAll(options);
        return true;
    }
}
