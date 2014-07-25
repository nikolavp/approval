Approval test
============
There is an older implementation of the approval tests idea at https://github.com/approvals/ApprovalTests.Java/tree/master/java/org/approvaltests/reporters. Here is a list of things that are still missing in our current implementation and how they map to it:

* UserReporter
    You are better of using a custom approval object and that uses the reporter you want

* QuietReporter
    Use the console reporter it will be a little more verbose but you will be able to see the differences in the console.

* MultiReporter
    Not sure what the usecase for this reporter is

* JunitReporter
    Not sure what the usecase for this reporter is

* ExecutableQueryFailure
  Think of a better way or get their implementation

Things we will implement and are still missing
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
* NotePadLancher reporter - this should be a one liner
* ImageWebReporter reporter - we should bring support for web reporter abstraction
* ImageReporter reporter - we should have this as a FirstWorkingReporter
* DelayedClipboardReporter or ClipboardReporter - those look almost the same
* FileLaunherReporter - launch the file. This should use the windows and unix proper commands
  
  'if (File.separatorChar == '\\')
    {
      cmd = "cmd /C start \"Needed Title\" \"%s\" /B";
    }
    else
    {
      cmd = "open %s";
    }'

    use xdg-open on Linux

All reporters from https://github.com/approvals/ApprovalTests.Java/tree/master/java/org/approvaltests/reporters/macosx
All reporters from https://github.com/approvals/ApprovalTests.Java/tree/master/java/org/approvaltests/reporters/windows
