.. _getting-started:

###############
Getting Started
###############

.. highlight:: text

.. rubric:: 
    *Getting Started* will guide you through the process of testing your classes with approval testing. Don't worry if you are used to normal testing with assertions you will get up to speed in minutes.

.. _gs-maven:

Setting Up Maven
================

Just add the ``approval`` library as a dependency:

.. code-block:: xml

    <dependencies>
        <dependency>
            <groupId>com.github.nikolavp</groupId>
            <artifactId>approval-core</artifactId>
            <version>${approval.version}</version>
        </dependency>
    </dependencies>


.. warning::
    Make sure you have a ``approval.version`` property declared in your POM with the current version,
    which is |release|.

How is approval testing different
=================================

There are many sources from which you can learn about approval testing(just google it) but basically the process is the following:

#. you already have a working implementation of the thing you want to test
#. you run it and get the result the first time
#. the result will be shown to you in your preferred tool(this can be configured)
#. you either approve the result in which case it is recored(saved) and the test pass or you disapprove it in which case the test fails
#. the recorded result is then used on further test runs to make sure that there are no regressions in your code(i.e. you broke something and the result is not the same).
#. Of course sometimes you want to change the way something behaves so if the result is not the same we will prompt you with difference between the new result and the last recorded again in your preferred tool.

.. _gs-approvals:

Approvals utility
=================
This is the main starting point of the library. If you want to just approve a primitive object or arrays of primitive object then you are ready to go. The following will start the approval process for a ``String`` that ``MyCoolThing`` (our class under test) generated and use ``src/test/resources/approval/string.verified`` for recording/saving the results:


.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/GettingStartedExamples.java
    :language: java
    :lines: 13-17

.. _gs-approval:

Approval class
================= 
This is the main object for starting the ``approval`` process. Basically it is used like this:

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/GettingStartedExamples.java
    :language: java
    :lines: 19-26


note how this is different from :ref:`gs-approvals` - we are building a custom ``Approval`` object which allows us to control and change the whole approval process. Look at :ref:`gs-reporter` and :ref:`gs-converter` for more info.

.. note::
    Approval object are thread safe so you are allowed to declare them as static variables and reuse them in all your tests. In the example above if we have more testing methods we can only declare the ``Approval`` object once as a static variable in the *Test* class

.. _gs-reporter:

Reporter class
==============
Reporters(in lack of better name) are used to prompt the user for approving the result that was given to the :java:ref:`Approval` object. There is a :java:ref:`withReporter` method on :java:ref:`ApprovalBuilder` that allows you to use a custom reporter. We provide some ready to use reporters in the following classes:

* :java:ref:`Reporters` - this factory class contains cross platform programs/reporters. Here you will find things like :java:ref:`gvim`, :java:ref:`console`.
* :java:ref:`WindowsReporters` - this factory class contains Windows programs/reporters. Here you will find things like :java:ref:`notepadPlusPlus`, :java:ref:`beyondCompare`, :java:ref:`tortoiseText`.
* :java:ref:`MacOSReporters` - this factory class contains MacOS programs/reporters. Here you will find things like :java:ref:`diffMerge`, :java:ref:`ksdiff`, etc.

.. note::
    Sadly I am unable to properly test the windows and macOS reporters because I mostly have access to Linux machines. If you find a problem, blame it on me.

.. _gs-converter:

Converter
=========
Converters are objects that are responsible for serializing objects to raw form(currently :java:ref:`byte[]`). This interface allows you to create a custom converter for your custom objects and reuse the approval process in the library. We have converters for all primitive types, String and their array variants. Of course providing a converter for your custom object is dead easy. Let's say you have a custom entity class that you are going to use for verifications in your tests:

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/Entity.java
    :language: java

Here is a possible simple converter for the class:

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/EntityConverter.java
    :language: java


now let's say we execute a simple test

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/EntityConverterExample.java
    :language: java
    :lines: 12-20

we will get the following output in the console(because we are using the console reporter)

    | Entity is:
    | age = 30
    | name = Nikola


.. _gs-pathmappers:

Path Mapper
============
Path mapper are used to abstract the way in which the final path file that contains the verification result is built. You are not required to use them but if you want to add structure to the your approval files you will at some point find the need for them. Let's see an example:

You have the following class containing two verifications:

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/PathMappersExample.java
    :language: java

now if you want to add another approval test you will need to write the same destination directory for the approval path again. You can of course write a private static method that does the mapping for you but we can do better with PathMappers:

.. literalinclude:: /../../approval-core/src/test/java/com/github/approval/example/PathMappersExampleImproved.java
    :language: java

we abstracted the common parent directory with the help of the :java:ref:`ParentPathMapper` class. We provide other path mapper as part of the library that you can use:

* :java:ref:`JunitPathMapper`

.. _gs-limitations:

