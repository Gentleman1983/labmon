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

import net.havox.labmon.model.api.contact.MailAddress;
import net.havox.labmon.model.utils.validation.address.AddressValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link MailAddress} entities.
 * <p>
 * Specifications for a given user entity:
 * - Receiver is not empty nor blank
 * - Address is not empty
 * - Address is valid
 *
 * @author Christian Otto
 */
public interface MailAddressValidator extends ContactOptionValidator<MailAddress> {
    @Override
    default List<String> validate(MailAddress validationTarget) {
        if (null == validationTarget) {
            return List.of("Expected mail address entity not to be null.");
        }

        List<String> validationErrors = new ArrayList<>();

        if(StringUtils.isAllBlank(validationTarget.getReceiver())) {
            validationErrors.add("Expected non empty or blank receiver.");
        }

        if(null == validationTarget.getAddress()) {
            validationErrors.add("Expected to have an address.");
        }

        AddressValidator addressValidator = validationTarget.getAddress().getValidator();
        if(!addressValidator.isValid(validationTarget.getAddress())) {
            validationErrors.addAll(addressValidator.validate(validationTarget.getAddress()));
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
