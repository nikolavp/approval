package com.nikolavp.approval.reporters;

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

import com.nikolavp.approval.Reporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-21 Time: 15:50
 */
@RunWith(MockitoJUnitRunner.class)
public class FirstWorkingReporterTest {
    @Mock
    final Reporter first = Mockito.mock(Reporter.class);
    @Mock
    final Reporter second = Mockito.mock(Reporter.class);
    @Mock
    final Reporter third = Mockito.mock(Reporter.class);

    @Test
    public void shouldCallNotTheSameForFirstSupportedReporter() throws Exception {
        when(second.canApprove(Mockito.any(File.class))).thenReturn(true);
        when(third.canApprove(Mockito.any(File.class))).thenReturn(true);
        new FirstWorkingReporter(first, second, third).notTheSame(null, null, null, null);

        verify(second, times(1)).notTheSame(null, null, null, null);
        verify(third, never()).notTheSame(null, null, null, null);
    }

    @Test
    public void shouldCallApproveNewForFirstSupporterReporter() throws Exception {
        when(second.canApprove(Mockito.any(File.class))).thenReturn(true);
        when(third.canApprove(Mockito.any(File.class))).thenReturn(true);
        new FirstWorkingReporter(first, second, third).approveNew(null, null, null);

        verify(second, times(1)).approveNew(null, null, null);
        verify(third, never()).approveNew(null, null, null);
    }

    @Test
    public void shouldCallCanApproveUntilItFindsAValidOne() throws Exception {
        when(second.canApprove(Mockito.any(File.class))).thenReturn(true);
        when(third.canApprove(Mockito.any(File.class))).thenReturn(true);

        final boolean canApprove = new FirstWorkingReporter(first, second, third).canApprove(null);

        assertThat(canApprove, is(true));
        verify(first, times(1)).canApprove(null);
        verify(second, times(1)).canApprove(null);
        verify(third, never()).canApprove(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfNooneCanApproveNew() throws Exception {
        new FirstWorkingReporter(first, second, third).approveNew(null, null, null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionIfNooneCanApproveNotTheSame() throws Exception {
        new FirstWorkingReporter(first, second, third).notTheSame(null, null, null, null);
    }

    @Test
    public void shouldReturnFalseFromCannApproveIfNooneCannApprove() throws Exception {
        final boolean canApprove = new FirstWorkingReporter(first, second, third).canApprove(null);

        assertThat(canApprove, is(false));
    }
}
