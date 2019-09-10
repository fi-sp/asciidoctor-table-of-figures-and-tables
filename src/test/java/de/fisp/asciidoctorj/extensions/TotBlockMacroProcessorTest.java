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

public class TotBlockMacroProcessorTest {
    @Test
    public void givenTotMacroThenSectionShouldBeAppendedTest() {
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenTotMacroThenSectionShouldBeAppendedTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        iterateDocumentToFindTotSection((StructuralNode) doc);
    }

    private void iterateDocumentToFindTotSection(StructuralNode block) {
        List<StructuralNode> blocks = block.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            final StructuralNode currentBlock = blocks.get(i);
            if ("_totSection".equals(currentBlock.getId())) {
                Assert.assertThat("Tabellenverzeichnis".equals(currentBlock.getTitle()), is(true));
                break;
            } else {
                iterateDocumentToFindTotSection(currentBlock);
            }
        }
    }
}
