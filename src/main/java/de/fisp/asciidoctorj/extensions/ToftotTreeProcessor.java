package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.Treeprocessor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.asciidoctor.jruby.AsciidoctorJRuby.Factory.create;


public class ToftotTreeProcessor extends Treeprocessor {

    Asciidoctor asciidoctor = create();

    public ToftotTreeProcessor() {
    }

    private int tofCounter = 1;
    HashMap<Integer, String> figureMap = new HashMap<Integer, String>();
    private int totCounter = 1;
    HashMap<Integer, String> tableMap = new HashMap<Integer, String>();

    @Override
    public Document process(Document document) {
        processBlock((StructuralNode) document);
        appendTof((StructuralNode) document);
        appendTot((StructuralNode) document);

        return document;
    }

    private void processBlock(StructuralNode block) {
        List<StructuralNode> blocks = block.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            final StructuralNode currentBlock = blocks.get(i);
            if (currentBlock instanceof StructuralNode) {
                if ("image".equals(currentBlock.getContext())) { // (3)
                    currentBlock.setId("_tof0000" + Integer.toString(tofCounter));
                    figureMap.put(tofCounter, currentBlock.getTitle());
                    tofCounter = tofCounter + 1;
                } else if ("table".equals(currentBlock.getContext())) {
                    currentBlock.setId("_tot0000" + Integer.toString(totCounter));
                    tableMap.put(totCounter, currentBlock.getTitle());
                    totCounter = totCounter + 1;
                } else {
                    // It's not a paragraph, so recursively descend into the child node
                    processBlock(currentBlock);
                }
            }
        }
    }

    private List<String> generateFigureVzLines() {
        List<String> figureVzLines = new LinkedList();
        for (int i = 1; i <= figureMap.size(); i++) {
            String line2 = "<a anchor=\"_tof0000" + i + "\">" + "Abbildung " + i + ": " + figureMap.get(i) + "</a>";
            figureVzLines.add(line2 + "<br>");
        }
        return figureVzLines;
    }

    private List<String> generateTableVzLines() {
        List<String> tableVzLines = new LinkedList();
        for (int i = 1; i <= tableMap.size(); i++) {
            String line2 = "<a anchor=\"_tot0000" + i + "\">" + "Tabelle " + i + ": " + tableMap.get(i) + "</a>";
            tableVzLines.add(line2 + "<br>");
        }
        return tableVzLines;
    }

    private void appendTof(StructuralNode block) {
        List<StructuralNode> blocks = block.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            final StructuralNode currentBlock = blocks.get(i);
            if ("_tofSection".equals(currentBlock.getId())) {
                currentBlock.append(createBlock(currentBlock, "paragraph", generateFigureVzLines(), currentBlock.getDocument().getAttributes()));
                //                 //parseContent(currentBlock, generateFigureVzLines());
                break;
            } else {
                appendTof(currentBlock);
            }
        }
    }

    private void appendTot(StructuralNode block) {
        List<StructuralNode> blocks = block.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            final StructuralNode currentBlock = blocks.get(i);
            if ("_totSection".equals(currentBlock.getId())) {
                currentBlock.append(createBlock(currentBlock, "paragraph", generateTableVzLines(), currentBlock.getDocument().getAttributes()));
                break;
            } else {
                appendTot(currentBlock);
            }
        }
    }
}






