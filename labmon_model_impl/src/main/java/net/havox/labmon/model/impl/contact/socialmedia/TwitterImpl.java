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

package net.havox.labmon.model.impl.contact.socialmedia;

import net.havox.labmon.model.api.contact.socialmedia.Twitter;
import net.havox.labmon.model.impl.AbstractChangeAwareClass;
import net.havox.labmon.model.utils.validation.contact.socialmedia.TwitterImplValidator;

/**
 * The functional representation of a {@link Twitter} profile.
 *
 * @author Christian Otto
 */
public class TwitterImpl extends AbstractChangeAwareClass<TwitterImpl> implements Twitter {
    /**
     * The username.
     */
    private String username;

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public void setUserName(String userName) {
        this.username = userName;
    }

    @Override
    public TwitterImplValidator getContactOptionValidator() {
        return new TwitterImplValidator();
    }

    @Override
    public boolean isValid() {
        return getContactOptionValidator().validate(this);
    }
}
