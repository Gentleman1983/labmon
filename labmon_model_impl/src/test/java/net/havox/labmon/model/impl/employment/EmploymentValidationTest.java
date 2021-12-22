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

import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.model.api.employment.Employment;
import net.havox.labmon.model.api.user.Credentials;
import net.havox.labmon.model.api.user.User;
import net.havox.labmon.model.impl.user.CredentialsImpl;
import net.havox.labmon.model.impl.user.UserImpl;
import net.havox.labmon.model.utils.validation.employment.AbstractEmploymentValidationTest;
import net.havox.labmon.model.utils.validation.employment.EmploymentImplValidator;
import net.havox.labmon.model.utils.validation.employment.EmploymentValidator;

/**
 * Validation test for {@link EmploymentImpl}.
 *
 * @author Christian Otto
 */
public class EmploymentValidationTest extends AbstractEmploymentValidationTest { // NOSONAR Validation test is only inherited, so only derived test cases.
    @Override
    public Employment getEmployment() {
        return new EmploymentImpl();
    }

    @Override
    public Employer getEmployer() {
        return new EmployerImpl();
    }

    @Override
    public User getUser() {
        return new UserImpl();
    }

    @Override
    public Credentials getCredentials() {
        return new CredentialsImpl();
    }

    @Override
    public EmploymentValidator getEmploymentValidator() {
        return new EmploymentImplValidator();
    }
}
