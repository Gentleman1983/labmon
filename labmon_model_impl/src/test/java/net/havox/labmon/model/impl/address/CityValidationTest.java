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

package net.havox.labmon.model.impl.address;

import net.havox.labmon.model.api.address.City;
import net.havox.labmon.model.api.address.Country;
import net.havox.labmon.model.utils.validation.address.AbstractCityValidationTest;
import net.havox.labmon.model.utils.validation.address.CityImplValidator;
import net.havox.labmon.model.utils.validation.address.CityValidator;

/**
 * Validation test for {@link CityImpl}.
 *
 * @author Christian Otto
 */
public class CityValidationTest extends AbstractCityValidationTest { // NOSONAR Validation test is only inherited, so only derived test cases.
    @Override
    public City getCity() throws Exception {
        return new CityImpl();
    }

    @Override
    public Country getCountry() throws Exception {
        return new CountryImpl();
    }

    @Override
    public CityValidator getCityValidator() throws Exception {
        return new CityImplValidator();
    }
}
