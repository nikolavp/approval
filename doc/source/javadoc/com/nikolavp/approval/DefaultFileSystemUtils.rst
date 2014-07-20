.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file Files

.. java:import:: java.nio.file Path

DefaultFileSystemUtils
======================

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type:: public class DefaultFileSystemUtils implements FileSystemUtils

   A default implementation for \ :java:ref:`FileSystemUtils`\ . This one just delegates to methods in \ :java:ref:`Files`\ . User: nikolavp Date: 27/02/14 Time: 12:26

Methods
-------
createDirectories
^^^^^^^^^^^^^^^^^

.. java:method:: @Override public void createDirectories(File directory) throws IOException
   :outertype: DefaultFileSystemUtils

move
^^^^

.. java:method:: @Override public void move(Path path, Path filePath) throws IOException
   :outertype: DefaultFileSystemUtils

readFully
^^^^^^^^^

.. java:method:: @Override public byte[] readFully(Path path) throws IOException
   :outertype: DefaultFileSystemUtils

write
^^^^^

.. java:method:: @Override public void write(Path path, byte[] value) throws IOException
   :outertype: DefaultFileSystemUtils

