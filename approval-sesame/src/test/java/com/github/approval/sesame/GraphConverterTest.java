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


import com.github.approval.Approvals;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openrdf.model.Graph;
import org.openrdf.model.Statement;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.TreeModel;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class GraphConverterTest {

    @Test
    public void shouldProperlyConvertGraphToGraphVizModel() throws Exception {
        ValueFactory v = new ValueFactoryImpl();

        Graph graph = new TreeModel();
        graph.add(v.createStatement(
                v.createURI("http://test.urn"),
                v.createURI("http://predicate"),
                v.createLiteral("Test label")
        ));

        graph.add(v.createStatement(
                v.createURI("http://test.urn1"),
                v.createURI("http://predicate1"),
                v.createLiteral("Test label1")
        ));

        String stringForm = new GraphConverter().getStringForm(graph);

        Approvals.verify(stringForm, Paths.get("src/test/resources/approvals/shouldProperlyConvertGraphToGraphVizModel.dot"));
    }
}
