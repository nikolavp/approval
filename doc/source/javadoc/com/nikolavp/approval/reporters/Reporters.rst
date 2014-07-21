.. java:import:: com.nikolavp.approval Reporter

Reporters
=========

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public final class Reporters

   Created with IntelliJ IDEA. User: nikolavp Date: 10/02/14 Time: 15:10 To change this template use File | Settings | File Templates.

Methods
-------
console
^^^^^^^

.. java:method:: public static Reporter console()
   :outertype: Reporters

   Creates a simple reporter that will print/report approvals to the console. This reporter will use convenient command line tools under the hood to only report the changes in finds. This is perfect for batch modes or when you run your build in a CI server

   :return: a reporter that uses console unix tools under the hood

firstWorking
^^^^^^^^^^^^

.. java:method:: public static Reporter firstWorking(Reporter... others)
   :outertype: Reporters

   Get a reporter that will use the first working reporter as per \ :java:ref:`com.nikolavp.approval.Reporter.canApprove`\  for the reporting.

   :param others: an array/list of reporters that will be used
   :return: the newly created composite reporter

gedit
^^^^^

.. java:method:: public static Reporter gedit()
   :outertype: Reporters

   Creates a reporter that uses gedit under the hood for approving.

   :return: a reporter that uses gedit under the hood

gvim
^^^^

.. java:method:: public static Reporter gvim()
   :outertype: Reporters

   Creates a convenient gvim reporter. This one will use gvimdiff for difference detection and gvim for approving new files. The proper way to exit vim and not approve the new changes is with ":cq" - just have that in mind!

   :return: a reporter that uses vim under the hood

