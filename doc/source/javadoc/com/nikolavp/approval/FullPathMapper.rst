.. java:import:: javax.annotation Nonnull

.. java:import:: java.nio.file Path

FullPathMapper
==============

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public interface FullPathMapper<T>

   A mapper that unlike \ :java:ref:`PathMapper`\  doesn't resolve the approval file path based on a given sub path but only needs the value. Of course there are possible implementations that don'n even need the value like \ :java:ref:`com.nikolavp.approval.pathmappers.JunitPathMapper`\ .

   :param <T>: the value that will be approved

   **See also:** :java:ref:`PathMapper`

Methods
-------
getApprovalPath
^^^^^^^^^^^^^^^

.. java:method:: @Nonnull  Path getApprovalPath(T value)
   :outertype: FullPathMapper

   Get the full approval path based on the value.

   :param value: the value that will be approved and for which the approval path will be built
   :return: a Path for the given value

