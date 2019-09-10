package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

public class ToftotExtension implements ExtensionRegistry {

    @Override
    public void register(Asciidoctor asciidoctor) {
        JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();
        javaExtensionRegistry.treeprocessor(ToftotTreeProcessor.class);
        javaExtensionRegistry.blockMacro(TotBlockMacroProcessor.class);
        javaExtensionRegistry.blockMacro(TofBlockMacroProcessor.class);
    }
}

