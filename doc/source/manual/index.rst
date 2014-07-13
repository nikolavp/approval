.. toctree::
   :maxdepth: 1

===========
User Manual
===========

Simple example of the library
=============================


Let's try to test the simplest example possible:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/SimpleExample.java
    :language: java

now this class is not rocket science and if we want to test getResult(), we would something like the following in JUnit:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/SimpleExampleTest.java
    :language: java

this is quite terse and short. Can we do better? Actually approval is not any shorter:


.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/SimpleExampleApprovalTest.java
    :language: java

this is not the best example
