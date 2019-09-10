package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.ast.Section;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockMacroProcessor;
import org.asciidoctor.extension.Name;

import java.util.Map;


@Name("tof")
public class TofBlockMacroProcessor extends BlockMacroProcessor {
    @Override
    public Object process(StructuralNode parent, String target, Map<String, Object> attributes) {


        Boolean isNumbered = false;

        try {
            String bool = parent.getDocument().getAttributes().get("tof-numbered").toString();
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


        //neue Einträge an Parent, nicht in Section, die an Parent eingehangen wurde
        Section section = createSection(parent, isNumbered, parent.getDocument().getOptions());
        section.setId("_tofSection");

        try {
            section.setTitle(parent.getDocument().getAttributes().get("tof-title").toString());
        } catch (NullPointerException e) {
        }


        parent.append(section);
        return null;
    }
}

