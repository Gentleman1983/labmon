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

package net.havox.labmon.model.impl.contact;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.contact.MailAddress;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.contact.ContactOptionValidator;

public class MailAddressImpl extends AbstractChangeAwareClass<MailAddressImpl> implements MailAddress {
    @Override
    public ContactOptionValidator getContactOptionValidator() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getReceiver() {
        return null;
    }

    @Override
    public void setReceiver(String receiver) {

    }

    @Override
    public Address getAddress() {
        return null;
    }

    @Override
    public void setAddress(Address address) {

    }
}
