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

import net.havox.labmon.model.api.employment.Employment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Employment} entities.
 * <p>
 * Specifications for a given user entity:
 * - user hat to be set
 * - user has to be valid
 * - employer has to be set
 * - employer has to be valid
 * - start date has to be set
 * - if end date is set the end date has not to be before the start date
 *
 * @author Christian Otto
 */
public interface EmploymentValidator {
    /**
     * Validates a {@link Employment} and returns a {@link List} of validation errors.
     *
     * @param instanceUnderValidation the instance
     * @return the list of validation errors
     */
    default List<String> validate(Employment instanceUnderValidation) {
        List<String> validationErrors = new ArrayList<>();

        if (null == instanceUnderValidation) {
            return List.of("Expected employment not to be null.");
        }

        if (null == instanceUnderValidation.getUser()) {
            validationErrors.add("Expected user not to be null.");
        } else if (!instanceUnderValidation.getUser().getValidator().isUserValid(instanceUnderValidation.getUser())) {
            validationErrors.add("Expected user not to be valid.");
            validationErrors.addAll(prefixValidationMessages(
                    instanceUnderValidation.getUser().getValidator().validateUser(instanceUnderValidation.getUser()),
                    "User"));
        }

        if (null == instanceUnderValidation.getEmployer()) {
            validationErrors.add("Expected employer not to be null.");
        } else if (!instanceUnderValidation.getEmployer().getValidator().isValid(instanceUnderValidation.getEmployer())) {
            validationErrors.add("Expected employer not to be valid.");
            validationErrors.addAll(prefixValidationMessages(
                    instanceUnderValidation.getEmployer().getValidator().validate(instanceUnderValidation.getEmployer()),
                    "Employer"));
        }

        if (null == instanceUnderValidation.getEmploymentStartDate()) {
            validationErrors.add("Expected start date not to be null.");
        } else if (null != instanceUnderValidation.getEmploymentEndDate() &&
                instanceUnderValidation.getEmploymentStartDate().isAfter(instanceUnderValidation.getEmploymentEndDate())) {
            validationErrors.add("Expected the start date before or equal to the end date.");
        }

        return Collections.unmodifiableList(validationErrors);
    }

    /**
     * Simple validity check for the {@link Employment} entities.
     *
     * @param instance the instance
     * @return is the instance valid
     */
    default boolean isValid(Employment instance) {
        return validate(instance).isEmpty();
    }

    /**
     * Prefixes a list of messages.
     *
     * @param messages the messages
     * @param prefix   the prefix
     * @return the prefixed messages
     */
    private List<String> prefixValidationMessages(List<String> messages, String prefix) {
        return messages.stream().map(message -> prefix + ": " + message).toList();
    }
}
