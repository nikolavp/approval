.. java:import:: java.util Locale

CrossPlatformCommand
====================

.. java:package:: com.nikolavp.approval.utils
   :noindex:

.. java:type:: public abstract class CrossPlatformCommand<T>

   A command that when run will execute the proper method for the specified operating system. This is especially useful when you are trying to create for handling different platforms. Here is an example usage of the class:

   .. parsed-literal::

      final Boolean result = new CrossPlatformCommand<Boolean>() {
          &#064;Override protected Boolean onWindows() {
          //do your logic for windows
          }

          &#064;Override protected Boolean onUnix() {
          //do your logic for unix
          }

          &#064;Override protected Boolean onMac() {
          //do your logic for mac
          }

          &#064;Override protected Boolean onSolaris() {
          //do your logic for solaris
          }
      }.execute();

   :param <T>: the result from the command.

Methods
-------
execute
^^^^^^^

.. java:method:: public T execute()
   :outertype: CrossPlatformCommand

   Main method that should be executed. This will return the proper result depending on your platform.

   :return: the result

isMac
^^^^^

.. java:method:: public static boolean isMac()
   :outertype: CrossPlatformCommand

   Check if the current OS is MacOS.

   :return: true if macOS and false otherwise

isSolaris
^^^^^^^^^

.. java:method:: public static boolean isSolaris()
   :outertype: CrossPlatformCommand

   Check if the current OS is Solaris.

   :return: true if solaris and false otherwise

isUnix
^^^^^^

.. java:method:: public static boolean isUnix()
   :outertype: CrossPlatformCommand

   Check if the current OS is some sort of unix.

   :return: true if unix and false otherwise

isWindows
^^^^^^^^^

.. java:method:: public static boolean isWindows()
   :outertype: CrossPlatformCommand

   Check if the current OS is windows.

   :return: true if windows and false otherwise

onMac
^^^^^

.. java:method:: protected T onMac()
   :outertype: CrossPlatformCommand

   What to execute on macOS.

   :return: the result

onSolaris
^^^^^^^^^

.. java:method:: protected T onSolaris()
   :outertype: CrossPlatformCommand

   What to execute on solaris.

   :return: the result

onUnix
^^^^^^

.. java:method:: protected abstract T onUnix()
   :outertype: CrossPlatformCommand

   What to execute on windows.

   :return: the result

onWindows
^^^^^^^^^

.. java:method:: protected abstract T onWindows()
   :outertype: CrossPlatformCommand

   What to execute on windows.

   :return: the result

setOS
^^^^^

.. java:method:: static void setOS(String newOs)
   :outertype: CrossPlatformCommand

