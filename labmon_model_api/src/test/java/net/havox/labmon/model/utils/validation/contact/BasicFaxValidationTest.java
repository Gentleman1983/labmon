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

import net.havox.labmon.model.api.contact.Fax;
import net.havox.labmon.model.basic.contact.BasicFax;

/**
 * Basic implementation of {@link AbstractFaxValidationTest}.
 *
 * @author Christian Otto
 */
public class BasicFaxValidationTest extends AbstractFaxValidationTest {
    @Override
    public Fax getFax() {
        return new BasicFax();
    }

    @Override
    public FaxValidator getFaxValidator() {
        return new BasicFaxValidator();
    }
}
