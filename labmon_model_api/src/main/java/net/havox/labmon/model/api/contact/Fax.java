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

package net.havox.labmon.model.api.contact;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * This interface represents a telefax number.
 *
 * @author Christian Otto
 */
public interface Fax extends ContactOption<Fax> {
    /**
     * Returns the telefax phone number.
     *
     * @return the phone number
     */
    PhoneNumber getPhoneNumber();

    /**
     * Sets the telefax phone number.
     *
     * @param phoneNumber the phone number
     */
    void setPhoneNumber(PhoneNumber phoneNumber);
}
