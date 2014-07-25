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
            <artifactId>approval</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>


.. note::
    Currently there are no stable version but we plan to release our first release soon, stay tuned :)

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
This is the main starting point of the library. If you want to just approve a primitive object or arrays of primitive object then start here. The following will start the approval process for a ``String`` that ``MyCoolThing`` (our class under test) generated and use ``src/test/resources/approval/string.verified`` for recording/saving the results:

.. code-block:: java

    @Test
    public void myApprovals() {
        String string = MyCoolThing.getComplexMultilineString();
        Approvals.verify(string, Paths.get('src/resources/approval/string.verified');
    }

.. _gs-approval:

Approval class
================= 
This is the main object for starting the ``approval`` process. Basically it is used like this:

.. code-block:: java

    @Test
    public void myApprovals() {
        String string = MyCoolThing.getComplexMultilineString();
        Approval<String> approver = Approval.of(String.class)
                .withReporter(Reporters.console())
                .build();
        approver.verify(string, Paths.get('src/resources/approval/string.verified');
    }


note how this is different from :ref:`gs-approvals` - we are building a custom ``Approval`` object which allows us to control and change the whole approval process. Look at :ref:`gs-reporter` and :ref:`gs-converter` for more info.

.. note::
    Approval object are thread safe so you are allowed to declare them as static variables and reuse them in all your tests. In the example above if we have more testing methods we can only declare the ``Approval`` object once as a static variable in the *Test* class

.. _gs-reporter:

Reporter class
==============
Reporters(in lack of better name) are used to prompt the user for approving the result that was given to the :java:ref:`Approval` object. There is a :java:ref:`withReporter` method on :java:ref:`ApprovalBuilder` that allows you to use a custom reporter. We provide some ready to use reporters in the ``Reporters`` class:

* :java:ref:`console` - this uses :program:`cat` and :program:`diff` to report the first result or the differences on the console
* :java:ref:`gvim` - this uses :program:`gvim` and :program:`gvimdiff` to report the first result or the differences in gvim(our favourite editor)
* :java:ref:`gedit` - this uses :program:`gedit` to report the first result. Sadly on differences it just opens two tabs :(

.. _gs-converter:

Converter
=========
Converters are objects that are responsible for serializing objects to raw form(currently byte[]). This interface allows you to create a custom converter for your custom objects and reuse the approval process in the library. We have converters for all primitive types, String and their array variants. Of course providing a converter for your custom object is dead easy. Let's say you have a custom entity model class that you are going to use for verifications in your tests:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/Entity.java
    :language: java

Here is a possible simple converter for the class:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/EntityConverter.java
    :language: java


now let's say we execute a simple test

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/EntityConverterExample.java
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

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/PathMappersExample.java

now if you want to add another approval test you will need to write the same destination directory for the approval path again. You can of course write a private static method that does the mapping for you but we can do better with PathMappers:

.. literalinclude:: /../../src/test/java/com/nikolavp/approval/example/PathMappersExample.java

we abstracted the common parent directory with the help of the :java:ref:`ParentPathMapper` class. We provide other path mapper as part of the library that you can use:

* :java:ref:`JunitPathMapper`

.. _gs-limitations:

Limitations
===========
Some things that you have to keep in mind when using the library:

* unordered objects like *HashSet*, *HashMap* cannot be determisticly verified because their representation will vary from run to run.
