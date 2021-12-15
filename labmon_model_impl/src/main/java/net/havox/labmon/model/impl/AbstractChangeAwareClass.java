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

package net.havox.labmon.model.impl;

import net.havox.labmon.model.api.ChangeAware;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This class provides the basic functionality to provide change awareness.
 *
 * @param <T> the type of change aware class.
 * @author Christian Otto
 */
public class AbstractChangeAwareClass<T extends AbstractChangeAwareClass<T>> implements ChangeAware, Comparable<T> {
    /**
     * The object identifier.
     */
    private Long id;

    /**
     * The version of the entity.
     */
    private long version = 1;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public long getVersion() {
        return this.version;
    }

    @Override
    public long incrementVersion() {
        return ++this.version;
    }

    @Override
    public int hashCode() {
        int hashCode;

        if (this.getId() == null) {
            hashCode = super.hashCode();
        } else {
            HashCodeBuilder builder = new HashCodeBuilder();

            builder.append(this.getId());

            hashCode = builder.toHashCode();
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null) {
            return false;
        } else if (this.getClass() == object.getClass()) {
            @SuppressWarnings("unchecked")
            T typedObject = (T) object;

            if (this.getId() == null) {
                return (this == typedObject);
            } else {
                EqualsBuilder builder = new EqualsBuilder();

                builder.append(this.getId(), typedObject.getId());

                return builder.isEquals();
            }
        }

        return false;
    }

    @Override
    public int compareTo(T other) {
        if (null == this.getId() || null == other.getId()) {
            return super.hashCode() - ((Object) other).hashCode();
        }
        return (int) (this.getId() - other.getId());
    }
}
