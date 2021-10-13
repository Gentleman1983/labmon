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

package net.havox.labmon.testutils.junit.junit4;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * Specialized JUnit 4 runner to handle extended test annotations, e.g. {@link Repeat}.
 */
public class ExtendedJunit4Runner extends BlockJUnit4ClassRunner {
    /**
     * {@inheritDoc}
     */
    public ExtendedJunit4Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Description describeChild(FrameworkMethod method) {
        // If not ignored.
        if (method.getAnnotation(Ignore.class) == null) {
            // Handle Repeat annotation.
            if (method.getAnnotation(Repeat.class) != null) {
                return describeRepeatTest(method);
            }
        }

        // In all other cases do run standard JUnit 4 behaviour.
        return super.describeChild(method);
    }

    /**
     * This handles the description of test methods annotated with the {@link Repeat} annotation.
     *
     * @param method the method
     * @return the description
     */
    private Description describeRepeatTest(FrameworkMethod method) {
        int times = method.getAnnotation(Repeat.class).value();

        Description description = Description.createSuiteDescription(
                testName(method) + " [" + times + " times]",
                method.getAnnotations());

        for (int i = 1; i <= times; i++) {
            description.addChild(Description.createTestDescription(
                    getTestClass().getJavaClass(),
                    "[" + i + "] " + testName(method)));
        }
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);

        // If not ignored.
        if (method.getAnnotation(Ignore.class) == null) {
            // Handle Repeat annotation.
            if (method.getAnnotation(Repeat.class) != null) {
                runRepeatedly(methodBlock(method), description, notifier);
            }
        }

        // In all other cases do run standard JUnit 4 behaviour.
        super.runChild(method, notifier);
    }

    /**
     * Handles the run of a {@link Repeat} annotated test method.
     *
     * @param statement   the statement
     * @param description the description
     * @param notifier    the run notifier
     */
    private void runRepeatedly(Statement statement, Description description,
                               RunNotifier notifier) {
        for (Description desc : description.getChildren()) {
            runLeaf(statement, desc, notifier);
        }
    }

}
