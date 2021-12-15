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

package net.havox.labmon.model.basic.contact;

import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.model.api.contact.CountryCode;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;

import java.util.Collections;
import java.util.Set;

/**
 * Basic implementations of {@link CountryCode} interface.
 *
 * @author Christian Otto
 */
public class BasicCountryCode extends AbstractChangeAwareAndIdentifiableClass implements CountryCode {
    private Country countryName;
    private Set<Integer> prefixes = Collections.emptySet();

    @Override
    public Country getCountryName() {
        return countryName;
    }

    @Override
    public void setCountryName(Country countryName) {
        this.countryName = countryName;
    }

    @Override
    public Set<Integer> getPrefixes() {
        return prefixes;
    }

    @Override
    public void setPrefixes(Set<Integer> prefixes) {
        this.prefixes = Collections.unmodifiableSet(prefixes);
    }
}
