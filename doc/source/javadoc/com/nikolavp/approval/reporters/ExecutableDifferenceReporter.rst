.. java:import:: com.nikolavp.approval Reporter

.. java:import:: java.io File

.. java:import:: java.io IOException

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

.. java:method:: @Override public boolean approveNew(byte[] value, File approvalDestination, File fileForVerification)
   :outertype: ExecutableDifferenceReporter

canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: ExecutableDifferenceReporter

notTheSame
^^^^^^^^^^

.. java:method:: @Override public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: ExecutableDifferenceReporter

startProcess
^^^^^^^^^^^^

.. java:method::  Process startProcess(String... cmdParts) throws IOException
   :outertype: ExecutableDifferenceReporter

