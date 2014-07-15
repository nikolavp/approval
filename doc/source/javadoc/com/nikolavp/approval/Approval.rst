.. java:import:: com.nikolavp.approval.converters Converter

.. java:import:: com.nikolavp.approval.converters Converters

.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file FileSystems

.. java:import:: java.nio.file Path

.. java:import:: java.util Arrays

Approval
========

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public class Approval<T>

   The main entry point class for each approval process. This is the main service class that is doing the hard work - it calls other classes for custom logic based on the object that is approved. Created by nikolavp on 1/29/14.

   :param <T>: the type of the object that will be approved by this \ :java:ref:`Approval`\

Constructors
------------
Approval
^^^^^^^^

.. java:constructor::  Approval(Reporter reporter, Converter<T> converter)
   :outertype: Approval

   Create a new object that will be able to approve "things" for you.

   :param reporter: a reporter that will be notified as needed for approval events
   :param converter: a converter that will be responsible for converting the type for approval to raw form

Approval
^^^^^^^^

.. java:constructor::  Approval(Reporter reporter, Converter<T> converter, FileSystemUtils fileSystemReadWriter)
   :outertype: Approval

   This ctor is for testing only.

Methods
-------
getApprovalPath
^^^^^^^^^^^^^^^

.. java:method:: public static Path getApprovalPath(Path filePath)
   :outertype: Approval

   Get the path for approval from the original file path.

   :param filePath: the original path to value
   :return: the path for approval

getConverter
^^^^^^^^^^^^

.. java:method::  Converter<T> getConverter()
   :outertype: Approval

getReporter
^^^^^^^^^^^

.. java:method::  Reporter getReporter()
   :outertype: Approval

of
^^

.. java:method:: public static <T> ApprovalBuilder<T> of(Class<T> clazz)
   :outertype: Approval

   Create a new approval builder that will be able to approve objects from the specified class type.

   :param clazz: the class object for the things you will be approving
   :param <T>: the type of the objects you will be approving
   :return: an approval builder that will be able to construct an \ :java:ref:`Approval`\  for your objects

verify
^^^^^^

.. java:method:: public void verify(T value, Path filePath)
   :outertype: Approval

   Verify the value that was passed in.

   :param value: the value object to be approved
   :param filePath: the path where the value will be kept for further approval

