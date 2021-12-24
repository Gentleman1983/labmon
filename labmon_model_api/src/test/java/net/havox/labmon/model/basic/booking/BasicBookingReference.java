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

package net.havox.labmon.model.basic.booking;

import net.havox.labmon.model.api.booking.BookingReference;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.utils.validation.booking.BasicBookingReferenceValidator;
import net.havox.labmon.model.utils.validation.booking.BookingReferenceValidator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.net.URL;

/**
 * Basic implementation of {@link BookingReference}.
 *
 * @author Christian Otto
 */
public class BasicBookingReference extends AbstractChangeAwareAndIdentifiableClass implements BookingReference {
    private String name;
    private String bookingReferenceRegex;
    private URL bookingReferenceBaseURL;
    private String bookingReferenceSubURL;

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
        return bookingReferenceBaseURL;
    }

    @Override
    public void setBookingReferenceBaseUrl(URL url) {
        bookingReferenceBaseURL = url;
    }

    @Override
    public String getBookingReferenceSubUrl() {
        return bookingReferenceSubURL;
    }

    @Override
    public void setBookingReferenceSubUrl(String subUrl) {
        bookingReferenceSubURL = subUrl;
    }

    @Override
    public URL getBookingReferenceUrlForReference(String reference) {
        return null;
    }

    @Override
    public BookingReferenceValidator getValidator() {
        return new BasicBookingReferenceValidator();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

        builder.append("id", getId());
        builder.append("name", getName());
        builder.append("bookingReferenceRegex", getBookingReferenceRegex());
        builder.append("bookingReferenceBaseURL", getBookingReferenceBaseUrl());
        builder.append("bookingReferenceSubURL", getBookingReferenceSubUrl());
        builder.append("version", getVersion());

        return builder.build();
    }
}
