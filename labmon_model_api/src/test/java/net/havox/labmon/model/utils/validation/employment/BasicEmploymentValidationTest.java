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

package net.havox.labmon.model.utils.validation.employment;

import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.basic.employment.BasicEmployer;
import net.havox.labmon.model.basic.employment.BasicEmployment;
import net.havox.labmon.model.basic.user.BasicCredentials;
import net.havox.labmon.model.basic.user.BasicUser;

/**
 * Basic implementation of {@link AbstractEmploymentValidationTest}.
 *
 * @author Christian Otto
 */
public class BasicEmploymentValidationTest extends AbstractEmploymentValidationTest {
    @Override
    public Employment getEmployment() {
        return new BasicEmployment();
    }

    @Override
    public Employer getEmployer() throws Exception {
        return new BasicEmployer();
    }

    @Override
    public User getUser() throws Exception {
        return new BasicUser();
    }

    @Override
    public Credentials getCredentials() throws Exception {
        return new BasicCredentials();
    }

    @Override
    public EmploymentValidator getEmploymentValidator() {
        return new BasicEmploymentValidator();
    }
}
