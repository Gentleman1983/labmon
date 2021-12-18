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

package net.havox.labmon.model.basic.contact;

import net.havox.labmon.model.api.contact.EMailAddress;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import net.havox.labmon.model.utils.validation.contact.BasicEMailAddressValidator;
import net.havox.labmon.model.utils.validation.contact.EMailAddressValidator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Basic implementations of {@link EMailAddress} interface.
 *
 * @author Christian Otto
 */
public class BasicEMailAddress extends AbstractChangeAwareAndIdentifiableClass implements EMailAddress {
    private String eMailAddress;

    @Override
    public EMailAddressValidator getContactOptionValidator() {
        return new BasicEMailAddressValidator();
    }

    @Override
    public boolean isValid() {
        return getContactOptionValidator().isValid(this);
    }

    @Override
    public String getEMailAddress() {
        return eMailAddress;
    }

    @Override
    public void setEMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

        builder.append("id", getId());
        builder.append("eMailAddress", getEMailAddress());
        builder.append("version", getVersion());
        builder.append("isValid", isValid());

        return builder.build();
    }
}
