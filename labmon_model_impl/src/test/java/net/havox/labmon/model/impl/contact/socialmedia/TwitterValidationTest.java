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
import net.havox.labmon.model.utils.validation.contact.socialmedia.AbstractTwitterValidationTest;
import net.havox.labmon.model.utils.validation.contact.socialmedia.TwitterImplValidator;
import net.havox.labmon.model.utils.validation.contact.socialmedia.TwitterValidator;

/**
 * Validation test for {@link TwitterImpl}.
 *
 * @author Christian Otto
 */
public class TwitterValidationTest extends AbstractTwitterValidationTest { // NOSONAR Validation test is only inherited, so only derived test cases.
    @Override
    public Twitter getTwitter() throws Exception {
        return new TwitterImpl();
    }

    @Override
    public TwitterValidator getTwitterValidator() throws Exception {
        return new TwitterImplValidator();
    }
}
