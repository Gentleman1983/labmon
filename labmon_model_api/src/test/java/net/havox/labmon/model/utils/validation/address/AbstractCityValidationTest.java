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

import net.havox.labmon.model.api.address.City;

/**
 * Abstract implementation of {@link CityValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractCityValidationTest {
    /**
     * Provides an {@link City} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract City getCity() throws Exception;

    /**
     * Provides an {@link CityValidator} entity.
     *
     * @return the entity
     * @throws Exception
     */
    public abstract CityValidator getCityValidator() throws Exception;
}
