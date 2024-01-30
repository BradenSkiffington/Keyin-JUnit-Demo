package com.keyin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;

public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
    }

    @Test
    public void testLoadDictionaryData() throws URISyntaxException, IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        Map<String, Integer> wordSuggestionDB = suggestionEngine.getWordSuggestionDB();
        assertNotNull(wordSuggestionDB);
        assertFalse(wordSuggestionDB.isEmpty(), "Word suggestion database should not be empty.");
    }

    @Test
    public void testSetWordSuggestionDB() {
        SuggestionsDatabase suggestionsDatabase = new SuggestionsDatabase();
        suggestionEngine.setWordSuggestionDB(suggestionsDatabase);
        assertNotNull(suggestionEngine.getWordSuggestionDB(), "Word suggestion database should not be null.");
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        boolean testInstanceSame = true;
        assertTrue(testInstanceSame);
        assertFalse(suggestionEngine.generateSuggestions("correct").contains("correct"));
    }

}
