Approval [![Build Status](https://secure.travis-ci.org/nikolavp/approval.png)](http://travis-ci.org/nikolavp/approval) [![Coverage Status](https://coveralls.io/repos/nikolavp/approval/badge.png)](https://coveralls.io/r/nikolavp/approval) [![Documentation Status](https://readthedocs.org/projects/approval/badge/?version=latest)](https://readthedocs.org/projects/approval/?badge=latest) [![Gitter chat](https://badges.gitter.im/nikolavp/approval.png)](https://gitter.im/nikolavp/approval)
=======
Approval is an open source assertion/verification library to aid unit testing.

How is approval testing different
=================================

There are many sources from which you can learn about approval testing(just google it) but basically the process is the following:

1. you already have a working implementation of the thing you want to test
2. you run it and get the result the first time
3. the result will be shown to you in your preferred tool(this can be configured)
4. you either approve the result in which case it is recored(saved) and the test pass or you disapprove it in which case the test fails
5. the recorded result is then used on further test runs to make sure that there are no regressions in your code(i.e. you broke something and the result is not the same).
6. Of course sometimes you want to change the way something behaves so if the result is not the same we will prompt you with difference between the new result and the last recorded again in your preferred tool.

Want to learn more? See **[our latest documentation](http://approval.readthedocs.org/en/latest/)**.

The current project is using ideas from [ApprovalTests](https://github.com/approvals/ApprovalTests.Java) but should provide a more up to date java feel to it.

Dependencies
---
There are currently no dependencies for the project

## LICENSE
[Apache 2.0 License](https://github.com/SignalR/SignalR/blob/master/LICENSE.md)


Questions?
---

You can use the gitter chat [![Gitter chat](https://badges.gitter.im/nikolavp/approval.png)](https://gitter.im/nikolavp/approval)

Issues?
---
You can just write a bug report or even better send a pull request with a patch :)


Contributions?
---
You want to help the project go forward. I am always open for additions contributions. There are several things you can do:

Documentation
-----
Documentation is never enough. Wanna write some of it and see how it looks? It is super easy - just install sphinx-autobuild and run:
```
sphinx-autobuild -b html doc/source doc/build/html
```

from the root directory of the project
