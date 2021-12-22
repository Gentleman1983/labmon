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

import net.havox.labmon.model.api.ChangeAware;
import net.havox.labmon.model.utils.validation.booking.BookingTypeValidator;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This interface represents a booking type.
 *
 * @author Christian Otto
 */
public interface BookingType extends ChangeAware, Serializable {
    /**
     * Returns the booking type name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the booking type name.
     *
     * @param name the name
     */
    void setName(String name);

    /**
     * Returns the multiplier.
     *
     * @return the multiplier
     */
    BigDecimal getMultiplier();

    /**
     * Sets the multiplier.
     *
     * @param multiplier the multiplier
     */
    void setMultiplier(BigDecimal multiplier);

    /**
     * Returns the entity validator.
     *
     * @return the validator
     */
    BookingTypeValidator getValidator();
}
