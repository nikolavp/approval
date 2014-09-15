package com.github.approval.sesame;

import com.github.approval.Approval;
import org.junit.Test;
import org.openrdf.model.Graph;
import org.openrdf.model.impl.TreeModel;

import java.nio.file.Paths;

public class GraphApprovalExample {
    @Test
    public void graphVerificationExample() {

        Graph graph = new TreeModel();
        // populate our graph with statements(maybe from GraphQueryResult?

        // Note: this is still thread safe...
        Approval<Graph> graphApproval = Approval.of(Graph.class)
                .withConveter(new GraphConverter())
                .withReporter(GraphReporter.getInstance())
                .build();

        // Verify the graph, change the path accordingly
        graphApproval.verify(graph, Paths.get("graph-result.dot"));
    }
}
