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

package net.havox.labmon.model.utils.validation.booking;

import net.havox.labmon.model.api.booking.BookingReference;

/**
 * Abstract implementation of {@link BookingReferenceValidator} test.
 *
 * @author Christian Otto
 */
public abstract class AbstractBookingReferenceValidationTest {
    /**
     * Provides an {@link BookingReference} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract BookingReference getBookingReference() throws Exception;

    /**
     * Provides an {@link BookingReferenceValidator} entity.
     *
     * @return the entity
     * @throws Exception in case of an exception
     */
    public abstract BookingReferenceValidator getBookingReferenceValidator() throws Exception;
}
