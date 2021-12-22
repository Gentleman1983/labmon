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

package net.havox.labmon.model.api.employment;

import net.havox.labmon.model.api.contact.ContactOption;
import net.havox.labmon.model.basic.contact.socialmedia.BasicTwitter;
import net.havox.labmon.model.basic.employment.BasicEmployer;

/**
 * Basic implementation of {@link AbstractEmployerTest}.
 *
 * @author Christian Otto
 */
public class BasicEmployerTest extends AbstractEmployerTest {
    @Override
    public Employer getEmployer() {
        return new BasicEmployer();
    }

    @Override
    public ContactOption getContactOption() {
        return new BasicTwitter();
    }
}
