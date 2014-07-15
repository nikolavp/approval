.. java:import:: com.nikolavp.approval.converters Converter

.. java:import:: com.nikolavp.approval.converters Converters

.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file FileSystems

.. java:import:: java.nio.file Path

.. java:import:: java.util Arrays

Approval.ApprovalBuilder
========================

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public static final class ApprovalBuilder<T>
   :outertype: Approval

   A builder class for approvals. This is used to conveniently build new approvals for a specific type with custom reporters, converters, etc.

   :param <T>: the type that will be approved by the the resulting approval object

Methods
-------
build
^^^^^

.. java:method:: public Approval<T> build()
   :outertype: Approval.ApprovalBuilder

   Creates a new approval with configuration/options(reporters, converters, etc) that were set for this builder.

   :return: a new approval for the specified type with custom configuration if any

withConveter
^^^^^^^^^^^^

.. java:method:: public ApprovalBuilder<T> withConveter(Converter<T> converterToBeUsed)
   :outertype: Approval.ApprovalBuilder

   Set the converter that will be used when building new approvals with this builder.

   :param converterToBeUsed: the converter that will be used from the approval that will be built
   :return: the same builder for chaining

   **See also:** :java:ref:`Converter`

withReporter
^^^^^^^^^^^^

.. java:method:: public ApprovalBuilder<T> withReporter(Reporter reporterToBeUsed)
   :outertype: Approval.ApprovalBuilder

   Set the reporter that will be used when building new approvals with this builder.

   :param reporterToBeUsed: the reporter that will be used from the approval that will be built
   :return: the same builder for chaninig

   **See also:** :java:ref:`Reporter`

