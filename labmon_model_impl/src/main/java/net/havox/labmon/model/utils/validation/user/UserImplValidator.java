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

package net.havox.labmon.model.utils.validation.user;

import net.havox.labmon.model.api.user.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Functional implementation of {@link UserValidator}.
 *
 * @author Christian Otto
 */
public class UserImplValidator implements UserValidator {
    @Override
    public List<String> validateUser(User instanceUnderValidation) {
        List<String> validationErrors = new ArrayList<>();

        if (!checkIsNullInstance(instanceUnderValidation, validationErrors)) {
            checkForValidFirstName(instanceUnderValidation, validationErrors);
            checkForValidLastName(instanceUnderValidation, validationErrors);
        }

        return validationErrors;
    }

    /**
     * Checks if the instance is {@code null}.
     *
     * @param instance         the instance
     * @param validationErrors the {@link List} of validation errors
     * @return is this a null instance?
     */
    private boolean checkIsNullInstance(User instance, List<String> validationErrors) {
        boolean result = false;

        if (null == instance) {
            validationErrors.add("The user entity has to be not null");
            result = true;
        }

        return result;
    }

    /**
     * Checks if the first name of the {@link User} is valid.
     *
     * @param instance         the instance
     * @param validationErrors the {@link List} of validation errors
     */
    private void checkForValidFirstName(User instance, List<String> validationErrors) {
        String name = instance.getFirstName();
        String propertyName = "firstName";

        checkStringIsNull(name, propertyName, validationErrors);
        checkStringIsEmpty(name, propertyName, validationErrors);
    }

    /**
     * Checks if the last name of the {@link User} is valid.
     *
     * @param instance         the instance
     * @param validationErrors the {@link List} of validation errors
     */
    private void checkForValidLastName(User instance, List<String> validationErrors) {
        String name = instance.getLastName();
        String propertyName = "lastName";

        checkStringIsNull(name, propertyName, validationErrors);
        checkStringIsEmpty(name, propertyName, validationErrors);
    }

    /**
     * Checks if a given property is {@code null}.
     *
     * @param propertyValue    the value of the property
     * @param propertyName     the name of the property under test
     * @param validationErrors the {@link List} of validation errors
     */
    private void checkStringIsNull(String propertyValue, String propertyName, List<String> validationErrors) {
        if (null == propertyValue) {
            validationErrors.add("The property '" + propertyName + "' has to be not NULL.");
        }
    }

    /**
     * Checks if a given property is blank.
     *
     * @param propertyValue    the value of the property
     * @param propertyName     the name of the property under test
     * @param validationErrors the {@link List} of validation errors
     */
    private void checkStringIsEmpty(String propertyValue, String propertyName, List<String> validationErrors) {
        if (StringUtils.isBlank(propertyValue)) {
            validationErrors.add("The property '" + propertyName + "' has not to be empty or consist of only whitespaces.");
        }
    }
}
