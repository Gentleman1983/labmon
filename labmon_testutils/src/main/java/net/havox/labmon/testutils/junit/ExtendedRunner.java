/*
 * Copyright (C) 2021 [haVox] Design
 * Created by The_G
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

package net.havox.labmon.testutils.junit;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class ExtendedRunner extends BlockJUnit4ClassRunner
{

  public ExtendedRunner( Class<?> klass ) throws InitializationError
  {
    super( klass );
  }

  @Override
  protected Description describeChild( FrameworkMethod method )
  {
    if ( method.getAnnotation( Repeat.class ) != null
            && method.getAnnotation( Ignore.class ) == null )
    {
      return describeRepeatTest( method );
    }
    return super.describeChild( method );
  }

  private Description describeRepeatTest( FrameworkMethod method )
  {
    int times = method.getAnnotation( Repeat.class ).value();

    Description description = Description.createSuiteDescription(
            testName( method ) + " [" + times + " times]",
            method.getAnnotations() );

    for ( int i = 1; i <= times; i++ )
    {
      description.addChild( Description.createTestDescription(
              getTestClass().getJavaClass(),
              "[" + i + "] " + testName( method ) ) );
    }
    return description;
  }

  @Override
  protected void runChild( final FrameworkMethod method, RunNotifier notifier )
  {
    Description description = describeChild( method );

    if ( method.getAnnotation( Repeat.class ) != null
            && method.getAnnotation( Ignore.class ) == null )
    {
      runRepeatedly( methodBlock( method ), description, notifier );
    }
    super.runChild( method, notifier );
  }

  private void runRepeatedly( Statement statement, Description description,
          RunNotifier notifier )
  {
    for ( Description desc : description.getChildren() )
    {
      runLeaf( statement, desc, notifier );
    }
  }

}
