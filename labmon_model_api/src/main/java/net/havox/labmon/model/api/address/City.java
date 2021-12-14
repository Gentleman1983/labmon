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

package net.havox.labmon.model.api.address;

import java.io.Serializable;

import net.havox.labmon.model.api.ChangeAware;

/**
 * This interface represents a city.
 *
 * @author Christian Otto
 */
public interface City extends ChangeAware, Serializable {

    /**
     * Gets the ZIP code.
     *
     * @return the ZIP code
     */
    String getZipCode();

    /**
     * Sets the ZIP code.
     *
     * @param zipCode the ZIP code
     */
    void setZipCode(String zipCode);

    /**
     * Gets the city name.
     *
     * @return the city name
     */
    String getName();

    /**
     * Sets the city name.
     *
     * @param name the city name
     */
    void setName(String name);

    /**
     * Gets the country.
     *
     * @return the county.
     */
    Country getCountry();

    /**
     * Sets the country.
     *
     * @param country the country
     */
    void setCountry(Country country);
}
