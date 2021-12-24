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

package net.havox.labmon.model.impl.booking;

import net.havox.labmon.model.api.booking.BookingReference;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.booking.BookingReferenceImplValidator;
import net.havox.labmon.model.utils.validation.booking.BookingReferenceValidator;

import java.net.URL;

/**
 * The functional representation of a {@link net.havox.labmon.model.api.booking.BookingReference}.
 *
 * @author Christian Otto
 */
public class BookingReferenceImpl extends AbstractChangeAwareClass<BookingReferenceImpl> implements BookingReference {
    /**
     * The booking reference name.
     */
    private String name;

    /**
     * The booking reference regex.
     */
    private String bookingReferenceRegex;

    /**
     * The booking reference base URL.
     */
    private URL bookingReferenceBaseUrl;

    /**
     * The booking reference sub URL.
     */
    private String bookingReferenceSubUrl;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBookingReferenceRegex() {
        return bookingReferenceRegex;
    }

    @Override
    public void setBookingReferenceRegex(String regex) {
        bookingReferenceRegex = regex;
    }

    @Override
    public URL getBookingReferenceBaseUrl() {
        return bookingReferenceBaseUrl;
    }

    @Override
    public void setBookingReferenceBaseUrl(URL url) {
        bookingReferenceBaseUrl = url;
    }

    @Override
    public String getBookingReferenceSubUrl() {
        return bookingReferenceSubUrl;
    }

    @Override
    public void setBookingReferenceSubUrl(String subUrl) {
        bookingReferenceSubUrl = subUrl;
    }

    @Override
    public URL getBookingReferenceUrlForReference(String reference) {
        return null;
    }

    @Override
    public BookingReferenceValidator getValidator() {
        return new BookingReferenceImplValidator();
    }
}
