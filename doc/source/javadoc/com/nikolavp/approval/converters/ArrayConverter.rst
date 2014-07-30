.. java:import:: javax.annotation Nonnull

.. java:import:: java.nio.charset StandardCharsets

ArrayConverter
==============

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public class ArrayConverter<T> extends AbstractStringConverter<T[]>

   An array converter that uses another converter for it's items. This allows this converter to be composed with another one and allow you to convert your types even if they are in an array. User: nikolavp Date: 20/03/14 Time: 19:34

   :param <T>: The type of the items in the list that this converter accepts

Constructors
------------
ArrayConverter
^^^^^^^^^^^^^^

.. java:constructor:: public ArrayConverter(Converter<T> typeConverter)
   :outertype: ArrayConverter

   Creates an array converter that will use the other converter for it's items and just make array structure human readable.

   :param typeConverter: the converters for the items in the array

Methods
-------
getStringForm
^^^^^^^^^^^^^

.. java:method:: @Nonnull @Override protected String getStringForm(T[] values)
   :outertype: ArrayConverter

