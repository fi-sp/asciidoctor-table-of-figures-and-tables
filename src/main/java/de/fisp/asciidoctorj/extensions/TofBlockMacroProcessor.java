package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.extension.Name;


@Name("tof")
public class TofBlockMacroProcessor extends AbstractTofTotBlockMacroProcessor {

    public TofBlockMacroProcessor() {
        super("_tofSection", "tof-numbered", "tof-title");
    }
}

