.. java:import:: java.lang.reflect Array

Converters
==========

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public final class Converters

   Converters for primitive types. Most of these just call toString on the passed object and then get the raw representation of the string result. . User: nikolavp Date: 28/02/14 Time: 17:25

Fields
------
BOOLEAN
^^^^^^^

.. java:field:: public static final Converter<Boolean> BOOLEAN
   :outertype: Converters

   A converter for the primitive or wrapper boolean object.

BOOLEAN_ARRAY
^^^^^^^^^^^^^

.. java:field:: public static final Converter<boolean[]> BOOLEAN_ARRAY
   :outertype: Converters

   A converter for the primitive boolean arrays.

BYTE
^^^^

.. java:field:: public static final Converter<Byte> BYTE
   :outertype: Converters

   A converter for the primitive or wrapper byte types.

BYTE_ARRAY
^^^^^^^^^^

.. java:field:: public static final Converter<byte[]> BYTE_ARRAY
   :outertype: Converters

   A converter for the primitive byte arrays.

CHAR
^^^^

.. java:field:: public static final Converter<Character> CHAR
   :outertype: Converters

   A converter for the primitive or wrapper char object.

CHAR_ARRAY
^^^^^^^^^^

.. java:field:: public static final Converter<char[]> CHAR_ARRAY
   :outertype: Converters

   A converter for the primitive char arrays.

DOUBLE
^^^^^^

.. java:field:: public static final Converter<Double> DOUBLE
   :outertype: Converters

   A converter for the primitive or wrapper double object.

DOUBLE_ARRAY
^^^^^^^^^^^^

.. java:field:: public static final Converter<double[]> DOUBLE_ARRAY
   :outertype: Converters

   A converter for the primitive double arrays.

FLOAT
^^^^^

.. java:field:: public static final Converter<Float> FLOAT
   :outertype: Converters

   A converter for the primitive or wrapper float object.

FLOAT_ARRAY
^^^^^^^^^^^

.. java:field:: public static final Converter<float[]> FLOAT_ARRAY
   :outertype: Converters

   A converter for the primitive float arrays.

INTEGER
^^^^^^^

.. java:field:: public static final Converter<Integer> INTEGER
   :outertype: Converters

   A converter for the primitive or wrapper int object.

INTEGER_ARRAY
^^^^^^^^^^^^^

.. java:field:: public static final Converter<int[]> INTEGER_ARRAY
   :outertype: Converters

   A converter for the primitive int arrays.

LONG
^^^^

.. java:field:: public static final Converter<Long> LONG
   :outertype: Converters

   A converter for the primitive or wrapper long object.

LONG_ARRAY
^^^^^^^^^^

.. java:field:: public static final Converter<long[]> LONG_ARRAY
   :outertype: Converters

   A converter for the primitive long arrays.

SHORT
^^^^^

.. java:field:: public static final Converter<Short> SHORT
   :outertype: Converters

   A converter for the primitive or wrapper short object.

SHORT_ARRAY
^^^^^^^^^^^

.. java:field:: public static final Converter<short[]> SHORT_ARRAY
   :outertype: Converters

   A converter for the primitive short arrays.

STRING
^^^^^^

.. java:field:: public static final Converter<String> STRING
   :outertype: Converters

   A converter for the String object.

STRING_ARRAY
^^^^^^^^^^^^

.. java:field:: public static final Converter<String[]> STRING_ARRAY
   :outertype: Converters

   A converter for an array of strings.

Methods
-------
of
^^

.. java:method:: static <T> Converter<T> of()
   :outertype: Converters

ofArray
^^^^^^^

.. java:method:: static Converter ofArray()
   :outertype: Converters

