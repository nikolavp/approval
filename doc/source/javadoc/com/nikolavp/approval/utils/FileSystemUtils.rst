.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file Path

FileSystemUtils
===============

.. java:package:: com.nikolavp.approval.utils
   :noindex:

.. java:type:: public interface FileSystemUtils

   This class is mostly used for indirection in the tests. We just don't like static utility classes. Created by ontotext on 2/2/14.

Methods
-------
createDirectories
^^^^^^^^^^^^^^^^^

.. java:method::  void createDirectories(File directory) throws IOException
   :outertype: FileSystemUtils

   Create a directory and their parent directories as needed.

   :param directory: the directory to create
   :throws IOException: if there was an error while creating the directories

move
^^^^

.. java:method::  void move(Path path, Path filePath) throws IOException
   :outertype: FileSystemUtils

   Move a path to another path.

   :param path: the source
   :param filePath: the destination
   :throws IOException: if there was an error while moving the paths

readFully
^^^^^^^^^

.. java:method::  byte[] readFully(Path path) throws IOException
   :outertype: FileSystemUtils

   Read the specified path as byte array.

   :param path: the path to read
   :throws IOException: if there was an error while reading the content
   :return: the path content

touch
^^^^^

.. java:method::  void touch(Path pathToCreate) throws IOException
   :outertype: FileSystemUtils

   Creates the specified path with empty content.

   :param pathToCreate: the path to create
   :throws IOException: if there was error while creating the path

write
^^^^^

.. java:method::  void write(Path path, byte[] value) throws IOException
   :outertype: FileSystemUtils

   Write the byte value to the specified path.

   :param path: the path
   :param value: the value
   :throws IOException: if there was an error while writing the content

