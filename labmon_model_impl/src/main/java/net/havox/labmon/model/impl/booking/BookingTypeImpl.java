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

import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.booking.BookingTypeImplValidator;
import net.havox.labmon.model.utils.validation.booking.BookingTypeValidator;

import java.math.BigDecimal;

/**
 * The functional representation of a {@link BookingType}.
 *
 * @author Christian Otto
 */
public class BookingTypeImpl extends AbstractChangeAwareClass<BookingTypeImpl> implements BookingType {
    /**
     * The booking type name.
     */
    private String name;

    /**
     * The multiplier.
     */
    private BigDecimal multiplier;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getMultiplier() {
        return multiplier;
    }

    @Override
    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public BookingTypeValidator getValidator() {
        return new BookingTypeImplValidator();
    }
}
