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

import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.employment.Project;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.utils.validation.employment.BasicProjectValidator;
import net.havox.labmon.model.utils.validation.employment.ProjectValidator;

import java.time.LocalDate;

/**
 * Basic implementation of {@link Project} interface.
 *
 * @author Christian Otto
 */
public class BasicProject extends AbstractChangeAwareAndIdentifiableClass implements Project {
    private String name;
    private Employment employment;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Employment getEmployment() {
        return employment;
    }

    @Override
    public void setEmployment(Employment employment) {
        this.employment = employment;
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
    public ProjectValidator getValidator() {
        return new BasicProjectValidator();
    }
}
