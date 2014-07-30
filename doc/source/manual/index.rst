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

now this class is not rocket science and if we want to test **getResult()**, we would write something like the following in JUnit:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/SimpleExampleTest.java
    :language: java

this is quite terse and short. Can we do better? Actually because we support strings out of the box, approval is a lot shorter


.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/SimpleExampleApprovalTest.java
    :language: java

when the latter is executed you will be prompted in your tool of choice to verify the result from **getResult()**. Verifying the result will vary from your tool of choice because some of them allow you to control the resulting file and others just show you what was the verification object.

To see it in action we will look at two possible reporters:

Gedit
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Gedit is just a simple editor. When we run the test it will show us the string representation:

.. image:: images/gedit-example.png

as you can see this is the string representation of the result opened in gedit. If we close gedit we will prompted by a confirm window which will ask us if we approve the result or it is not OK. On not OK the test will fail with an :java:ref:`AssertionError` and otherwise the test will pass and will continue to pass until the returned value from **getResult()** changes.

GvimDiff
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Gvimdiff is much more powerful than gedit. If we decide to use it then we got the power in our hands and we can decide if we want the file or not(there will be no confirmation window). Here is how it looks like:

.. image:: images/gvimdiff-example.png

as you can see on the left side is the result from the test run and on the right side is what will be written for consecutive test runs. If we are ok with the result we can get everything from the left side, save the right side and exit vim. The test will now pass and will continue to pass until the returned value from **getResult()** changes.


Let's say someone changes the code and it no longer contains a DOCTYPE declaration. The reporter will fire up and we will get the following window:

.. image:: images/gvimdiff-changes-example.png

we can approve the change or exit our tool and mark the result as invalid.
