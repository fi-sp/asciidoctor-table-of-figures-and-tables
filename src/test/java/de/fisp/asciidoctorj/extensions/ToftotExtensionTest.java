package de.fisp.asciidoctorj.extensions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ToftotExtensionTest {

    @Test
    public void registerExtensionClassesToAsciidocExtensionRegistryInstanceTest() {
        TestAsciidocInterface ascInterTest = new TestAsciidocInterface();
        ToftotExtension toftotExtension = new ToftotExtension();
        toftotExtension.register(ascInterTest);

        boolean toftotTree = false;
        boolean tofBlock = false;
        boolean totBlock = false;

        for (int i = 0; i < ascInterTest.processorList.size(); i++) {
            switch (ascInterTest.processorList.get(i)) {
                case "de.fisp.asciidoctorj.extensions.ToftotTreeProcessor":
                    toftotTree = true;
                    break;
                case "de.fisp.asciidoctorj.extensions.TofBlockMacroProcessor":
                    tofBlock = true;
                    break;
                case "de.fisp.asciidoctorj.extensions.TotBlockMacroProcessor":
                    totBlock = true;
                    break;
                default:
                    throw new IllegalStateException(String.format("Unbekannter Prozessor %s registriert", ascInterTest.processorList.get(i)));
            }
        }

        assertThat(toftotTree, is(true));
        assertThat(tofBlock, is(true));
        assertThat(totBlock, is(true));
    }

}
