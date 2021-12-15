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
import net.havox.labmon.model.api.user.AbstractUserTest;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.impl.address.AddressImpl;
import net.havox.labmon.model.impl.contact.EMailAddressImpl;
import net.havox.labmon.model.impl.contact.FaxImpl;
import net.havox.labmon.model.impl.contact.MailAddressImpl;
import net.havox.labmon.model.impl.contact.PhoneImpl;
import net.havox.labmon.model.impl.contact.messenger.SkypeImpl;
import net.havox.labmon.model.impl.contact.messenger.ThreemaImpl;
import net.havox.labmon.model.impl.contact.socialmedia.TwitterImpl;
import net.havox.labmon.testutils.random.ModelRandomGenerator;

/**
 * API specific test for {@link User}.
 *
 * @author Christian Otto
 */
public class UserApiTest extends AbstractUserTest { // NOSONAR API test is only inherited, so only derived test cases.
    @Override
    public User getUser() {
        return new UserImpl();
    }

    @Override
    public Address getAddress() {
        return new AddressImpl();
    }

    @Override
    public Credentials getCredentials() {
        return new CredentialsImpl();
    }

    @Override
    public ContactOption<?> getContactOption() throws Exception {
        return randomContactOption();
    }

    private ContactOption<?> randomContactOption() {
        int selection = ModelRandomGenerator.randomInt(10);

        switch (selection) {
            case 0:
                return new EMailAddressImpl();
            case 1:
                return new MailAddressImpl();
            case 2:
                return new FaxImpl();
            case 3:
                return new PhoneImpl();
            case 4:
                return new SkypeImpl();
            case 5:
                return new ThreemaImpl();
            case 6:
            default:
                return new TwitterImpl();
        }
    }
}
