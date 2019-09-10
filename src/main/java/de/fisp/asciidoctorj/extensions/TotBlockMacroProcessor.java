package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.ast.Section;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockMacroProcessor;
import org.asciidoctor.extension.Name;

import java.util.Map;

@Name("tot")
public class TotBlockMacroProcessor extends BlockMacroProcessor {
    @Override
    public Object process(StructuralNode parent, String target, Map<String, Object> attributes) {
        Boolean isNumbered = false;

        try {
            String bool = parent.getDocument().getAttributes().get("tot-numbered").toString();
            if (bool.equals("true")) {
                isNumbered = true;
            } else if (bool.equals("false")) {
                isNumbered = false;
            }
        } catch (NullPointerException e) {
        }


        if ("preamble".equals(parent.getContext())) {
            isNumbered = false;
        }


        Section section = createSection(parent, isNumbered, parent.getDocument().getOptions());
        section.setId("_totSection");
        try {
            section.setTitle(parent.getDocument().getAttributes().get("tot-title").toString());
        } catch (NullPointerException e) {
        }

        parent.append(section);
        return null;
    }
}
