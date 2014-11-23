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

import java.io.File;
import java.lang.reflect.Method;

/**
 * A reporter that uses a reporter specified in a property value. This reporter is perfect for first parameter in
 * {@link Reporters#firstWorking(com.github.approval.Reporter...)}.
 *
 * @see #getInstance(String)
 */
public final class SystemPropertyReporter implements Reporter {

    private Reporter reporter;

    private SystemPropertyReporter(String classOrFactoryMethod) {
        try {
            Class<?> aClass = Class.forName(classOrFactoryMethod);
            try {
                Object instance = aClass.getConstructor().newInstance();
                if (!(instance instanceof Reporter)) {
                    throw new AssertionError("We got an instance of " + aClass + " but it isn't a reporter!");
                }
                this.reporter = (Reporter) instance;
            } catch (ReflectiveOperationException e) {
                throw new AssertionError(aClass + " exists but we couldn't instantiate it!", e);
            }
        } catch (ClassNotFoundException e) {
            int i = classOrFactoryMethod.lastIndexOf('.');
            if (i == -1 || i + 1 == classOrFactoryMethod.length()) {
                throw new AssertionError("Couldn't instantiate class " + classOrFactoryMethod, e);
            }
            String possibleClass = classOrFactoryMethod.substring(0, i);
            String method = classOrFactoryMethod.substring(i + 1);

            try {
                Class<?> aClass = Class.forName(possibleClass);
                try {
                    Method m = aClass.getMethod(method);

                    Object instance = m.invoke(null);

                    if (!(instance instanceof Reporter)) {
                        throw new AssertionError("The instance that was returned from the factory method " + method + " in " + aClass + " is not a reporter");
                    }

                    this.reporter = (Reporter) instance;

                } catch (ReflectiveOperationException e1) {
                    throw new AssertionError("Couldn't call static factory method " + method + " in " + aClass, e1);
                }
            } catch (ClassNotFoundException e1) {
                throw new AssertionError("Couldn't find class " + possibleClass, e1);
            }
        }
    }

    /**
     * Returns the internal reporter that was constructed from the system property.
     *
     * @return the reporter that was constructed
     */
    public Reporter getReporter() {
        return reporter;
    }

    /**
     * A factory method that returns a reporter which will behave the same as the one specified in propertyName value.
     * For example the following instance
     * <pre>
     *     {@code
     *     SystemPropertyReporter.getInstance(&quote;response.reporter&quote;);
     *     }
     *
     * </pre>
     * <p>
     * will use the reporter that was specified in -Drensponse.reporter on the command line.
     * The value should be a fully classified class name or a static factory method. Valid values are $packagename.$classname or
     * $packagename.$classname.$staticmethod
     * </p>
     *
     * @param propertyName the property name from which value we will get the class or the static method
     * @return the new reporter
     */
    public static SystemPropertyReporter getInstance(String propertyName) {
        return new SystemPropertyReporter(System.getProperty(propertyName));
    }

    //visible for testing only
    static SystemPropertyReporter getInstanceForClassnameOrFactoryMethod(String name) {
        return new SystemPropertyReporter(name);
    }

    @Override
    public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval) {
        reporter.notTheSame(oldValue, fileForVerification, newValue, fileForApproval);
    }

    @Override
    public void approveNew(byte[] value, File fileForApproval, File fileForVerification) {
        reporter.approveNew(value, fileForApproval, fileForVerification);
    }

    @Override
    public boolean canApprove(File fileForApproval) {
        return reporter.canApprove(fileForApproval);
    }
}
