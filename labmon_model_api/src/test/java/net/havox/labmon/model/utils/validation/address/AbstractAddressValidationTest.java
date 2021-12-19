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
import net.havox.labmon.testutils.random.ModelRandomGenerator;

/**
 * Abstract implementation of {@link AddressValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractAddressValidationTest {
    /**
     * Provides an {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Address getAddress() throws Exception;

    /**
     * Provides an {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides an {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract Country getCountry() throws Exception;

    /**
     * Provides an {@link AddressValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract AddressValidator getAddressValidator() throws Exception;

    /**
     * Provides a valid {@link Address} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Address getValidAddressInstance() throws Exception {
        Address instance = getAddress();

        instance.setStreet(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setHouseNumber(Integer.toString(ModelRandomGenerator.randomInt(9999)));
        instance.setCity(getValidCityInstance());

        return instance;
    }

    /**
     * Provides a valid {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private City getValidCityInstance() throws Exception {
        City instance = getCity();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),ModelRandomGenerator.ALPHABETIC_STRING));
        instance.setZipCode(Integer.toString(ModelRandomGenerator.randomIntInRange(10000,99999)));
        instance.setCountry(getValidCountryInstance());

        return instance;
    }

    /**
     * Provides a valid {@link Country} entity.
     *
     * @return the entity
     * @throws Exception
     */
    private Country getValidCountryInstance() throws Exception {
        Country instance = getCountry();

        instance.setName(ModelRandomGenerator.randomString(ModelRandomGenerator.randomIntInRange(1, 50),ModelRandomGenerator.ALPHABETIC_STRING));

        return instance;
    }
}
