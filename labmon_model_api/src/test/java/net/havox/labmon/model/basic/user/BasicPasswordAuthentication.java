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

package net.havox.labmon.model.basic.user;

import net.havox.labmon.model.api.user.PasswordAuthentication;
import net.havox.labmon.model.basic.AbstractChangeAwareAndIdentifiableClass;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Basic implementation of {@link PasswordAuthentication} interface.
 *
 * @author Christian Otto
 */
public class BasicPasswordAuthentication extends AbstractChangeAwareAndIdentifiableClass implements PasswordAuthentication {
    String passphrase;

    @Override
    public String getPassPhrase() {
        return passphrase;
    }

    @Override
    public void setPassPhrase(String passPhrase) {
        this.passphrase = passPhrase;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

        builder.append("id", getId());
        builder.append("passPhrase", getPassPhrase());
        builder.append("version", getVersion());

        return builder.build();
    }
}
