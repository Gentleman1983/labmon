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

package net.havox.labmon.model.impl.employment;

import net.havox.labmon.model.api.booking.BookingType;
import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.employment.EmploymentImplValidator;
import net.havox.labmon.model.utils.validation.employment.EmploymentValidator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * This represents the functional entity of an {@link Employment}.
 *
 * @author Christian Otto
 */
public class EmploymentImpl extends AbstractChangeAwareClass<EmploymentImpl> implements Employment {
    /**
     * The booking types.
     */
    private final Set<BookingType> bookingTypes = new ConcurrentSkipListSet<>();
    /**
     * The user having the employment.
     */
    private User user;
    /**
     * The employer.
     */
    private Employer employer;
    /**
     * The employment's description.
     */
    private String description;
    /**
     * The employment's start date.
     */
    private LocalDate startDate;
    /**
     * The employment's end date.
     */
    private LocalDate endDate;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Employer getEmployer() {
        return employer;
    }

    @Override
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public Set<BookingType> getBookingTypes() {
        return Collections.unmodifiableSet(bookingTypes);
    }

    @Override
    public boolean addBookingTypes(Collection<BookingType> types) {
        bookingTypes.addAll(types);

        return true;
    }

    @Override
    public boolean removeBookingTypes(Collection<BookingType> types) {
        bookingTypes.removeAll(types);

        return true;
    }

    @Override
    public EmploymentValidator getValidator() {
        return new EmploymentImplValidator();
    }
}
