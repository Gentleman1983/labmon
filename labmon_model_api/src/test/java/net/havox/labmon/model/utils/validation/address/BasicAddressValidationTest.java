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

package net.havox.labmon.model.utils.validation.address;

import net.havox.labmon.model.api.address.Address;
import net.havox.labmon.model.api.address.City;
import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.model.basic.address.BasicAddress;
import net.havox.labmon.model.basic.address.BasicCity;
import net.havox.labmon.model.basic.address.BasicCountry;

/**
 * Basic implementation of {@link AbstractAddressValidationTest}.
 *
 * @author Christian Otto
 */
public class BasicAddressValidationTest extends AbstractAddressValidationTest {
    @Override
    public Address getAddress() throws Exception {
        return new BasicAddress();
    }

    @Override
    public City getCity() throws Exception {
        return new BasicCity();
    }

    @Override
    public Country getCountry() throws Exception {
        return new BasicCountry();
    }

    @Override
    public AddressValidator getAddressValidator() {
        return new BasicAddressValidator();
    }

    @Override
    public CityValidator getCityValidator() {
        return new BasicCityValidator();
    }
}
