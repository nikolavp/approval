.. java:import:: javax.annotation Nonnull

.. java:import:: javax.annotation Nullable

.. java:import:: java.nio.file Path

PathMapper
==========

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public interface PathMapper<T>

   An interface representing objects that will return an appropriate path for the approval process. Most of the times those are used because you don't want to repeat yourself with the same parent path in \ :java:ref:`com.nikolavp.approval.Approval.verify(Object,java.nio.file.Path)`\  for the path argument. This will map your approval results file from the value for approval and a possible sub path.

   :param <T>: the value that will be approved

   **See also:** :java:ref:`com.nikolavp.approval.pathmappers.ParentPathMapper`

Methods
-------
getPath
^^^^^^^

.. java:method:: @Nonnull  Path getPath(T value, Path approvalFilePath)
   :outertype: PathMapper

   Gets the path for the approval result based on the value that we want to approve and a sub path for that.

   :param value: the value that will be approved
   :param approvalFilePath: a name/subpath for the approval. This will be the path that was passed to \ :java:ref:`Approval.verify(Object,java.nio.file.Path)`\
   :return: the full path for the approval result

