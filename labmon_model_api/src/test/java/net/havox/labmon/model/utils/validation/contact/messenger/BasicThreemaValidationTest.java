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
import net.havox.labmon.model.basic.contact.messenger.BasicThreema;

/**
 * Basic implementation of {@link AbstractThreemaValidationTest}.
 *
 * @author Christian Otto
 */
public class BasicThreemaValidationTest extends AbstractThreemaValidationTest {
    @Override
    public Threema getThreema() throws Exception {
        return new BasicThreema();
    }

    @Override
    public ThreemaValidator getThreemaValidator() throws Exception {
        return new BasicThreemaValidator();
    }
}