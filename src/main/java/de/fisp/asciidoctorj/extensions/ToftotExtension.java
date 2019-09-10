package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

public class ToftotExtension implements ExtensionRegistry { // (1)

    @Override
    public void register(Asciidoctor asciidoctor) { // (2)
        JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();// (3)
        javaExtensionRegistry.treeprocessor(ToftotTreeProcessor.class);
        javaExtensionRegistry.blockMacro(TotBlockMacroProcessor.class);
        javaExtensionRegistry.blockMacro(TofBlockMacroProcessor.class);
    }
}

