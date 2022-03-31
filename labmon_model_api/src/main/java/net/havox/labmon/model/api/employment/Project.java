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

package net.havox.labmon.model.api.employment;

import net.havox.labmon.model.api.ChangeAware;
import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.utils.validation.employment.ProjectValidator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This interface represents a project.
 *
 * @author Christian Otto
 */
public interface Project extends ChangeAware, Serializable {
    /**
     * Returns the project name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the project name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * Returns the corresponding employment.
     *
     * @return the employment
     */
    Employment getEmployment();

    /**
     * Sets the corresponding employment.
     *
     * @param employment the employment
     */
    void setEmployment(Employment employment);

    /**
     * Returns the project start date.
     *
     * @return the start date
     */
    LocalDate getStartDate();

    /**
     * Sets the project start date.
     *
     * @param startDate the start date
     */
    void setStartDate(LocalDate startDate);

    /**
     * Returns the project end date.
     *
     * @return the end date
     */
    LocalDate getEndDate();

    /**
     * Sets the project end date.
     *
     * @param endDate the end date
     */
    void setEndDate(LocalDate endDate);

    /**
     * Returns the booking types.
     *
     * @return the booking types
     */
    Set<BookingType> getBookingTypes();

    /**
     * Adds a booking type.
     *
     * @param type the booking type
     * @return true if everything works all right
     */
    default boolean addBookingType(BookingType type) {
        return addBookingTypes(type);
    }

    /**
     * Adds several booking types.
     *
     * @param types the booking types
     * @return true if everything works all right
     */
    default boolean addBookingTypes(BookingType... types) {
        return addBookingTypes(List.of(types));
    }

    /**
     * Adds several booking types.
     *
     * @param types the booking types
     * @return true if everything works all right
     */
    boolean addBookingTypes(Collection<BookingType> types);

    /**
     * Removes a booking type.
     *
     * @param type the booking type
     * @return true if everything works all right
     */
    default boolean removeBookingType(BookingType type) {
        return removeBookingTypes(type);
    }

    /**
     * Removes several booking types.
     *
     * @param types the booking types
     * @return true if everything works all right
     */
    default boolean removeBookingTypes(BookingType... types) {
        return removeBookingTypes(List.of(types));
    }

    /**
     * Removes several booking types.
     *
     * @param types the booking types
     * @return true if everything works all right
     */
    boolean removeBookingTypes(Collection<BookingType> types);

    /**
     * Returns the entity validator.
     *
     * @return the validator
     */
    ProjectValidator getValidator();
}
