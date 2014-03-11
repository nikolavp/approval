package com.nikolavp.approval;

import com.nikolavp.approval.converters.Converters;
import com.nikolavp.approval.converters.DefaultConverter;
import com.nikolavp.approval.reporters.Reporters;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: nikolavp
 * Date: 28/02/14
 * Time: 15:25
 */
public class ApprovalBuilderTest {

    @Test
    public void shouldBuildApprovalsWithDifferentParameters() throws Exception {
        //assign
        Approval<byte[]> approver = Approval.of(byte[].class)
                .withConveter(new DefaultConverter())
                .withReporter(Reporters.gvim()).build();

        Assert.assertThat(approver, CoreMatchers.notNullValue());
    }

    @Test
    public void shouldProvideDefaultConvertersForCommonObjects() throws Exception {
        Approval<Byte> byteApproval = Approval.of(byte.class).build();
        Assert.assertThat(byteApproval.getConverter(), CoreMatchers.sameInstance(Converters.BYTE));

        Approval<Integer> intApproval = Approval.of(int.class).build();
        Assert.assertThat(intApproval.getConverter(), CoreMatchers.sameInstance(Converters.INTEGER));

        Approval<String> stringApproval = Approval.of(String.class).build();
        Assert.assertThat(stringApproval.getConverter(), CoreMatchers.sameInstance(Converters.STRING));
    }
}
