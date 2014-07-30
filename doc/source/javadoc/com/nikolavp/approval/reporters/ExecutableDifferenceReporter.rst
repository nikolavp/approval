.. java:import:: com.nikolavp.approval Reporter

.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.util ArrayList

.. java:import:: java.util Arrays

.. java:import:: java.util Collections

.. java:import:: java.util List

ExecutableDifferenceReporter
============================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public class ExecutableDifferenceReporter implements Reporter

   A reporter that will shell out to an executable that is presented on the user's machine to verify the test output. Note that the approval command and the difference commands can be the same.

   ..

   * approval command will be used for the first approval
   * the difference command will be used when there is already a verified file but it is not the same as the value from the user

Constructors
------------
ExecutableDifferenceReporter
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public ExecutableDifferenceReporter(String approvalCommand, String diffCommand)
   :outertype: ExecutableDifferenceReporter

   Main constructor for the executable reporter.

   :param approvalCommand: the approval command
   :param diffCommand: the difference command

Methods
-------
approveNew
^^^^^^^^^^

.. java:method:: @Override public void approveNew(byte[] value, File approvalDestination, File fileForVerification)
   :outertype: ExecutableDifferenceReporter

buildApproveNewCommand
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected String[] buildApproveNewCommand(File approvalDestination, File fileForVerification)
   :outertype: ExecutableDifferenceReporter

buildCommandline
^^^^^^^^^^^^^^^^

.. java:method:: static List<String> buildCommandline(String... cmdParts)
   :outertype: ExecutableDifferenceReporter

buildNotTheSameCommand
^^^^^^^^^^^^^^^^^^^^^^

.. java:method:: protected String[] buildNotTheSameCommand(File fileForVerification, File fileForApproval)
   :outertype: ExecutableDifferenceReporter

canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: ExecutableDifferenceReporter

getApprovalCommand
^^^^^^^^^^^^^^^^^^

.. java:method:: protected String getApprovalCommand()
   :outertype: ExecutableDifferenceReporter

getDiffCommand
^^^^^^^^^^^^^^

.. java:method:: protected String getDiffCommand()
   :outertype: ExecutableDifferenceReporter

notTheSame
^^^^^^^^^^

.. java:method:: @Override public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: ExecutableDifferenceReporter

runProcess
^^^^^^^^^^

.. java:method:: public static Process runProcess(String... cmdParts) throws IOException
   :outertype: ExecutableDifferenceReporter

   Execute a command with the following arguments.

   :param cmdParts: the command parts
   :throws IOException: if there were any I/O errors
   :return: the process for the command that was started

startProcess
^^^^^^^^^^^^

.. java:method::  Process startProcess(String... cmdParts) throws IOException
   :outertype: ExecutableDifferenceReporter

