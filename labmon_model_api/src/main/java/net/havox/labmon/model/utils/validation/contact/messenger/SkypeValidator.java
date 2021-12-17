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

package net.havox.labmon.model.utils.validation.contact.messenger;

import net.havox.labmon.model.api.contact.messenger.Skype;
import net.havox.labmon.model.utils.validation.contact.ContactOptionValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Skype} entities.
 * <p>
 * Specifications for a given user entity:
 * - A skype username consists of 6 to 22 characters
 * - It starts with a letter
 *
 * @author Christian Otto
 */
public interface SkypeValidator extends ContactOptionValidator<Skype> {
    @Override
    default List<String> validate(Skype validationTarget) {
        List<String> validationErrors = new ArrayList<>();

        if (validationTarget.getUserName().length() < 6) {
            validationErrors.add("Expected username to be at least 6 characters.");
        }
        if (validationTarget.getUserName().length() > 22) {
            validationErrors.add("Expected username to be at maximum 22 characters.");
        }
        if (Character.isLetter(validationTarget.getUserName().charAt(0))) {
            validationErrors.add("Expected the username to start with a letter.");
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
