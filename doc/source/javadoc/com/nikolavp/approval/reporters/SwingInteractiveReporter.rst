.. java:import:: com.nikolavp.approval Reporter

.. java:import:: com.nikolavp.approval.utils DefaultFileSystemUtils

.. java:import:: com.nikolavp.approval.utils FileSystemUtils

.. java:import:: javax.swing JOptionPane

.. java:import:: java.awt GraphicsEnvironment

.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file Path

SwingInteractiveReporter
========================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public class SwingInteractiveReporter implements Reporter

   A reporter that can wrap another reporter and give you a prompt to approve the new result value.

   This is useful for reporters that cannot give you a clear way to create the result file. If for example you are using a reporter that only shows you the resulting value but you cannot move it to the proper result file for the approval.

   If you say OK/YES on the prompt then the result will be written to the proper file for the next approval time.

Constructors
------------
SwingInteractiveReporter
^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor::  SwingInteractiveReporter(Reporter other, FileSystemUtils fileSystemReadWriter)
   :outertype: SwingInteractiveReporter

Methods
-------
approveNew
^^^^^^^^^^

.. java:method:: @Override public void approveNew(byte[] value, File fileForApproval, File fileForVerification)
   :outertype: SwingInteractiveReporter

canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: SwingInteractiveReporter

isHeadless
^^^^^^^^^^

.. java:method::  boolean isHeadless()
   :outertype: SwingInteractiveReporter

notTheSame
^^^^^^^^^^

.. java:method:: @Override public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: SwingInteractiveReporter

promptUser
^^^^^^^^^^

.. java:method::  int promptUser()
   :outertype: SwingInteractiveReporter

wrap
^^^^

.. java:method:: public static SwingInteractiveReporter wrap(Reporter reporter)
   :outertype: SwingInteractiveReporter

   Wrap another reporter.

   :param reporter: the other reporter
   :return: a new reporter that call the other reporter and then propmts the user

