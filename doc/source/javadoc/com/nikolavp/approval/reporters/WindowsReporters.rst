.. java:import:: com.nikolavp.approval Reporter

.. java:import:: com.nikolavp.approval.utils CrossPlatformCommand

.. java:import:: java.io File

WindowsReporters
================

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public final class WindowsReporters

   Reporters that use windows specific binaries, i.e. the programs that are used are not cross platform.

   If you are looking for something cross platform like gvim, emacs, you better look in \ :java:ref:`com.nikolavp.approval.reporters.Reporters`\ .

Methods
-------
beyondCompare
^^^^^^^^^^^^^

.. java:method:: public static Reporter beyondCompare()
   :outertype: WindowsReporters

   A reporter that calls \ `Beyond Compare 3 <http://www.scootersoftware.com/moreinfo.php>`_\  to show you the results.

   :return: a reporter that calls beyond compare

notepadPlusPlus
^^^^^^^^^^^^^^^

.. java:method:: public static Reporter notepadPlusPlus()
   :outertype: WindowsReporters

   A reporter that calls \ `notepad++ <http://notepad-plus-plus.org/>`_\  to show you the results.

   :return: a reporter that calls notepad++

tortoiseImage
^^^^^^^^^^^^^

.. java:method:: public static Reporter tortoiseImage()
   :outertype: WindowsReporters

   A reporter that calls \ `TortoiseIDiff <http://tortoisesvn.net/TortoiseIDiff.html>`_\  to show you the results.

   :return: a reporter that calls tortoise image difference tool

tortoiseText
^^^^^^^^^^^^

.. java:method:: public static Reporter tortoiseText()
   :outertype: WindowsReporters

   A reporter that calls \ `TortoiseMerge <http://tortoisesvn.net/>`_\  to show you the results.

   :return: a reporter that calls tortoise difference tool for text

winMerge
^^^^^^^^

.. java:method:: public static Reporter winMerge()
   :outertype: WindowsReporters

   A reporter that calls \ `WinMerge <http://winmerge.org/about/>`_\  to show you the results.

   :return: a reporter that calls win merge

