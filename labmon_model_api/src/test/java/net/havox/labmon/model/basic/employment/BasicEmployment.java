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

package net.havox.labmon.model.basic.employment;

import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.utils.validation.employment.BasicEmploymentValidator;
import net.havox.labmon.model.utils.validation.employment.EmploymentValidator;

import java.time.LocalDate;

/**
 * Basic implementation of {@link Employment} interface.
 *
 * @author Christian Otto
 */
public class BasicEmployment extends AbstractChangeAwareAndIdentifiableClass implements Employment {
    private User user;
    private Employer employer;
    private String description;
    private LocalDate start;
    private LocalDate end;

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
    public LocalDate getEmploymentStartDate() {
        return start;
    }

    @Override
    public void setEmploymentStartDate(LocalDate startDate) {
        start = startDate;
    }

    @Override
    public LocalDate getEmploymentEndDate() {
        return end;
    }

    @Override
    public void setEmploymentEndDate(LocalDate endDate) {
        end = endDate;
    }

    @Override
    public EmploymentValidator getValidator() {
        return new BasicEmploymentValidator();
    }
}
