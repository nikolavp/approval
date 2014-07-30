.. java:import:: com.nikolavp.approval FullPathMapper

.. java:import:: com.nikolavp.approval PathMapper

.. java:import:: org.junit.rules TestRule

.. java:import:: org.junit.runner Description

.. java:import:: org.junit.runners.model Statement

.. java:import:: javax.annotation Nonnull

.. java:import:: javax.annotation Nullable

.. java:import:: java.io File

.. java:import:: java.nio.file Path

.. java:import:: java.nio.file Paths

JunitPathMapper
===============

.. java:package:: com.nikolavp.approval.pathmappers
   :noindex:

.. java:type:: public class JunitPathMapper implements TestRule, PathMapper, FullPathMapper

   A path mapper that have to be declared as a \ :java:ref:`org.junit.Rule`\  and will use the standard junit mechanisms to put your approval results in $package-name-with-slashes/$classname/$methodname.

   This class can be used as a \ :java:ref:`com.nikolavp.approval.PathMapper`\  in which case it will put your approval results in that directory or you can use it as a \ :java:ref:`com.nikolavp.approval.FullPathMapper`\  in which case your approval result for the \ **single virifacion**\  will be put in a file with that path. In the latter case you will have to make sure that there aren't two approvals for a single test method.

Constructors
------------
JunitPathMapper
^^^^^^^^^^^^^^^

.. java:constructor:: public JunitPathMapper(Path parentPath)
   :outertype: JunitPathMapper

   A parent path in which you want to put your approvals if any.

   :param parentPath: the parent path

Methods
-------
apply
^^^^^

.. java:method:: @Override public Statement apply(Statement base, Description description)
   :outertype: JunitPathMapper

getApprovalPath
^^^^^^^^^^^^^^^

.. java:method:: @Nonnull @Override public Path getApprovalPath(Object value)
   :outertype: JunitPathMapper

getCurrentTestPath
^^^^^^^^^^^^^^^^^^

.. java:method::  Path getCurrentTestPath()
   :outertype: JunitPathMapper

getPath
^^^^^^^

.. java:method:: @Nonnull @Override public Path getPath(Object value, Path approvalFilePath)
   :outertype: JunitPathMapper

