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

package net.havox.labmon.model.utils.validation.contact;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import net.havox.labmon.model.api.contact.Phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link Phone} entities.
 * <p>
 * Specifications for a given user entity:
 * - A valid phone number
 *
 * @author Christian Otto
 */
public interface PhoneValidator extends ContactOptionValidator<Phone> {
    @Override
    default List<String> validate(Phone validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected phone number entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if (null == validationTarget.getPhoneNumber() || !PhoneNumberUtil.getInstance().isValidNumber(validationTarget.getPhoneNumber())) {
            validationErrors.add("Expected an valid phone number.");
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
