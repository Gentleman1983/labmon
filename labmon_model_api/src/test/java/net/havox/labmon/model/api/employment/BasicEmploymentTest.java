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

import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.basic.booking.BasicBookingType;
import net.havox.labmon.model.basic.employment.BasicEmployer;
import net.havox.labmon.model.basic.employment.BasicEmployment;
import net.havox.labmon.model.basic.user.BasicUser;

/**
 * Basic implementation of {@link AbstractEmploymentTest}.
 *
 * @author Christian Otto
 */
public class BasicEmploymentTest extends AbstractEmploymentTest {
    @Override
    public Employment getEmployment() throws Exception {
        return new BasicEmployment();
    }

    @Override
    public User getUser() throws Exception {
        return new BasicUser();
    }

    @Override
    public Employer getEmployer() throws Exception {
        return new BasicEmployer();
    }

    @Override
    public BookingType getBookingType() throws Exception {
        return new BasicBookingType();
    }
}
