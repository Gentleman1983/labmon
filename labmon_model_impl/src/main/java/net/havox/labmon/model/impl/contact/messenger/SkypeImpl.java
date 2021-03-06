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

package net.havox.labmon.model.impl.contact.messenger;

import net.havox.labmon.model.api.contact.messenger.Skype;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.contact.messenger.SkypeImplValidator;

/**
 * The functional representation of a {@link Skype} account.
 *
 * @author Christian Otto
 */
public class SkypeImpl extends AbstractChangeAwareClass<SkypeImpl> implements Skype {
    /**
     * The username.
     */
    private String userName;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public SkypeImplValidator getContactOptionValidator() {
        return new SkypeImplValidator();
    }

    @Override
    public boolean isValid() {
        return getContactOptionValidator().isValid(this);
    }
}
