package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.asciidoctor.jruby.AsciidoctorJRuby.Factory.create;
import static org.hamcrest.CoreMatchers.is;

public class TofBlockMacroProcessorTest {
    @Test
    public void givenTofMacroThenSectionShouldBeAppendedTest() {
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<>();
        File file = new File("src/test/docs/givenTofMacroThenSectionShouldBeAppendedTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        iterateDocumentToFindTofSection(doc);
    }

    private void iterateDocumentToFindTofSection(StructuralNode block) {
        List<StructuralNode> blocks = block.getBlocks();
        for (final StructuralNode currentBlock : blocks) {
            if ("_tofSection".equals(currentBlock.getId())) {
                Assert.assertThat("Abbildungsverzeichnis".equals(currentBlock.getTitle()), is(true));
                break;
            } else {
                iterateDocumentToFindTofSection(currentBlock);
            }
        }
    }
}
