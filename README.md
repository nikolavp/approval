ApprovalTests
==================
Capturing Human Intelligence - ApprovalTests is an open source assertion/verification library to aid unit testing. The current project is using ideas from [ApprovalTests] but should provide a more up to date java feel to it.

What can it be used for?
---

Approval Tests can be used for verifing objects that require more than a simple assert. The idea is that you sometimes just want to verify a particular result at the end and then start implementation refactoring. Usecases for this might be:


- performance improvements to the implementation while preserving the current system output
- just verifying RESTful response results, be it JSON, XML, HTML whatever
- people use it for TDD but instead of providing the result upfront you just implement the simple possible thing, verify the result and then start improving the implementation.



[Video Tutorials](http://www.youtube.com/playlist?list=PLFBA98F47156EFAA9&feature=view_all)
---

You can watch a bunch of short videos on getting started and [using ApprovalTests in Java](http://www.youtube.com/playlist?list=PLFBA98F47156EFAA9&feature=view_all) at youtube

Podcasts
---
If you prefer auditory learning, you might enjoy the following podcast (Note: Some of these talk about the .net side)

- [Hanselminutes] (http://www.hanselminutes.com/360/approval-tests-with-llewellyn-falco)
- [Herding Code](http://www.developerfusion.com/media/122649/herding-code-117-llewellyn-falcon-on-approval-tests/)
- [The Watir Podcast](http://watirpodcast.com/podcast-53/)

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
