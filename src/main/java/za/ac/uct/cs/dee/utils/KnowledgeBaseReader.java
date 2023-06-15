package za.ac.uct.cs.dee.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.tweetyproject.commons.ParserException;

import za.ac.uct.cs.dee.common.structures.DefeasibleFormulaCollection;
import za.ac.uct.cs.dee.common.structures.DefeasibleKnowledgeBase;

/**
 * Class provides ability to read in defeasible knowledge base text files stored in a formula per line
 * format and returns an instance of the DefeasibleKnowledgeBase class
 */
public class KnowledgeBaseReader extends FileReader implements DefeasibleParser<DefeasibleKnowledgeBase>{

    /**
     * Constructor - must specify directory containing knowledge base files
     * @param path
     */
    public KnowledgeBaseReader(String path) {
        super(path);
    }

    /**
     * Reads in and parses the specified knowledge base text file
     * @param filePath - path to the knowledge base file
     * @return DefeasibleKnowledgeBase instance
     * @throws FileNotFoundException
     * @throws ParserException
     * @throws IOException
     */
    @Override
    public DefeasibleKnowledgeBase parse(String filePath) throws FileNotFoundException, ParserException, IOException {
        ArrayList<String> formulas = this.readFileLines(filePath);
        DefeasibleFormulaCollection parsedFormulas = Parsing.parseFormulas(formulas);
        return new DefeasibleKnowledgeBase(parsedFormulas);
    }

}
