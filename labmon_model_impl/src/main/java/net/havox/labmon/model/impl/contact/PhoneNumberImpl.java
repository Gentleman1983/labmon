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

import net.havox.labmon.model.api.contact.CountryCode;
import net.havox.labmon.model.api.contact.PhoneNumber;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;

public class PhoneNumberImpl extends AbstractChangeAwareClass<PhoneNumberImpl> implements PhoneNumber {
    @Override
    public CountryCode getCountryCode() {
        return null;
    }

    @Override
    public void setCountryCode(CountryCode countryCode) {

    }

    @Override
    public Integer getPhoneNumber() {
        return 0;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public void setPhoneNumber(Integer phoneNumber) {

    }
}
