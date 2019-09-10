package de.fisp.asciidoctorj.extensions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ToftotExtensionTest {

    @Test
    public void registerExtensionClassesToAsciidocExtensionRegistryInstanceTest() {
        AsciidocInterfaceTest ascInterTest = new AsciidocInterfaceTest();
        ToftotExtension toftotExtension = new ToftotExtension();
        toftotExtension.register(ascInterTest);

        Boolean toftotTree = false;
        Boolean tofBlock = false;
        Boolean totBlock = false;
        Boolean valid = false;

        for (int i = 0; i < ascInterTest.processorList.size(); i++) {
            if (ascInterTest.processorList.get(i).equals("de.fisp.asciidoctorj.extensions.ToftotTreeProcessor")) {
                toftotTree = true;
            } else if (ascInterTest.processorList.get(i).equals("de.fisp.asciidoctorj.extensions.TofBlockMacroProcessor")) {
                tofBlock = true;
            } else if (ascInterTest.processorList.get(i).equals("de.fisp.asciidoctorj.extensions.TotBlockMacroProcessor")) {
                totBlock = true;
            }
        }
        if (toftotTree == true && tofBlock == true && totBlock == true) {
            valid = true;
        }
        assertThat(valid.toString(), is("true"));
    }

}
