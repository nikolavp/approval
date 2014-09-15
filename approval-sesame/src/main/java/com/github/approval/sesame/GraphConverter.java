package com.github.approval.sesame;

/*
 * #%L
 * approval-sesame
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.approval.converters.AbstractStringConverter;
import org.openrdf.model.Graph;
import org.openrdf.model.Statement;

import javax.annotation.Nonnull;

/**
 * <p>
 * A converter that accepts a sesame graph object and will return it's "dot" directed graph format.
 * <p/>
 * <p>
 * You most probably want to use this in cojection with {@link com.github.approval.sesame.GraphReporter}.
 * </p>
 */
public class GraphConverter extends AbstractStringConverter<Graph> {
    @Nonnull
    @Override
    protected String getStringForm(Graph value) {
        StringBuilder statementsInDotFormat = new StringBuilder();
        for (Statement statement : value) {
            statementsInDotFormat.append(
                    String.format("\t\"<%s>\" -> \"<%s>\" [label=\"%s\"];%n",
                            statement.getSubject().stringValue(),
                            statement.getObject().stringValue(),
                            statement.getPredicate().stringValue()
                    )
            );
        }

        return String.format("digraph graphName {%n%s}%n", statementsInDotFormat);
    }
}
