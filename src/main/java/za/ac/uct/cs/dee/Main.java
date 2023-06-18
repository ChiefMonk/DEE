package za.ac.uct.cs.dee;

import org.tweetyproject.logics.pl.syntax.*;

import java.io.*;
import java.util.Scanner;

import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.commons.ParserException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, ParserException {
        PlBeliefSet beliefSet = new PlBeliefSet();
        PlParser parser = new PlParser();
        PlBeliefSet classicalSet = new PlBeliefSet();
        PlFormula formulaWereCheckingFor = (PlFormula) parser.parseFormula("a");

        String fileName = args[0];
        String entailmentCheckingAlgorithm = args[1];
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            String formulaToCheckFor = reader.nextLine();
            if (formulaToCheckFor.contains("¬")) {
                formulaToCheckFor = formulaToCheckFor.replaceAll("¬", "!");
            }
            formulaWereCheckingFor = (PlFormula) parser.parseFormula(reformatDefeasibleImplication(formulaToCheckFor));
            while (reader.hasNextLine()) {
                String stringFormula = reader.nextLine();
                if (stringFormula.contains("¬")) {
                    stringFormula = stringFormula.replaceAll("¬", "!");
                }
                if (stringFormula.contains("~>")) {
                    stringFormula = reformatDefeasibleImplication(stringFormula);
                } else {
                    classicalSet.add((PlFormula) parser.parseFormula(stringFormula));
                }
                beliefSet.add((PlFormula) parser.parseFormula(stringFormula));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Output not in correct format. Please ensure each formula is in a separate line, with the first line being the defeasible query, and the remainder being the knowledge base. All formulas must use the following syntax:");
            System.out.println("Implication symbol: =>");
            System.out.println("Defeasible Implication symbol: ~>");
            System.out.println("Conjunction symbol: && ");
            System.out.println("Disjunction symbol: ||");
            System.out.println("Equivalence symbol: <=>");
            System.out.println("Negation symbol: !");
        }
        RationalReasoner reasoner = new RationalReasoner(beliefSet, formulaWereCheckingFor, classicalSet,
                entailmentCheckingAlgorithm);
    }

    static String reformatDefeasibleImplication(String formula) {
        int index = formula.indexOf("~>");
        formula = "(" + formula.substring(0, index) + ") => (" + formula.substring(index + 2, formula.length()) + ")";
        return formula;
    }
}