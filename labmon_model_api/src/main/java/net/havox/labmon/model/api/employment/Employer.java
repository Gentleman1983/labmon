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

import net.havox.labmon.model.api.ChangeAware;
import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.model.utils.validation.employment.EmployerValidator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * This interface represents an employer.
 *
 * @author Christian Otto
 */
public interface Employer extends ChangeAware, Serializable {
    /**
     * Returns the employer name.
     *
     * @return the employer name
     */
    String getName();

    /**
     * Sets the employer name.
     *
     * @param name the employer name
     */
    void setName(String name);

    /**
     * Returns the employer's contact options.
     *
     * @return the contact options
     */
    Set<ContactOption<?>> getContactOptions();

    /**
     * Adds a {@link ContactOption} to the contact options.
     *
     * @param option the option
     * @return true, if everything works well
     */
    default boolean addContactOption(ContactOption<?> option) {
        return addContactOptions(option);
    }

    /**
     * Adds {@link ContactOption}s to the contact options.
     *
     * @param options the options
     * @return true, if everything works well
     */
    default boolean addContactOptions(ContactOption<?>... options) {
        return addContactOptions(Arrays.asList(options));
    }

    /**
     * Adds {@link ContactOption}s to the contact options.
     *
     * @param options the options
     * @return true, if everything works well
     */
    boolean addContactOptions(Collection<ContactOption<?>> options);

    /**
     * Removes a {@link ContactOption} to the contact options.
     *
     * @param option the option
     * @return true, if everything works well
     */
    default boolean removeContactOption(ContactOption<?> option) {
        return removeContactOptions(option);
    }

    /**
     * Removes {@link ContactOption}s to the contact options.
     *
     * @param options the options
     * @return true, if everything works well
     */
    default boolean removeContactOptions(ContactOption<?>... options) {
        return removeContactOptions(Arrays.asList(options));
    }

    /**
     * Removes {@link ContactOption}s to the contact options.
     *
     * @param options the options
     * @return true, if everything works well
     */
    boolean removeContactOptions(Collection<ContactOption<?>> options);

    /**
     * Returns the entity validator.
     *
     * @return the validator
     */
    EmployerValidator getValidator();
}
