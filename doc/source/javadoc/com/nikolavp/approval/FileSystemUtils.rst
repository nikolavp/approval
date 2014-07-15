.. java:import:: java.io File

.. java:import:: java.io IOException

.. java:import:: java.nio.file Path

FileSystemUtils
===============

.. java:package:: com.nikolavp.approval
   :noindex:

.. java:type::  interface FileSystemUtils

   This class is mostly used for indirection in the tests. We just don't like static utility classes. Created by ontotext on 2/2/14.

Methods
-------
createDirectories
^^^^^^^^^^^^^^^^^

.. java:method::  void createDirectories(File parentPathDirectory) throws IOException
   :outertype: FileSystemUtils

move
^^^^

.. java:method::  void move(Path path, Path filePath) throws IOException
   :outertype: FileSystemUtils

readFully
^^^^^^^^^

.. java:method::  byte[] readFully(Path path) throws IOException
   :outertype: FileSystemUtils

write
^^^^^

.. java:method::  void write(Path path, byte[] value) throws IOException
   :outertype: FileSystemUtils

