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
import net.havox.labmon.model.utils.validation.contact.MailAddressImplValidator;

/**
 * The functional representation of a {@link MailAddress}.
 *
 * @author Christian Otto
 */
public class MailAddressImpl extends AbstractChangeAwareClass<MailAddressImpl> implements MailAddress {
    /**
     * The receiver.
     */
    private String receiver;

    /**
     * The receiver's address.
     */
    private Address address;

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
    public MailAddressImplValidator getContactOptionValidator() {
        return new MailAddressImplValidator();
    }

    @Override
    public boolean isValid() {
        return getContactOptionValidator().validate(this);
    }
}
