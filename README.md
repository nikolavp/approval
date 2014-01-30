ApprovalTests
==================
Capturing Human Intelligence - ApprovalTests is an open source assertion/verification library to aid unit testing. The current project is using ideas from [ApprovalTests] but should provide a more up to date java feel to it.

What can it be used for?
---

Approval Tests can be used for verifing objects that require more than a simple assert. The idea is that you sometimes just want to verify a particular result at the end and then start implementation refactoring. Usecases for this might be:


- performance improvements to the implementation while preserving the current system output
- just verifying RESTful response results, be it JSON, XML, HTML whatever
- people use it for TDD but instead of providing the result upfront you just implement the simple possible thing, verify the result and then start improving the implementation.

Dependencies
---
Currently there are no dependencies for this project. Although at some point I might add guava because programming in java without it is such a pain.

## LICENSE
[Apache 2.0 License](https://github.com/SignalR/SignalR/blob/master/LICENSE.md)


Questions?
---

twitter: [@nikolavp](https://twitter.com/#!/nikolavp)

Issues?
---
You can just write a bug report or even better send a pull request with a patch. Patches are gladly accepted.
