package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Preprocessor;
import org.asciidoctor.extension.PreprocessorReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Asciidoctorj Extension - Table of Figures / Table of Tables :tof: / :tot:
Über Setzen der Attribute :tof: bzw. :tot: im Header einer .adoc-Datei wird
am Ende des Dokumentes ein Abbildungsverzeichnis bzw. ein Tabellenverzeichnis generiert.
Hierbei werden Bilder und Tabellen folgender Syntax betrachtet :

Für Figures ( Bilder ) :

.Imagetitel
image::/Pandoc/media/image191.png[image,width=604,height=171]


Für Tables ( Tabellen ) :

.Tabellenname
[cols=",",options="header",]
|===
|Bereich / Feld / Schaltfläche |Beschreibung
|image:/Pandoc/media/image192.png[image,width=172,height=62] |Öffnet einen Upload Dialog für eine CSV Datei
|image:/Pandoc/media/image193.png[image,width=93,height=41] |Sendet die hochgeladene Händlerimportdatei ab
|image:/Pandoc/media/image194.png[image,width=66,height=39] |Abbruch der Aktion und zurück zur vorigen Seite
|===


Funktionsweise:
Durchläuft gesamtes Dokument Zeile für Zeile
Wenn eine Line mit "." beginnt, wird geprüft, ob die Überschrift zu einem Bild oder einer Tabelle gehört. Ist dies der Fall,
wird das Bild/die Tabelle in das jewilige Verzeichnis aufgenommen und ein Anker im Dokument gesetzt.
Abschließend werden die Verzeichnisse ans Ende des Dokumentes angehangen, sofern die Attribute :tof: bzw :tot: gesetzt sind
 */

public class ToftotPreProcessor extends Preprocessor {

    // Counter um die Anker für das jewilige Verzeichnis zu erstellen

    int figureCounter = 1;
    int tableCounter = 1;

    //Namen der Verzeichnisse, falls im Dokumenten-Header :tof-title: bzw toc:title gesetzt sind
    String figureVzName ="";
    String tableVzName ="";

