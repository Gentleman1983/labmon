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

package net.havox.labmon.model.utils.validation.employment;

import net.havox.labmon.model.api.employment.Employer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A validator for the {@link Employer} entities.
 * <p>
 * Specifications for a given user entity:
 * - name has not to be empty
 *
 * @author Christian Otto
 */
public interface EmployerValidator {
    /**
     * Validates a {@link Employer} and returns a {@link List} of validation errors.
     *
     * @param instanceUnderValidation the instance
     * @return the list of validation errors
     */
    default List<String> validate(Employer instanceUnderValidation) {
        List<String> validationErrors = new ArrayList<>();

        if (null == instanceUnderValidation) {
            return List.of("Expected employer not to be null.");
        }

        if (null == instanceUnderValidation.getName()) {
            return List.of("Expected employer name not to be null.");
        }

        if (StringUtils.isAllBlank(instanceUnderValidation.getName())) {
            validationErrors.add("Expected employer name not to be blank or empty.");
        }

        return validationErrors;
    }

    /**
     * Simple validity check for the {@link Employer} entities.
     *
     * @param instance the instance
     * @return is the instance valid
     */
    default boolean isValid(Employer instance) {
        return validate(instance).isEmpty();
    }
}
