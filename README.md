# Asciidoctorj-Extension zum automatischen Einfügen von Tabellen- und Abbildungsverzeichnissen

Zum Benutzen der Extension die Dependencies in der pom.xml zur Verfügung stellen

    <groupId>de.fisp.asciidoctorj.extensions</groupId>
      <artifactId>toftot</artifactId>
      <version>1.0-SNAPSHOT</version>    

Nun steht die Extension zur Verfügung.
Die Nutzung der Features **:tof:** bzw. **:tof:** funktioniert analog zu **:toc:**

Wenn ein Tabellenverzeichnis eingefügt werden soll, muss im Document-Header das Attribut 
**:tot:** gesetzt werden. Über das zusätzliche Setzen von **:tot-title:** kann ein Titel für das Tabellenverzeichnis
gesetzt werden, default ist "Tabellenverzeichnis". 


Wenn ein Abbildungsverzeichnis eingefügt werden soll, muss im Document-Header das Attribut 
**:tof:** gesetzt werden. Über das zusätzliche Setzen von **:tof-title:** kann ein Titel für das Abbildungsverzeichnis
gesetzt werden, default ist "Abbildungsverzeichnis". 


Die Extension funktioniert wie folgt : 

Wenn im Header des Dokuments :tof: und/oder :tot: gesetzt sind, werden die jeweiligen Verzeichnisse nach folgendem Schema generiert:

Die Extension durchläuft das übergebene Dokument und sucht nach Zeilen, die mit einem Punkt "." beginnen. Wenn eine solche Zeile gefunden wurde,
wird geprüft, ob es sich bei der folgenden Zeile um den Beginn einer Tabelle oder eines Bildes handelt. Der Name des Objekts wird in die jeweilige
Namensliste (Abbildung oder Tabelle) übernommen und es wird ein Anker gesetzt.
Nachdem das gesamte Dokument durchlaufen wurde, werden die generierten Verzeichnisse mit den jeweils passenden Ankern ans Ende des Zieldokumentes geschrieben .

#Hinweis
Die Extension erkennt nur Tabellen/Bilder, die im Source-Dokument richtig nach Asciidoc-Nomenklatur beschrieben sind. Diese sieht wie folgt aus:

für Bilder:

    .Image-Name
    image::/my/path/image11.png[image,width=XXX,height=XXX]

für Tabellen:

    .Table-Name
    [cols=",",options="header",]
    |===
    |Werte |Werte
    |Werte |Werte
    |Werte|
    * Werte
    * Werte
    |===

Wichtig ist hierbei, dass nach der Zeile, die mit dem Punkt "." beginnt und den Namen der Abbildung/Tabelle bestimmt, **keine** Leerzeile folgt, sondern direkt 
der Pfad zum Bild ("**image::/...**") bzw. die Struktur der Tabelle (" **[cols=","...** "), da das Objekt sonst nicht von 
der Extension erkannt wird und auch von Asciidoctorj nicht automatisch mitgezählt wird. Um die Erkennung der Bilder sicherzustellen,
bitte in der Pfaddeklaration **image::/** statt **image:/** verwenden, also mit zwei Doppelpunkten(**::**), statt mit einem(:), da Asciidoctorj sonst
ebenfalls nicht automatisch mitzählt und die Extension das Bild somit nicht einbezieht.
 