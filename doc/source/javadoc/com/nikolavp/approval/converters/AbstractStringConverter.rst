.. java:import:: javax.annotation Nonnull

.. java:import:: javax.annotation Nullable

.. java:import:: java.nio.charset StandardCharsets

AbstractStringConverter
=======================

.. java:package:: com.nikolavp.approval.converters
   :noindex:

.. java:type:: public abstract class AbstractStringConverter<T> extends AbstractConverter<T>

   A convenient abstract converter to handle object approvals on string representable objects.

   :param <T>: the type you want to convert

Methods
-------
getRawForm
^^^^^^^^^^

.. java:method:: @Nonnull @Override public final byte[] getRawForm(T value)
   :outertype: AbstractStringConverter

getStringForm
^^^^^^^^^^^^^

.. java:method:: @Nonnull protected abstract String getStringForm(T value)
   :outertype: AbstractStringConverter

   Gets the string representation of the type object. This representation will be written in the files you are going to then use in the approval process.

   :param value: the object that you want to convert
   :return: the string representation of the object

