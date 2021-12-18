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
import net.havox.labmon.model.utils.validation.address.AddressValidator;

/**
 * This interface represents an address.
 *
 * @author Christian Otto
 */
public interface Address extends ChangeAware, Serializable {

    /**
     * Gets the street name.
     *
     * @return the street name
     */
    String getStreet();

    /**
     * Sets the street name.
     *
     * @param street the street name
     */
    void setStreet(String street);

    /**
     * Gets the house number.
     *
     * @return the house number
     */
    String getHouseNumber();

    /**
     * Sets the house number.
     *
     * @param houseNumber the house number
     */
    void setHouseNumber(String houseNumber);

    /**
     * Gets the city.
     *
     * @return the city
     */
    City getCity();

    /**
     * Sets the city.
     *
     * @param city the cityS
     */
    void setCity(City city);

    /**
     * Returns am {@link AddressValidator} to validator {@link Address} entities.
     *
     * @return the validator
     */
    AddressValidator getValidator();
}
