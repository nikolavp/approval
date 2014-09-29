.. toctree::
   :maxdepth: 1

===========
Adapters
===========

We provide many adapters for external libraries that you can use in the verification process. The adapters for a particular framework are represented as submodules in our codebase so you will need to add them explicitly as dependencies.

Sesame
=============================
`Sesame <http://openrdf.callimachus.net/>`_ is one of the frameworks that we support out of the box. The integration allows you to verify :java:ref:`Graph` objects through graphviz.

1. Ok so first you will need the dot binary otherwise our reporter will fail. Go to the `Graphviz <http://www.graphviz.org/>`_, download and install graphviz.
2. Add the dependency in maven:

.. code-block:: xml

    <dependencies>
        <dependency>
            <groupId>com.github.nikolavp</groupId>
            <artifactId>approval-sesame</artifactId>
            <version>${approval.version}</version>
        </dependency>
    </dependencies>

3. Now you will be able to approve graph objects with the following:

.. literalinclude:: /../../approval-sesame/src/test/java/com/github/approval/sesame/GraphApprovalExample.java
    :language: java
    :lines: 14-24

