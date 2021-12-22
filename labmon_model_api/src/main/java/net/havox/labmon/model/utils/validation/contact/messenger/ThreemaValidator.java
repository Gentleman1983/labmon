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

import net.havox.labmon.model.api.contact.messenger.Threema;
import net.havox.labmon.model.utils.validation.contact.ContactOptionValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Threema} entities.
 * <p>
 * Specifications for a given user entity:
 * - A Threema id is an 8-digit alphanumeric string
 *
 * @author Christian Otto
 */
public interface ThreemaValidator extends ContactOptionValidator<Threema> {
    @Override
    default List<String> validate(Threema validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected Threema entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (StringUtils.isAllBlank(validationTarget.getThreemaId()) || validationTarget.getThreemaId().length() != 8) {
            validationErrors.add("Expected the Threema id to be exactly 8 digits.");
        }

        if (!StringUtils.isAlphanumeric(validationTarget.getThreemaId())) {
            validationErrors.add("Expected the Threema id to be alphanumeric (A-Z0-9).");
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
