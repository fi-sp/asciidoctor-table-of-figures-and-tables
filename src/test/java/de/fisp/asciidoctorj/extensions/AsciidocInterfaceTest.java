package de.fisp.asciidoctorj.extensions;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.DocumentHeader;
import org.asciidoctor.converter.JavaConverterRegistry;
import org.asciidoctor.extension.*;
import org.asciidoctor.log.LogHandler;
import org.asciidoctor.syntaxhighlighter.SyntaxHighlighterRegistry;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AsciidocInterfaceTest implements Asciidoctor, JavaExtensionRegistry {

    Integer treeProcessorCounter = 0;
    Integer blockProcessorCounter = 0;
    List<String> processorList = new LinkedList<String>();


    @Override
    public String convert(String content, Map<String, Object> options) {
        return null;
    }

    @Override
    public <T> T convert(String content, Map<String, Object> options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public String convert(String content, Options options) {
        return null;
    }

    @Override
    public <T> T convert(String content, Options options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public String convert(String content, OptionsBuilder options) {
        return null;
    }

    @Override
    public <T> T convert(String content, OptionsBuilder options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public void convert(Reader contentReader, Writer rendererWriter, Map<String, Object> options) throws IOException {

    }

    @Override
    public void convert(Reader contentReader, Writer rendererWriter, Options options) throws IOException {

    }

    @Override
    public void convert(Reader contentReader, Writer rendererWriter, OptionsBuilder options) throws IOException {

    }

    @Override
    public String convertFile(File file, Map<String, Object> options) {
        return null;
    }

    @Override
    public <T> T convertFile(File file, Map<String, Object> options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public String convertFile(File file, Options options) {
        return null;
    }

    @Override
    public <T> T convertFile(File file, Options options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public String convertFile(File file, OptionsBuilder options) {
        return null;
    }

    @Override
    public <T> T convertFile(File file, OptionsBuilder options, Class<T> expectedResult) {
        return null;
    }

    @Override
    public String[] convertDirectory(Iterable<File> directoryWalker, Map<String, Object> options) {
        return new String[0];
    }

    @Override
    public String[] convertDirectory(Iterable<File> directoryWalker, Options options) {
        return new String[0];
    }

    @Override
    public String[] convertDirectory(Iterable<File> directoryWalker, OptionsBuilder options) {
        return new String[0];
    }

    @Override
    public String[] convertFiles(Collection<File> files, Map<String, Object> options) {
        return new String[0];
    }

    @Override
    public String[] convertFiles(Collection<File> asciidoctorFiles, Options options) {
        return new String[0];
    }

    @Override
    public String[] convertFiles(Collection<File> files, OptionsBuilder options) {
        return new String[0];
    }

    @Override
    public void requireLibrary(String... requiredLibraries) {

    }

    @Override
    public void requireLibraries(Collection<String> requiredLibraries) {

    }

    @Override
    public DocumentHeader readDocumentHeader(File file) {
        return null;
    }

    @Override
    public DocumentHeader readDocumentHeader(String content) {
        return null;
    }

    @Override
    public DocumentHeader readDocumentHeader(Reader contentReader) {
        return null;
    }

    @Override
    public JavaExtensionRegistry javaExtensionRegistry() {
        return this;
    }

    @Override
    public RubyExtensionRegistry rubyExtensionRegistry() {
        return null;
    }

    @Override
    public JavaConverterRegistry javaConverterRegistry() {
        return null;
    }

    @Override
    public SyntaxHighlighterRegistry syntaxHighlighterRegistry() {
        return null;
    }

    @Override
    public ExtensionGroup createGroup() {
        return null;
    }

    @Override
    public ExtensionGroup createGroup(String groupName) {
        return null;
    }

    @Override
    public void unregisterAllExtensions() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public String asciidoctorVersion() {
        return null;
    }

    @Override
    public Document load(String content, Map<String, Object> options) {
        return null;
    }

    @Override
    public Document loadFile(File file, Map<String, Object> options) {
        return null;
    }

    @Override
    public void registerLogHandler(LogHandler logHandler) {

    }

    @Override
    public void unregisterLogHandler(LogHandler logHandler) {

    }


    @Override
    public JavaExtensionRegistry docinfoProcessor(Class<? extends DocinfoProcessor> docInfoProcessor) {
        return this;
    }

    @Override
    public JavaExtensionRegistry docinfoProcessor(DocinfoProcessor docInfoProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry docinfoProcessor(String docInfoProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry preprocessor(Class<? extends Preprocessor> preprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry preprocessor(Preprocessor preprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry preprocessor(String preprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry postprocessor(String postprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry postprocessor(Class<? extends Postprocessor> postprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry postprocessor(Postprocessor postprocessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry includeProcessor(String includeProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry includeProcessor(Class<? extends IncludeProcessor> includeProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry includeProcessor(IncludeProcessor includeProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry treeprocessor(Treeprocessor treeprocessor) {
        processorList.add(treeprocessor.getClass().toString());
        return null;
    }

    @Override
    public JavaExtensionRegistry treeprocessor(Class<? extends Treeprocessor> abstractTreeProcessor) {
        processorList.add(abstractTreeProcessor.getName());
        return null;
    }

    @Override
    public JavaExtensionRegistry treeprocessor(String treeProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(String blockName, String blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(String blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(String blockName, Class<? extends BlockProcessor> blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(Class<? extends BlockProcessor> blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(BlockProcessor blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry block(String blockName, BlockProcessor blockProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(String blockName, Class<? extends BlockMacroProcessor> blockMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(Class<? extends BlockMacroProcessor> blockMacroProcessor) {
        processorList.add(blockMacroProcessor.getName());
        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(String blockName, String blockMacroProcessor) {


        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(String blockMacroProcessor) {

        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(BlockMacroProcessor blockMacroProcessor) {

        return null;
    }

    @Override
    public JavaExtensionRegistry blockMacro(String blockName, BlockMacroProcessor blockMacroProcessor) {

        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(InlineMacroProcessor inlineMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(String macroName, InlineMacroProcessor inlineMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(String macroName, Class<? extends InlineMacroProcessor> inlineMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(Class<? extends InlineMacroProcessor> inlineMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(String blockName, String inlineMacroProcessor) {
        return null;
    }

    @Override
    public JavaExtensionRegistry inlineMacro(String inlineMacroProcessor) {
        return null;
    }
}
