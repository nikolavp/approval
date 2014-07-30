package com.nikolavp.approval.example;

import com.nikolavp.approval.Approval;
import com.nikolavp.approval.reporters.Reporters;
import org.junit.Test;

import java.nio.file.Paths;

public class EntityConverterExample {
    @Test
    public void customEntityTest() {
        Entity entity = new Entity("Nikola", 30);
        Approval<Entity> approver = Approval.of(Entity.class)
                .withReporter(Reporters.console())
                .withConveter(new EntityConverter())
                .build();
        approver.verify(entity, Paths.get("src/test/resources/approval/example/entity.verified"));
    }
}
