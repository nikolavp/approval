.. java:import:: com.nikolavp.approval Reporter

.. java:import:: java.io File

FirstWorkingReporter
====================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type::  class FirstWorkingReporter implements Reporter

   A reporter that will compose other reporters and use the first one that can approve the objects for verification as per \ :java:ref:`com.nikolavp.approval.Reporter.canApprove(java.io.File)`\ .

Constructors
------------
FirstWorkingReporter
^^^^^^^^^^^^^^^^^^^^

.. java:constructor::  FirstWorkingReporter(Reporter... others)
   :outertype: FirstWorkingReporter

Methods
-------
approveNew
^^^^^^^^^^

.. java:method:: @Override public void approveNew(byte[] value, File fileForApproval, File fileForVerification)
   :outertype: FirstWorkingReporter

canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: FirstWorkingReporter

notTheSame
^^^^^^^^^^

.. java:method:: @Override public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: FirstWorkingReporter

