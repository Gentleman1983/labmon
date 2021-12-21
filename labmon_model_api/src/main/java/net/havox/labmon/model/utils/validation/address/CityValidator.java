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
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link City} entities.
 * <p>
 * Specifications for a given user entity:
 * - the name neither has to be empty nor blank
 * - the ZIP code neither has to be empty nor blank
 * - the country has to be valid
 *
 * @author Christian Otto
 */
public interface CityValidator {
    /**
     * Validates a {@link City} entity.
     *
     * @param validationTarget the contact option entity
     * @return the validation errors
     */
    default List<String> validate(City validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected city entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (StringUtils.isAllBlank(validationTarget.getName())) {
            validationErrors.add("The city name has not to be empty nor blank.");
        }

        if (StringUtils.isAllBlank(validationTarget.getZipCode())) {
            validationErrors.add("The ZIP code has not to be empty nor blank.");
        }

        if (null == validationTarget.getCountry()) {
            validationErrors.add("The country has not to be null.");
        } else {
            CountryValidator countryValidator = validationTarget.getCountry().getValidator();
            if (!countryValidator.isValid(validationTarget.getCountry())) {
                validationErrors.addAll(countryValidator.validate(validationTarget.getCountry()));
            }
        }

        return Collections.unmodifiableList(validationErrors);
    }

    /**
     * Validates a {@link City} entity.
     *
     * @param validationTarget the contact option entity
     * @return true, if no errors occured on validation
     */
    default boolean isValid(City validationTarget) {
        return validate(validationTarget).isEmpty();
    }
}
