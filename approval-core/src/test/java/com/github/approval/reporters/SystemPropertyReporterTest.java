package com.github.approval.reporters;

/*
 * #%L
 * com.github.nikolavp:approval-core
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

import com.github.approval.Reporter;
import com.github.approval.converters.ReflectiveBeanConverter;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

public class SystemPropertyReporterTest {

    public static class ClassReporter extends AbstractReporter {

        @Override
        public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {

        }

        @Override
        public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {

        }

        @Override
        public boolean canApprove(File fileForApproval) {
            return false;
        }
    }

    public static class ReporterWithNotDefaultCtor extends ClassReporter {
        public ReporterWithNotDefaultCtor(String str) {
        }
    }

    @Test
    public void shouldProperlyInstantiateReporterClass() {
        SystemPropertyReporter systemReporter = SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(ClassReporter.class.getName());
        Reporter reporter = systemReporter.getReporter();
        Assert.assertTrue(reporter.getClass() == ClassReporter.class);
    }

    @Test(expected = NoSuchMethodException.class)
    public void shouldReportIfClassDoesntExist() throws Throwable {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(ReporterWithNotDefaultCtor.class.getName());
        } catch (AssertionError error) {
            throw error.getCause();
        }
    }

    @Test
    public void shouldProperlyInstantiateOtherClassesButThrowOnCasting() {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(ReflectiveBeanConverter.class.getName());
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            Assert.assertThat(error.getMessage(), CoreMatchers.containsString("but it isn't a reporter"));
        }
    }

    @Test
    public void shouldProperlyGetInstancesFromFactoryMethods() {
        SystemPropertyReporter systemReporter = SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(Reporters.class.getName() + ".gvim");
        Reporter reporter = systemReporter.getReporter();
        Assert.assertTrue(reporter.getClass() == ExecutableDifferenceReporter.class);
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldThrowIfTheClassFromTheMethodDefinitionDoesntExist() throws Throwable {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod("com.github.approval.NonEistingClass.gvim");
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            throw error.getCause();
        }
    }

    @Test(expected = NoSuchMethodException.class)
    public void shouldThrowIfTheFactoryMethodAcceptsParameters() throws Throwable {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(this.getClass().getName() + ".failingMethod");
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            throw error.getCause();
        }
    }

    public static void failingMethod(String param) {
    }

    @Test
    public void shouldThrowIfReturnedInstanceFromMethodIsNotReporter() {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(this.getClass().getName() + ".failingMethodBadInstance");
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            Assert.assertThat(error.getMessage(), CoreMatchers.containsString("is not a reporter"));
        }
    }

    public static ReflectiveBeanConverter failingMethodBadInstance() {
        return new ReflectiveBeanConverter();
    }

    @Test
    public void shouldThrowProperExceptionOnReallyReallyBadInputThatBreaksSplit() {
        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod("ClassThatDoesntExist");
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            Assert.assertThat(error.getMessage(), CoreMatchers.containsString("Couldn't instantiate class "));
        }

        try {
            SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(ReflectiveBeanConverter.class.getName() + ".");
            Assert.fail("Should throw an exception!");
        } catch (AssertionError error) {
            Assert.assertThat(error.getMessage(), CoreMatchers.containsString("Couldn't instantiate class "));
        }
    }

    private static Reporter mockedReporter = Mockito.mock(Reporter.class);

    @Test
    public void shouldProperlyCallMethodsOnTheInstantiatedReporter() {
        SystemPropertyReporter instance = SystemPropertyReporter.getInstanceForClassnameOrFactoryMethod(SystemPropertyReporterTest.class.getName() + ".reporter");

        instance.notTheSame(null, null, null, null);
        Mockito.verify(mockedReporter).notTheSame(null, null, null, null);

        instance.approveNew(null, null, null);
        Mockito.verify(mockedReporter).approveNew(null, null, null);

        instance.canApprove(null);
        Mockito.verify(mockedReporter).canApprove(null);
    }

    public static Reporter reporter() {
        return mockedReporter;
    }
}
