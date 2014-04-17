package com.nikolavp.approval;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * User: nikolavp (Nikola Petrov)
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
