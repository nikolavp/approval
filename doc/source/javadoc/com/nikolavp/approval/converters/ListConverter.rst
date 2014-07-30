.. java:import:: javax.annotation Nonnull

.. java:import:: java.nio.charset StandardCharsets

.. java:import:: java.util List

ListConverter
=============

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public class ListConverter<T> extends AbstractStringConverter<List<T>>

   A list converter that uses another converter for it's items. This allows this converter to be composed with another one and allow you to convert your types even if they are in a list. User: nikolavp Date: 28/02/14 Time: 17:47

   :param <T>: The type of the items in the list that this converter accepts

Constructors
------------
ListConverter
^^^^^^^^^^^^^

.. java:constructor:: public ListConverter(Converter<T> typeConverter)
   :outertype: ListConverter

   Creates a list converter that will use the other converter for it's items and just make list structure human readable.

   :param typeConverter: the converters for the items

Methods
-------
getStringForm
^^^^^^^^^^^^^

.. java:method:: @Nonnull @Override protected String getStringForm(List<T> values)
   :outertype: ListConverter

