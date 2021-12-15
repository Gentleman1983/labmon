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

import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.model.api.contact.CountryCode;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;

import java.util.Set;

public class CountryCodeImpl extends AbstractChangeAwareClass<CountryCodeImpl> implements CountryCode {
    @Override
    public Country getCountryName() {
        return null;
    }

    @Override
    public void setCountryName(Country countryName) {

    }

    @Override
    public Set<Integer> getPrefixes() {
        return null;
    }

    @Override
    public void setPrefixes(Set<Integer> prefixes) {

    }
}
