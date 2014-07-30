.. java:import:: javax.annotation Nonnull

.. java:import:: javax.annotation Nullable

.. java:import:: java.nio.charset StandardCharsets

DefaultConverter
================

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public class DefaultConverter implements Converter<byte[]>

   Just a simple converter for byte array primitives. We might want to move this into \ :java:ref:`Converters`\ . User: nikolavp Date: 28/02/14 Time: 14:54

Methods
-------
getRawForm
^^^^^^^^^^

.. java:method:: @Nonnull @Override public byte[] getRawForm(byte[] value)
   :outertype: DefaultConverter

