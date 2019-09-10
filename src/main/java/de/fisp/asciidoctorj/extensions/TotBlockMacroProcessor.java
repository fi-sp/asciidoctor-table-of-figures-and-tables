package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.extension.Name;

@Name("tot")
public class TotBlockMacroProcessor extends AbstractTofTotBlockMacroProcessor {
    public TotBlockMacroProcessor() {
        super("_totSection", "tot-numbered", "tot-title");
    }
}
