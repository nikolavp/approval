.. approval documentation master file, created by
   sphinx-quickstart on Sat Jul 12 20:09:47 2014.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

######################################################################################################
Approval is a Java library which will make you look at your testing from a whole different angle
######################################################################################################

**Approval** provides a powerful toolkit of ways to test the behavior of critical components so you can prevent problems **in your production environment.**


What can it be used for?
========================

Approval can be used for verifying objects that require more than a simple assert. The idea is that you sometimes just want to verify a particular result at the end and then start implementation refactoring. I like to call it "I will know the right result when I see it". Usecases for this might be:

- performance improvements to the implementation while preserving the current system output
- just verifying RESTful response results, be it JSON, XML, HTML whatever
- people use it for TDD but instead of providing the result upfront you just implement the simple possible thing, verify the result and then start improving the implementation.


.. toctree::
   :maxdepth: 1

   getting-started
   manual/index
   manual/adapters
   faq
   javadoc/packages
