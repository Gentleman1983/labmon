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

package net.havox.labmon.model.utils.validation.contact.socialmedia;

import net.havox.labmon.model.api.contact.socialmedia.Twitter;
import net.havox.labmon.model.utils.validation.contact.ContactOptionValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Twitter} entities.
 * <p>
 * Specifications for a given user entity:
 * - Twitter usernames are 1 to 15 letters
 * - They consist of alphanumeric letters and underscores
 *
 * @author Christian Otto
 */
public interface TwitterValidator extends ContactOptionValidator<Twitter> {
    /**
     * The {@link Twitter} username validation regular expression.
     */
    static final String TWITTER_USER_REGEX = "^[A-Za-z0-9_]{1,15}$";

    @Override
    default List<String> validate(Twitter validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected twitter entity not to be null.");
        }

        if (null == validationTarget.getUserName()) {
            return List.of("Expected username entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (validationTarget.getUserName().length() < 1) {
            validationErrors.add("Expected the username to be at least 1 letter.");
        }

        if (validationTarget.getUserName().length() > 15) {
            validationErrors.add("Expected the username to be at maximum 15 letter.");
        }

        if (!validationTarget.getUserName().matches(TWITTER_USER_REGEX)) {
            validationErrors.add("Expected the username to consist only of alphanumeric letters and underscores [A-Z0-9_].");
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
