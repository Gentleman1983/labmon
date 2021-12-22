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

import net.havox.labmon.model.api.employment.Project;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Project} entities.
 * <p>
 * Specifications for a given user entity:
 * - name hat to be set
 * - name has not to be blank or empty
 * - employment has to be set
 * - employment has to be valid
 * - start date has to be set
 * - if end date is set the end date has not to be before the start date
 * - start date has to be after employment start date
 * - if employment end date is set the start date has to be before employment end date
 * - if employment end date is set the end date has not to be after employment end date
 *
 * @author Christian Otto
 */
public interface ProjectValidator {
    /**
     * Validates a {@link Project} and returns a {@link List} of validation errors.
     *
     * @param instanceUnderValidation the instance
     * @return the list of validation errors
     */
    default List<String> validate(Project instanceUnderValidation) {
        List<String> validationErrors = new ArrayList<>();

        if (null == instanceUnderValidation) {
            return List.of("Expected employment not to be null.");
        }

        validateNameData(instanceUnderValidation, validationErrors);
        validateEmploymentData(instanceUnderValidation, validationErrors);
        validateStartDateData(instanceUnderValidation, validationErrors);

        return Collections.unmodifiableList(validationErrors);
    }

    /**
     * Simple validity check for the {@link Project} entities.
     *
     * @param instance the instance
     * @return is the instance valid
     */
    default boolean isValid(Project instance) {
        return validate(instance).isEmpty();
    }

    /**
     * Validates the data corresponding to {@link Project#getName()}.
     *
     * @param instanceUnderValidation the {@link Project}
     * @param validationErrors        the {@link List} of validation errors
     */
    private void validateNameData(Project instanceUnderValidation, List<String> validationErrors) {
        if (null == instanceUnderValidation.getName()) {
            validationErrors.add("Expected name not to be null.");
        } else if (StringUtils.isAllBlank(instanceUnderValidation.getName())) {
            validationErrors.add("Expected name not to be blank or empty.");
        }
    }

    /**
     * Validates the data corresponding to {@link Project#getEmployment()}.
     *
     * @param instanceUnderValidation the {@link Project}
     * @param validationErrors        the {@link List} of validation errors
     */
    private void validateEmploymentData(Project instanceUnderValidation, List<String> validationErrors) {
        if (null == instanceUnderValidation.getEmployment()) {
            validationErrors.add("Expected employment not to be null.");
        } else {
            if (!instanceUnderValidation.getEmployment().getValidator().isValid(instanceUnderValidation.getEmployment())) {
                validationErrors.add("Expected employment not to be valid.");
                validationErrors.addAll(prefixValidationMessages(
                        instanceUnderValidation.getEmployment().getValidator().validate(instanceUnderValidation.getEmployment()),
                        "Employment"));
            }

            if (null != instanceUnderValidation.getEmployment().getEmploymentEndDate()) {
                if (null != instanceUnderValidation.getStartDate() &&
                        instanceUnderValidation.getStartDate().isAfter(instanceUnderValidation.getEmployment().getEmploymentEndDate())) {
                    validationErrors.add("Expected the start date not to be after the employment end date.");
                }

                if (null != instanceUnderValidation.getEndDate() &&
                        instanceUnderValidation.getEndDate().isAfter(instanceUnderValidation.getEmployment().getEmploymentEndDate())) {
                    validationErrors.add("Expected the end date not to be after the employment end date.");
                }
            }
        }
    }

    /**
     * Validates the data corresponding to {@link Project#getStartDate()}.
     *
     * @param instanceUnderValidation the {@link Project}
     * @param validationErrors        the {@link List} of validation errors
     */
    private void validateStartDateData(Project instanceUnderValidation, List<String> validationErrors) {
        if (null == instanceUnderValidation.getStartDate()) {
            validationErrors.add("Expected start date not to be null.");
        } else {
            if (null != instanceUnderValidation.getEndDate() &&
                    instanceUnderValidation.getStartDate().isAfter(instanceUnderValidation.getEndDate())) {
                validationErrors.add("Expected the start date before or equal to the end date.");
            }

            if (null != instanceUnderValidation.getEmployment() &&
                    null != instanceUnderValidation.getEmployment().getEmploymentStartDate() &&
                    instanceUnderValidation.getStartDate().isBefore(instanceUnderValidation.getEmployment().getEmploymentStartDate())) {
                validationErrors.add("The start date has not to be before the employment start date.");
            }
        }
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
