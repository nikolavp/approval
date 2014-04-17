package com.nikolavp.approval;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: nikolavp (Nikola Petrov)
 * Date: 07/04/14
 * Time: 11:38
 */
public class ApprovalsTest extends StaticUtilityTestAbstract {
    @Override
    protected Class<?> getUtilityClassUnderTest() {
        return Approvals.class;
    }

    private Path get(String testName) {
        final String basePath = Paths.get("src", "test", "resources", "approvals", ApprovalsTest.class.getName()).toString();
        return Paths.get(basePath, testName);
    }

    @Test
    public void shouldVerifyArrayTypesProperly() throws Exception {
        Approvals.verify(new byte[] {1, 3, 3}, get("shouldVerifyArrayTypesProperlyBytes.txt"));
        Approvals.verify(new short[] {1, 3, 3}, get("shouldVerifyArrayTypesProperlyShorts.txt"));
        Approvals.verify(new int[] {1, 3, 3}, get("shouldVerifyArrayTypesProperlyInts.txt"));
        Approvals.verify(new long[] {1L, 3L, 3L}, get("shouldVerifyArrayTypesProperlyLongs.txt"));
        Approvals.verify(new float[] {1.0F, 2.0F, 3.0F}, get("shouldVerifyArrayTypesProperlyFloats.txt"));
        Approvals.verify(new double[] {1.0D, 2.0D, 3.0D}, get("shouldVerifyArrayTypesProperlyDoubles.txt"));
        Approvals.verify(new boolean[] {true, false, true}, get("shouldVerifyArrayTypesProperlyBooleans.txt"));
        Approvals.verify(new char[] {'a', 'b', 'c'}, get("shouldVerifyArrayTypesProperlyChars.txt"));
        Approvals.verify(new String[] {"first", "second", "third"}, get("shouldVerifyArrayTypesProperlyStrings.txt"));
    }

    @Test
    public void shouldVerifyAllPrimitiveTypes() throws Exception {
        Approvals.verify((byte)1, get("shouldVerifyAllPrimitiveTypesByte.txt"));
        Approvals.verify((short)1, get("shouldVerifyAllPrimitiveTypesShort.txt"));
        Approvals.verify(1, get("shouldVerifyAllPrimitiveTypesInt.txt"));
        Approvals.verify((long)1, get("shouldVerifyAllPrimitiveTypesLong.txt"));
        Approvals.verify(1.0F, get("shouldVerifyAllPrimitiveTypesFloat.txt"));
        Approvals.verify(1.0D, get("shouldVerifyAllPrimitiveTypesDouble.txt"));
        Approvals.verify(true, get("shouldVerifyAllPrimitiveTypesBoolean.txt"));
        Approvals.verify('a', get("shouldVerifyAllPrimitiveTypesChar.txt"));
        Approvals.verify("asd", get("shouldVerifyAllPrimitiveTypesString.txt"));
    }
}
