package uct.cs.dee.utils;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.PlFormula;

import enums.FormularType;

public class FormularParser implements IFormularParser
{
	
	@Override
	public PlFormula parseFormula(FormularType formularType, String formula) throws ParserException, IOException 
	{
		if(ExtensionUtils.isEmptyOrNull(formula))		
			throw new InvalidParameterException(Constants.ErrorMessages.SpecifyValidFormula);
				
		if(formularType == FormularType.Propositional)
		{
			if(ExtensionUtils.isDefeasible(formula))					
				throw new ParserException("The formular specified is not a propositional '" + formula + "'");
			
			return new PlParser().parseFormula(formula);
		}
		
		if(formularType == FormularType.Defeasible)
		{
			if(!ExtensionUtils.isDefeasible(formula))					
				throw new ParserException("The formular specified is not a Defeasible '" + formula + "'");
			
			return new PlParser().parseFormula(ExtensionUtils.materialiseDefeasibleImplication(formula));
		}
		
		throw new InvalidParameterException("The FormularType specified is not supported");
	}
	
}