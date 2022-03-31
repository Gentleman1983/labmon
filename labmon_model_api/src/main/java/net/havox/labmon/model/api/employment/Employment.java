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

package net.havox.labmon.model.api.employment;

import net.havox.labmon.model.api.ChangeAware;
import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.utils.validation.employment.EmploymentValidator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This interface represents an employment.
 *
 * @author Christian Otto
 */
public interface Employment extends ChangeAware, Serializable {
    /**
     * Returns the user.
     *
     * @return the user
     */
    User getUser();

    /**
     * Sets the user.
     *
     * @param user the user
     */
    void setUser(User user);

    /**
     * Returns the employer.
     *
     * @return the employer
     */
    Employer getEmployer();

    /**
     * Sets the employer.
     *
     * @param employer the employer
     */
    void setEmployer(Employer employer);

    /**
     * Returns the description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Sets the description.
     *
     * @param description the description
     */
    void setDescription(String description);

    /**
     * Returns the employment start date.
     *
     * @return the date
     */
    LocalDate getStartDate();

    /**
     * Sets the employment start date.
     *
     * @param startDate the date
     */
    void setStartDate(LocalDate startDate);

    /**
     * Returns the employment end date.
     *
     * @return the date
     */
    LocalDate getEndDate();

    /**
     * Sets the employment end date.
     *
     * @param endDate the date
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
    EmploymentValidator getValidator();
}
