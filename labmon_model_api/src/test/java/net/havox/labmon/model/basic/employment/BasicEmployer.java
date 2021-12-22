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

import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.model.api.employment.Employer;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.utils.validation.employment.BasicEmployerValidator;
import net.havox.labmon.model.utils.validation.employment.EmployerValidator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of {@link Employer} interface.
 *
 * @author Christian Otto
 */
public class BasicEmployer extends AbstractChangeAwareAndIdentifiableClass implements Employer {
    private String name;
    private Set<ContactOption<?>> contactOptions = new HashSet<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<ContactOption<?>> getContactOptions() {
        return Collections.unmodifiableSet(contactOptions);
    }

    @Override
    public boolean addContactOptions(Collection<ContactOption<?>> options) {
        contactOptions.addAll(options);

        return true;
    }

    @Override
    public boolean removeContactOptions(Collection<ContactOption<?>> options) {
        contactOptions.removeAll(options);

        return true;
    }

    @Override
    public EmployerValidator getValidator() {
        return new BasicEmployerValidator();
    }
}
