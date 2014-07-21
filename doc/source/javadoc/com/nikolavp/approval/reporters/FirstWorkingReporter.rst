.. java:import:: com.nikolavp.approval Reporter

.. java:import:: java.io File

FirstWorkingReporter
====================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type::  class FirstWorkingReporter implements Reporter

   User: nikolavp (Nikola Petrov) Date: 14-7-21 Time: 15:35

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

.. java:method:: @Override public boolean approveNew(byte[] value, File fileForApproval, File fileForVerification)
   :outertype: FirstWorkingReporter

canApprove
^^^^^^^^^^

.. java:method:: @Override public boolean canApprove(File fileForApproval)
   :outertype: FirstWorkingReporter

notTheSame
^^^^^^^^^^

.. java:method:: @Override public void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: FirstWorkingReporter

