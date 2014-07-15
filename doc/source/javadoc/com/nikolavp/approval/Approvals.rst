.. java:import:: com.nikolavp.approval.reporters Reporters

.. java:import:: java.nio.file Path

Approvals
=========

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public final class Approvals

   Approvals for primitive types. This is a convenient static utility class that is the first thing to try when you want to use the library. If you happen to be lucky and need to verify only primitive types or array of primitive types then we got you covered.

   User: nikolavp (Nikola Petrov) Date: 07/04/14 Time: 11:38

Methods
-------
verify
^^^^^^

.. java:method:: public static void verify(int[] ints, Path path)
   :outertype: Approvals

   An overload for verifying int arrays. This will call the approval object with proper reporter and use the path for verification.

   :param ints: the int array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(byte[] bytes, Path path)
   :outertype: Approvals

   An overload for verifying byte arrays. This will call the approval object with proper reporter and use the path for verification.

   :param bytes: the byte array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(short[] shorts, Path path)
   :outertype: Approvals

   An overload for verifying short arrays. This will call the approval object with proper reporter and use the path for verification.

   :param shorts: the short array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(long[] longs, Path path)
   :outertype: Approvals

   An overload for verifying long arrays. This will call the approval object with proper reporter and use the path for verification.

   :param longs: the long array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(float[] floats, Path path)
   :outertype: Approvals

   An overload for verifying float arrays. This will call the approval object with proper reporter and use the path for verification.

   :param floats: the float array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(double[] doubles, Path path)
   :outertype: Approvals

   An overload for verifying double arrays. This will call the approval object with proper reporter and use the path for verification.

   :param doubles: the double array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(boolean[] booleans, Path path)
   :outertype: Approvals

   An overload for verifying boolean arrays. This will call the approval object with proper reporter and use the path for verification.

   :param booleans: the boolean array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(char[] chars, Path path)
   :outertype: Approvals

   An overload for verifying char arrays. This will call the approval object with proper reporter and use the path for verification.

   :param chars: the char array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(String[] strings, Path path)
   :outertype: Approvals

   An overload for verifying string arrays. This will call the approval object with proper reporter and use the path for verification.

   :param strings: the string array that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(byte value, Path path)
   :outertype: Approvals

   An overload for verifying a single byte value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the byte that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(short value, Path path)
   :outertype: Approvals

   An overload for verifying a single short value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the short that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(int value, Path path)
   :outertype: Approvals

   An overload for verifying a single int value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the int that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(long value, Path path)
   :outertype: Approvals

   An overload for verifying a single long value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the long that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(float value, Path path)
   :outertype: Approvals

   An overload for verifying a single float value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the float that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(double value, Path path)
   :outertype: Approvals

   An overload for verifying a single double value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the double that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(boolean value, Path path)
   :outertype: Approvals

   An overload for verifying a single boolean value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the boolean that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(char value, Path path)
   :outertype: Approvals

   An overload for verifying a single char value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the char that needs to be verified
   :param path: the path in which to store the approval file

verify
^^^^^^

.. java:method:: public static void verify(String value, Path path)
   :outertype: Approvals

   An overload for verifying a single String value. This will call the approval object with proper reporter and use the path for verification.

   :param value: the String that needs to be verified
   :param path: the path in which to store the approval file

