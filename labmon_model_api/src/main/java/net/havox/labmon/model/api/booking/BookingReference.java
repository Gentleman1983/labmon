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

package net.havox.labmon.model.api.booking;

import net.havox.labmon.model.utils.validation.booking.BookingReferenceValidator;

import java.net.URL;

/**
 * This interface represents a booking reference.
 *
 * @author Christian Otto
 */
public interface BookingReference {
    /**
     * Returns the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * Returns the booking reference regex to identify the references.
     *
     * @return the regex
     */
    String getBookingReferenceRegex();

    /**
     * Sets the booking reference regex to identify the references.
     *
     * @param regex the regex
     */
    void setBookingReferenceRegex(String regex);

    /**
     * Returns the booking reference base URL.
     *
     * @return the base URL
     */
    URL getBookingReferenceBaseUrl();

    /**
     * Sets the booking reference base URL.
     *
     * @param url the base URL
     */
    void setBookingReferenceBaseUrl(URL url);

    /**
     * Returns the booking reference sub URL. It is used to address the correct ticket to the reference.
     *
     * @return the sub URL string
     */
    String getBookingReferenceSubUrl();

    /**
     * Sets the booking reference sub URL. It is used to address the correct ticket to the reference.
     *
     * @param subUrl the sub URL string
     */
    void setBookingReferenceSubUrl(String subUrl);

    /**
     * Returns a calculated URL link to the given ticket reference.
     *
     * @param reference the reference
     * @return the corresponding URL
     */
    URL getBookingReferenceUrlForReference(String reference);

    /**
     * Returns the entity validator.
     *
     * @return the validator
     */
    BookingReferenceValidator getValidator();
}
