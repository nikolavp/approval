package com.github.approval;

/*
 * #%L
 * approval
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * User: github (Nikola Petrov)
 * Date: 07/04/14
 * Time: 10:43
 */
public abstract class StaticUtilityTestAbstract {

    protected abstract Class<?> getUtilityClassUnderTest();

    @Test
    public void shouldHitPrivateConstructorForClass() throws Exception {
       /*
        * Cobertura doesn't have the option to ignore private empty constructors and we make our utility classes with those.
        * This breaks the stats for coverage :(. Fix it here and remove the hack when they give a sane option for this.
        * Code taken from http://www.brainoverload.nl/java/163/improve-code-coverage-on-private-cronstructors-with-cobertura
        */
        Constructor<?>[] cons = getUtilityClassUnderTest().getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance((Object[])null);
    }
}
