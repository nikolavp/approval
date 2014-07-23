package com.nikolavp.approval.pathlocators;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: nikolavp (Nikola Petrov) Date: 14-7-23 Time: 14:44
 */
public class JunitPathLocatorTest {

    public static final Path PARENT_PATH = Paths.get("src/main/resources");

    @Rule
    public JunitPathLocator pathLocator = new JunitPathLocator(PARENT_PATH);

    private static final Path pathForClass = PARENT_PATH.resolve(Paths.get("com", "nikolavp", "approval", "pathlocators", "JunitPathLocatorTest"));

    @Test
    public void shouldProperlyResolvePathBasedOnTest() throws Exception {
        final Path currentTestPath = pathLocator.getCurrentTestPath();

        final Path expected = pathForClass.resolve("shouldProperlyResolvePathBasedOnTest");

        Assert.assertThat(currentTestPath, CoreMatchers.equalTo(expected));
    }

    @Test
    public void shouldProperlyResolvePathBasedOnTestOther() throws Exception {
        final Path currentTestPath = pathLocator.getCurrentTestPath();

        final Path expected = pathForClass.resolve("shouldProperlyResolvePathBasedOnTestOther");

        Assert.assertThat(currentTestPath, CoreMatchers.equalTo(expected));
    }

    @Test
    public void shouldReturnProperPathWhenUsedAsFullPathLocator() throws Exception {
        final Path path = pathLocator.getApprovalPath("test");

        Assert.assertThat(path, CoreMatchers.equalTo(pathForClass.resolve("shouldReturnProperPathWhenUsedAsFullPathLocator.approved")));
    }

    @Test
    public void shouldReturnProperPathWhenUsedAsPathLocator() throws Exception {
        final Path path = pathLocator.getPath("Some cool value", Paths.get("subpath"));

        Assert.assertThat(path, CoreMatchers.equalTo(pathForClass.resolve(Paths.get("shouldReturnProperPathWhenUsedAsPathLocator", "subpath"))));
    }
}

