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
import static org.hamcrest.MatcherAssert.assertThat;

public class ToftotTreeProcessorTest {

    @Test
    public void givenFiguresInDocThenMapShouldContainValuesTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenFiguresInDocThenMapShouldContainValuesTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        treeProcessor.process(doc);
        assertThat(treeProcessor.figureMap.get(1).equals("Middle Office Service Partner Startseite1"), is(true));
        Assert.assertNull(treeProcessor.figureMap.get(2));
        assertThat(treeProcessor.figureMap.size(), is(2));
    }

    @Test
    public void givenTablesInDocThenMapShouldContainValuesTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenTablesinDocThenMapShouldContainValuesTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        treeProcessor.process(doc);
        assertThat(treeProcessor.tableMap.get(1).equals("Standard Vorgangssuche1"), is(true));
        Assert.assertNull(treeProcessor.tableMap.get(2));
        assertThat(treeProcessor.tableMap.size(), is(2));
    }

    @Test
    public void givenBlockAndInlineMacroThenOnlyShouldListBlockMacroTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenBlockAndInlineMacroThenOnlyShouldListBlockMacroTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        treeProcessor.process(doc);
        assertThat(treeProcessor.figureMap.size(), is(1));
        assertThat(treeProcessor.figureMap.get(1).toString().equals("Middle Office Service Partner Startseite1"), is(true));
    }

    @Test
    public void givenNoImagesOrTablesThenMapShouldBeEmptyTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenNoImagesOrTablesThenMapShouldBeEmptyTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        treeProcessor.process(doc);
        assertThat(treeProcessor.figureMap.size(), is(0));
        assertThat(treeProcessor.tableMap.size(), is(0));
    }

    @Test
    public void givenTofSectionInDocThenSectionShouldHaveValuesTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenTofSectionInDocThenSectionShouldHaveValuesTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);
        String[] titleArray = new String[2];
        String content = "<div class=\"paragraph\">\n" +
                "<p><a anchor=\"_tof00001\">Abbildung 1: Middle Office Service Partner Startseite1</a><br>\n" +
                "<a anchor=\"_tof00002\">Abbildung 2: Middle Office Service Partner Startseite2</a><br></p>\n" +
                "</div>";
        sectionInhaltTest(doc, "_tofSection", content);
    }

    @Test
    public void givenTotSectionInDocThenSectionShouldHaveValuesTest() {
        ToftotTreeProcessor treeProcessor = new ToftotTreeProcessor();
        Asciidoctor asciidoctor = create();
        Map<String, Object> map = new HashMap<String, Object>();
        File file = new File("src/test/docs/givenTotSectionInDocThenSectionShouldHaveValuesTest.adoc");
        Document doc = asciidoctor.loadFile(file, map);

        String content = "<div class=\"paragraph\">\n" +
                "<p><a anchor=\"_tot00001\">Tabelle 1: Standard Vorgangssuche1</a><br>\n" +
                "<a anchor=\"_tot00002\">Tabelle 2: Standard Vorgangssuche2</a><br></p>\n" +
                "</div>";
        sectionInhaltTest(doc, "_totSection", content);
    }

    public void sectionInhaltTest(StructuralNode block, String sectId, String inhalt) {
        List<StructuralNode> blocks = block.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            final StructuralNode currentBlock = blocks.get(i);
            if (sectId.equals(currentBlock.getId())) {
                String proof = currentBlock.getContent().toString();
                assertThat(proof.equals(inhalt), is(true));
            } else {
                sectionInhaltTest(currentBlock, sectId, inhalt);
            }
        }
    }
}
