.. java:import:: com.nikolavp.approval Reporter

.. java:import:: com.nikolavp.approval.utils CrossPlatformCommand

.. java:import:: java.io File

WindowsReporters.WindowsExecutableReporter
==========================================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public static class WindowsExecutableReporter extends ExecutableDifferenceReporter
   :outertype: WindowsReporters

   Windows executable reporters should use this class instead of the more general ExecutableDifferenceReporter.

Constructors
------------
WindowsExecutableReporter
^^^^^^^^^^^^^^^^^^^^^^^^^

.. java:constructor:: public WindowsExecutableReporter(String approvalCommand, String diffCommand)
   :outertype: WindowsReporters.WindowsExecutableReporter

   Main constructor for the executable reporter.

   :param approvalCommand: the approval command
   :param diffCommand: the difference command

Methods
-------
canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: WindowsReporters.WindowsExecutableReporter

