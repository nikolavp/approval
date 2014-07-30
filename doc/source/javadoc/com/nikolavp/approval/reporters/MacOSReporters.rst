.. java:import:: com.nikolavp.approval Reporter

MacOSReporters
==============

.. java:package:: com.nikolavp.approval.reporters
   :noindex:

.. java:type:: public final class MacOSReporters

   Reporters that use macOS specific binaries, i.e. not cross platform programs.

   If you are looking for something cross platform like gvim, emacs, you better look in \ :java:ref:`com.nikolavp.approval.reporters.Reporters`\ .

Methods
-------
diffMerge
^^^^^^^^^

.. java:method:: public static Reporter diffMerge()
   :outertype: MacOSReporters

   A reporter that calls \ `diffmerge <https://sourcegear.com/diffmerge/>`_\  to show you the results.

   :return: a reporter that calls diffmerge

ksdiff
^^^^^^

.. java:method:: public static Reporter ksdiff()
   :outertype: MacOSReporters

   A reporter that calls \ `ksdiff <http://www.kaleidoscopeapp.com/ksdiff2>`_\  to show you the results.

   :return: a reporter that calls ksdiff

p4merge
^^^^^^^

.. java:method:: public static Reporter p4merge()
   :outertype: MacOSReporters

   A reporter that calls \ `p4merge <http://www.perforce.com/product/components/perforce-visual-merge-and-diff-tools>`_\  to show you the results.

   :return: a reporter that calls p4merge

tkdiff
^^^^^^

.. java:method:: public static Reporter tkdiff()
   :outertype: MacOSReporters

   A reporter that calls \ `tkdiff <http://sourceforge.net/projects/tkdiff/>`_\  to show you the results.

   :return: a reporter that calls tkdiff

