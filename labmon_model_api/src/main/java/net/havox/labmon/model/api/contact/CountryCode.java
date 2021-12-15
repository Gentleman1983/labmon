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

package net.havox.labmon.model.api.contact;

import net.havox.labmon.model.api.address.Country;

import java.util.*;

public interface CountryCode {
    public static final String COUNTRY_CODE_PREFIX = "+";

    Country getCountryName();

    void setCountryName(Country countryName);

    Set<Integer> getPrefixes();

    void setPrefixes(Set<Integer> prefixes);

    default void setPrefixes(int... prefixes) {
        Set<Integer> data = new HashSet<>();

        for (int prefix : prefixes) {
            data.add(prefix);
        }

        setPrefixes(data);
    }

    default Set<String> getPhonePrefixes() {
        Set<String> prefixesForCountry = new HashSet<>();

        for (int prefix : getPrefixes()) {
            prefixesForCountry.add(COUNTRY_CODE_PREFIX + prefix);
        }

        return prefixesForCountry;
    }
}
