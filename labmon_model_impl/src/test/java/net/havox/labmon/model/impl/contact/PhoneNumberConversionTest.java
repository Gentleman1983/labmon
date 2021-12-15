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

import net.havox.labmon.model.api.contact.PhoneNumber;
import net.havox.labmon.model.utils.conversion.contact.AbstractPhoneNumberConversionTest;
import net.havox.labmon.model.utils.conversion.contact.PhoneNumberConverter;
import net.havox.labmon.model.utils.conversion.contact.PhoneNumberImplConverter;

/**
 * Conversion test for {@link PhoneNumberImpl}.
 *
 * @author Christian Otto
 */
public class PhoneNumberConversionTest extends AbstractPhoneNumberConversionTest { // NOSONAR Conversion test is only inherited, so only derived test cases.
    @Override
    public PhoneNumber getPhoneNumber() throws Exception {
        return new PhoneNumberImpl();
    }

    @Override
    public PhoneNumberConverter getPhoneNumberConverter() throws Exception {
        return new PhoneNumberImplConverter();
    }
}
