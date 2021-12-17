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

import net.havox.labmon.model.api.address.Address;

/**
 * This interface represents a mail address
 *
 * @author Christian Otto
 */
public interface MailAddress extends ContactOption {
    /**
     * Returns the receiver.
     *
     * @return the receiver
     */
    String getReceiver();

    /**
     * Sets the receiver.
     *
     * @param receiver the receiver
     */
    void setReceiver(String receiver);

    /**
     * Returns the address of the receiver.
     *
     * @return the address
     */
    Address getAddress();

    /**
     * Sets the address of the receiver.
     *
     * @param address the address
     */
    void setAddress(Address address);
}
