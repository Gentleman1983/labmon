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

import java.util.List;

/**
 * A validator for the {@link User} entities.
 * <p>
 * Specifications for a given user entity:
 * - firstName has not to be empty
 * - lastName has not to be empty
 * - a credential must be set
 *
 * @author Christian Otto
 */
public interface UserValidator {
    /**
     * Validates a {@link User} and returns a {@link List} of validation errors.
     *
     * @param instanceUnderValidation the instance
     * @return the list of validation errors
     */
    List<String> validateUser(User instanceUnderValidation);

    /**
     * Simple validity check for the {@link User} entities.
     *
     * @param instance the instance
     * @return is the instance valid
     */
    default boolean isUserValid(User instance) {
        return validateUser(instance).isEmpty();
    }
}
