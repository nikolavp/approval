.. java:import:: java.io UnsupportedEncodingException

.. java:import:: java.lang.reflect Field

ReflectiveBeanConverter
=======================

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public class ReflectiveBeanConverter<T> implements Converter<T>

   A converter that accepts a bean object and uses reflection to introspect the fields of the bean and builds a raw form of them. Note that the fields must have a human readable string representation for this converter to work properly. User: nikolavp Date: 28/02/14 Time: 15:12

   :param <T>: the type of objects you want convert to it's raw form

Methods
-------
getRawForm
^^^^^^^^^^

.. java:method:: @Override public byte[] getRawForm(T value)
   :outertype: ReflectiveBeanConverter

