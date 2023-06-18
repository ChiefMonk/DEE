/**
 * 
 */
package uct.cs.dee.utils;

import java.io.IOException;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.syntax.PlFormula;

import enums.FormularType;

/**
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za) 
 *
 */
public interface IFormularParser 
{
	 /**
	     * Parse a single formula (propositional or defeasible)
	     * @param formula
	     * @return PlFormula
	     * @throws ParserException
	     * @throws IOException
	     */
	 public PlFormula parseFormula(FormularType formularType, String formula) throws ParserException, IOException;
}
