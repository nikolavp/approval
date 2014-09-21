.. _faq:


###
FAQ
###

Is there a way to delete orphaned(not used) approval files?
#####################################################################################
Yes! Each time we do the approval process, the last modified date will be set to current time for you. This allows you to delete the files with something like the following in a shell(bash/cygwin/zshell)


.. code-block:: bash

    find src/test/resources/approval -type f -mtime +7 -exec rm -i {} \;

this will all files that are located in src/test/resources/approval and were not modified in the last 7 days. The _-i_ flag on :program:`rm` will make the whole process interactive.

I am getting Illegal state exteption with "<myclass> is not a primitive type class!"?
#####################################################################################
This means that you are trying to create/use an :java:ref:`Approval` object that's for a non primitive type and you haven't specified a :ref:`gs-converter`


Can I use the library in android?
#################################
No. Sadly the android framework is only `stubbed <http://stackoverflow.com/questions/8982631/error-java-lang-runtimeexception-stub-in-android-with-fitnesse-testing>`_ while you are writing/compiling your code. The only way you can run the tests is on the device/emulator which doesn't allow us to use the reporters properly.
