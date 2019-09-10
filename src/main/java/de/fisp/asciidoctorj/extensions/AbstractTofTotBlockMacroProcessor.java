package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.ast.Section;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockMacroProcessor;

import java.util.Map;


public class AbstractTofTotBlockMacroProcessor extends BlockMacroProcessor {

    private final String totNumberedAttribute;
    private final String totSection;
    private final String totTitleAttribute;

    AbstractTofTotBlockMacroProcessor(String totSection, String totNumberedAttribute, String totTitleAttribute) {
        this.totSection = totSection;
        this.totNumberedAttribute = totNumberedAttribute;
        this.totTitleAttribute = totTitleAttribute;
    }

    @Override
    public Object process(StructuralNode parent, String target, Map<String, Object> attributes) {

        Object totNumbered = parent.getDocument().getAttributes().get(totNumberedAttribute);
        boolean isNumbered = totNumbered != null && Boolean.parseBoolean(totNumbered.toString());

        if ("preamble".equals(parent.getContext())) {
            isNumbered = false;
        }

        Section section = createSection(parent, isNumbered, parent.getDocument().getOptions());
        section.setId(totSection);

        Object totTitle = parent.getDocument().getAttributes().get(totTitleAttribute);
        if (totTitle != null) {
            section.setTitle(totTitle.toString());
        }

        parent.append(section);
        return null;
    }
}
