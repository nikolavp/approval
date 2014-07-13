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
            <groupId>com.nikolavp</groupId>
            <artifactId>approval</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>


.. note::

    To be able use the library you will have to build it yourself until there are artifacts in maven central.

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

