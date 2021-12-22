/*
 * Copyright (C) 2021 [haVox] Design
 * Created by The_G
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

package net.havox.labmon.model.utils.validation.booking;

import net.havox.labmon.model.api.booking.BookingType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link BookingType} entities.
 * <p>
 * Specifications for a given user entity:
 * - The name has not to be empty nor blank
 * - The multiplier has to be set
 *
 * @author Christian Otto
 */
public interface BookingTypeValidator {
    /**
     * Validates a {@link BookingType} entity.
     *
     * @param validationTarget the contact option entity
     * @return the validation errors
     */
    default List<String> validate(BookingType validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected booking type entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (StringUtils.isAllBlank(validationTarget.getName())) {
            validationErrors.add("Expected the booking type name not to be empty nor blank.");
        }

        if (null == validationTarget.getMultiplier()) {
            validationErrors.add("Expected the booking type multiplier to be set.");
        }

        return Collections.unmodifiableList(validationErrors);
    }

    /**
     * Validates a {@link BookingType} entity.
     *
     * @param validationTarget the contact option entity
     * @return true, if no errors occurred on validation
     */
    default boolean isValid(BookingType validationTarget) {
        return validate(validationTarget).isEmpty();
    }
}
