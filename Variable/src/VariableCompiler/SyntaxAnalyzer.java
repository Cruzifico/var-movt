package VariableCompiler;

import VariableCompiler.LexicalAnalysis.Token;

public class SyntaxAnalyzer {
	Token nextToken;
	LexicalAnalysis LA;
	
	public void syntax(){
		//create symbol table 
		expr();
	}
	
	public void setNewInput(String in){
		LA.setNewInputString(in);
		nextToken=LA.lex();		
	}
	
	SyntaxAnalyzer(String in){
		LA.setNewInputString(in);
		nextToken=LA.lex();
	}
	
	void term(){
		factor();
		while(nextToken==Token.MULT_CODE || nextToken==Token.DIVI_CODE){
			LA.lex();
			factor();
		}
	}
	
	void expr(){
		term();
		while(nextToken==Token.PLUS_CODE || nextToken==Token.MINUS_CODE
				|| nextToken==Token.MOD_CODE){
			LA.lex();
			term();
		}
	}
	
	void id(){
		
	}
	
	void factor(){
		if(nextToken==Token.IDENT || nextToken==Token.INT_LIT){
			LA.lex();
		}
		else{
			if(nextToken == Token.LEFT_P){
				LA.lex();
				expr();
				if(nextToken == Token.RIGHT_P){
					LA.lex();
				}
				else error();
			}
			else error();
		}
	}
	
	void assign(){
		if(nextToken!=Token.IDENT){
			error();
		}
		else{
			expr();
		}
	}
	
	void if_stmt(){
		if(nextToken!= Token.IF_CODE){
			error();
		}
		else{
			LA.lex();
			if(nextToken!= Token.LEFT_P){
				error();
			}
			else{
				logic_expr();
				if(nextToken!=Token.RIGHT_P){
					error();
				}
				else{
					stmt();
				}
			}
		}
	}
	
	void stmt(){
		if(nextToken == Token.IF_CODE){
			if_stmt();
		}
		else expr();
	}
	
	void logic_expr(){
		expr();
		if(nextToken!= Token.AND_CODE || nextToken!= Token.OR_CODE  
			|| nextToken!=Token.NOT_EQUAL_CODE || nextToken!=Token.EQUAL_CODE
			|| nextToken!=Token.LESS_THAN || nextToken!=Token.GREATER_THAN){
			error();
		}
		expr();
	}
	
	void error(){
		//create error array 
	}
}