    //Wird aufgerufen, um das Dokument zu verarbeiten
    @Override
    public void process(Document document, PreprocessorReader reader) {
        //Maps, um die Namen der Bilder / Tabellen zu speichern
        HashMap<Integer, String> imageNameHash = new HashMap<>();
        HashMap<Integer, String> tableNameHash = new HashMap<>();

        //Dokument in Liste gelesen
        List<String> lines = reader.readLines();

        //Liste, in der das Überarbeitete Dokument weitergegeben wird
        List<String> newLines = new ArrayList<String>();

        //Ursprungsdokument als String-Array
        String[] docLines = lines.toArray(new String[0]);
        //Variable, um den Beginn des Inhalts des tatsächlichen Doumentes zu speichern
        int contentStartLine = 0;

        //Booleans, um die Macro-Funktionen ein- und abzuschalten (wenn im Header ":tof-title: Macro" bzw ":tot-title: Macro" gesetzt)
        Boolean macroTot = false;
        Boolean macroTof = true;


        //Varibalen um die Line zu speichern, ab der das jew. Verzeichnis beginnen soll, falls im Header ":tof-title: Macro" bzw ":tot-title: Macro" gesetzt sind
        int placementTot = 0;
        int placementTof = 0;

        //Ermittlung des Beginns des Dokumentes
        for ( int i = 1 ; i < docLines.length; i++){
            if ( docLines[i].startsWith("=")){
                contentStartLine = i;
                break;
            }
        }

        //Ermittlen, ob :tof-title: als Attribut im Dokument-Header gesetzt ist. Wenn ja, wird der Titel in figureVzName übernommen
        for ( int i = 1 ; i < contentStartLine; i++){
            if (docLines[i].contains("tof-title")){
                String[] line = docLines[i].split(":");
                figureVzName = line[2];
            }
            //Ermittlen, ob :tot-title: als Attribut im Dokument-Header gesetzt ist. Wenn ja, wird der Titel in tableVzName übernommen
            if (docLines[i].contains("tot-title")){
                String[] line = docLines[i].split(":");
                tableVzName = line[2];
            }
            if (docLines[i].contains("tot") && docLines[i].contains("macro")){ macroTot = true;}
            if (docLines[i].contains("tof") && docLines[i].contains("macro")){ macroTof = true;}
        }

        //Dokument wird iteriert. Wenn eine Line mit "." anfängt, wird Line+1 auf ihren Inhalt geprüft
        for ( int i = 0 ; i < docLines.length; i++){
            if(docLines[i].contains("tot::[]")){ placementTot = i;System.out.println(Integer.toString(i));};
            if(docLines[i].contains("tof::[]")){ placementTof = i;System.out.println(Integer.toString(i));};


            if ( docLines[i].startsWith(".")){
                // Fängt Line+1 mit image::/ an, wird ein Anker für Bilder gesetzt und der Name des Bilds in imageNameHash übernommen
                if ( docLines[i+1].startsWith("image::/")){
                    newLines.add("[[tof0000" + Integer.toString(figureCounter)+ "]]");
                    imageNameHash.put(figureCounter, docLines[i]);
                    figureCounter = figureCounter+1;
                    newLines.add(docLines[i]);
                    newLines.add(docLines[i+1]);
                    i = i+1;
                }
                // Fängt Line+1 mit [cols= an, wird ein Anker für die Tabelle gesetzt und der Name der Tabelle in tableNameHash übernommen
                else if(docLines[i+1].startsWith("[cols=")){
                    newLines.add("[[tot0000" + Integer.toString(tableCounter)+ "]]");
                    tableNameHash.put(tableCounter, docLines[i]);
                    tableCounter = tableCounter+1;
                    newLines.add(docLines[i]);
                }
            }
            else{
                //Keine gesonderte Behandlung
                newLines.add(docLines[i]);
            }
        }
        // Wenn :tof: vor Beginn des Dokumentes ( in der Attribute-Section vor "=") gesetzt wurde, wird das Abbildungverzeichnis ans Ende des Dokumentes angefügt
        for ( int i = 0; i<=contentStartLine;i++){
            String tofTitle ="";
                        if (docLines[i].contains(":tof:")&& docLines[i].contains("macro")==false){
                            //Prüfung ob ein Name für das Abbildungsverzeichnis angegeben wurde. Falls nciht ist der default-Wert "Abbildungsverzeichnis"
                            if (!figureVzName.equals("")){
                                // Setzen des Namens des Abbildungsverzeichnisses
                                tofTitle = figureVzName;
                                //Default = "Abbildungverzeichnis"
                           } else {tofTitle = " Abbildungsverzeichnis";}

                            newLines.add("===" + tofTitle);
                            newLines.add("");
                            // Schleife, die durch das Hash iteriert und die Namen aller Bilder, die der Konvention entsprechen, mit Link im Abbildungsverzeichnis auflistet
                            for ( int c = 1; c <= imageNameHash.size(); c++){
                                newLines.add("<<tof0000"+Integer.toString(c)+",[Abbildung " + Integer.toString(c)+imageNameHash.get(c)+"]>>");
                                newLines.add("");
                            }
            };
            String totTitle ="";
            if(docLines[i].contains(":tot:") && docLines[i].contains("macro")==false){
                //Prüfung ob ein Name für das Tabellenverzeichnis angegeben wurde. Falls nciht ist der default-Wert "Tabellenverzeichnis"
                if (!tableVzName.equals("")){
                    // Setzen des Namens des Abbildungsverzeichnisses
                    totTitle = tableVzName;
                    // Default = Tabellenverzeichnis
                } else {totTitle = " Tabellenverzeichnis";}

                newLines.add("=== " + totTitle);
                newLines.add("");
                // Schleife, die durch das Hash iteriert und die Namen aller Bilder, die der Konvention entsprechen, mit Link im Abbildungsverzeichnis auflistet
                for ( int c = 1; c <= tableNameHash.size(); c++){
                    newLines.add("<<tot0000"+Integer.toString(c)+",[Tabelle " + Integer.toString(c)+tableNameHash.get(c) +"]>>");
                    newLines.add("");
                }
            }

        }
        // Wenn :tot: / :tof: vor Beginn des Dokumentes (in der Attribute-Section vor "=") gesetzt wurde, wird das Tabellenverzeichnis ans Ende des Dokumentes angefügt
        // Rückgabe des überarbeiteten Dokumentes an den Hauptprozess
        reader.restoreLines(newLines);
    }
}
