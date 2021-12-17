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

import net.havox.labmon.model.api.contact.EMailAddress;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A validator for the {@link EMailAddress} entities.
 * <p>
 * Specifications for a given user entity:
 * - a valid email address
 *
 * @author Christian Otto
 */
public interface EMailAddressValidator extends ContactOptionValidator<EMailAddress> {
    @Override
    default List<String> validate(EMailAddress validationTarget) {
        List<String> validationErrors = new ArrayList<>();

        boolean allowLocalDomains = false;
        boolean allowTopLevelDomains = false;
        DomainValidator domainValidator = DomainValidator.getInstance(allowLocalDomains);
        EmailValidator emailValidator = new EmailValidator(allowLocalDomains, allowTopLevelDomains, domainValidator);

        if (emailValidator.isValid(validationTarget.getEMailAddress())) {
            validationErrors.add("EMail address is not a valid address.");
        }

        return Collections.unmodifiableList(validationErrors);
    }
}
