.. java:import:: java.io File

Reporter
========

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public interface Reporter

   Created by nikolavp on 1/30/14.

Methods
-------
approveNew
^^^^^^^^^^

.. java:method::  boolean approveNew(byte[] value, File fileForApproval, File fileForVerification)
   :outertype: Reporter

   Called by an \ :java:ref:`com.nikolavp.approval.Approval`\  object when a value for verification is produced but no old.

   :param value: the new value that came from the verification
   :param fileForApproval: the approval file(this contains the value that was passed in)
   :param fileForVerification: the file for the this new approval value @return true if the new value is approved and false otherwise
   :return: true if the value was approved and false otherwise

notTheSame
^^^^^^^^^^

.. java:method::  void notTheSame(byte[] oldValue, File fileForVerification, byte[] newValue, File fileForApproval)
   :outertype: Reporter

   Called by an \ :java:ref:`com.nikolavp.approval.Approval`\  object when values don't match in the approval process.

   :param oldValue: the old value that was found in fileForVerification from old runs
   :param newValue: the new value that was passed for verification
   :param fileForVerification: the file for this approval value
   :param fileForApproval: the file for the new content

