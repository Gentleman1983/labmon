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
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Address} entities.
 * <p>
 * Specifications for a given user entity:
 * - the street neither has to be empty nor blank
 * - the city hast to be valid
 *
 * @author Christian Otto
 */
public interface AddressValidator {
    /**
     * Validates a {@link Address} entity.
     *
     * @param validationTarget the contact option entity
     * @return the validation errors
     */
    default List<String> validate(Address validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected address entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (StringUtils.isAllBlank(validationTarget.getStreet())) {
            validationErrors.add("Expected the street not to be empty nor blank.");
        }

        CityValidator cityValidator = validationTarget.getCity().getValidator();
        if (!cityValidator.isValid(validationTarget.getCity())) {
            validationErrors.addAll(cityValidator.validate(validationTarget.getCity()));
        }

        return Collections.unmodifiableList(validationErrors);
    }

    /**
     * Validates a {@link Address} entity.
     *
     * @param validationTarget the contact option entity
     * @return true, if no errors occured on validation
     */
    default boolean isValid(Address validationTarget) {
        return validate(validationTarget).isEmpty();
    }
}
