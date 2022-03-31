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
import net.havox.labmon.model.api.contact.AbstractMailAddressTest;
import net.havox.labmon.model.api.contact.MailAddress;
import net.havox.labmon.model.impl.address.AddressImpl;

/**
 * API specific test of {@link MailAddressImpl}.
 *
 * @author Christian Otto
 */
public class MailAddressApiTest extends AbstractMailAddressTest { // NOSONAR API test is only inherited, so only derived test cases.
    @Override
    public MailAddress getMailAddress() {
        return new MailAddressImpl();
    }

    @Override
    public Address getAddress() {
        return new AddressImpl();
    }
}
