/*
 *Scans characters and assigns them each
 *a Token type
 */

package VariableCompiler;

public class LexicalAnalysis {
	public enum Token {DIGIT, LETTER, IDENT, INT_LIT, 
					   ERROR, SPACE, STOP, PLUS_CODE, 
					   MULT_CODE, DIVI_CODE, FOR_LOOP, 
					   AND_CODE, OR_CODE, MOD_CODE,
					   ASSIGN_CODE, NOT_CODE,IF_CODE, 
					   CHAR_TYPE, INT_TYPE, NOT_EQUAL_CODE,
					   LEFT_P, RIGHT_P, MINUS_CODE, EQUAL_CODE,
					   LESS_THAN, GREATER_THAN};
	public char nextChar;
	public Token charClass;
	public String lexeme;
	
	private String input;
	
	public LexicalAnalysis(String in){
		input = in;
		charClass = Token.ERROR;
		nextChar=' ';
		lexeme = "";
		getChar();
	}
	
	Token charClassType(){
		if(lexeme=="for") return Token.FOR_LOOP;
		if(lexeme=="if") return Token.IF_CODE;
		if(lexeme=="int") return Token.INT_TYPE;
		if(lexeme=="char") return Token.CHAR_TYPE;
		return Token.IDENT;
	}
	
	public Token lex(){
		lexeme="";
		while(charClass == Token.SPACE)
			getChar();
		if(charClass ==Token.ERROR) {addChar();getChar();return Token.ERROR;}
		if(charClass == Token.STOP) {return Token.STOP;}
		
		switch(charClass){
		case LETTER: 
			addChar();
			getChar();
			while(charClass==Token.LETTER || charClass == Token.DIGIT){
				addChar();
				getChar();
			}
			return charClassType();
		case DIGIT:
			addChar();
			getChar();
			while(charClass==Token.DIGIT){
				addChar();
				getChar();
			}
			return Token.INT_LIT;
		case PLUS_CODE:
			addChar();
			getChar();
			return Token.PLUS_CODE;
		case MULT_CODE:
			addChar();
			getChar();
			return Token.MULT_CODE;
		case DIVI_CODE:
			addChar();
			getChar();
			return Token.DIVI_CODE;
		case NOT_CODE:
			addChar();
			getChar();
			if(Token.ASSIGN_CODE == peek()){
				addChar();
				getChar();
				return Token.NOT_EQUAL_CODE;
			}
			return Token.NOT_CODE;
		case LEFT_P:
			addChar();
			getChar();
			return Token.LEFT_P;
		case RIGHT_P:
			addChar();
			getChar();
			return Token.RIGHT_P;
		case MINUS_CODE:
			addChar();
			getChar();
			return Token.MINUS_CODE;
		case ASSIGN_CODE:
			addChar();
			getChar();
			if(Token.ASSIGN_CODE == peek()){
				addChar();
				getChar();
				return Token.EQUAL_CODE;
			}
			return Token.ASSIGN_CODE;
		case LESS_THAN:
			addChar();
			getChar();
			return Token.LESS_THAN;
		case GREATER_THAN:
			addChar();
			getChar();
			return Token.GREATER_THAN;
		default:
			break;
		}
		
		return Token.ERROR;
	}
	
	public void setNewInputString(String in){
		input=in;
		getChar();
	}
	
	private void addChar(){
		lexeme+=nextChar;
	}
	
	private Token peek(){
		Token charClassT = null;
		char nextCharT;
		
		if(input.length()>0){
			nextCharT = input.charAt(0);
		}
		else nextCharT=';';
		
		charClassT=Token.ERROR;
		
		if((nextCharT > 64 && nextCharT<91) || (nextCharT>96 && nextCharT<123)){
			charClassT = Token.LETTER;
		}
		
		if((nextCharT>47 && nextCharT<58)){
			charClassT = Token.DIGIT;
		}
		
		switch(nextCharT){
		case ' ': charClassT = Token.SPACE;
		break;
		case ';': charClassT = Token.STOP;
		break;
		case '+': charClassT = Token.PLUS_CODE;
		break;
		case '*': charClassT = Token.MULT_CODE;
		break;
		case '/': charClassT = Token.DIVI_CODE;
		break;
		case '&': charClassT = Token.AND_CODE;
		break;
		case '|': charClassT = Token.OR_CODE;
		break;
		case '=': charClassT = Token.ASSIGN_CODE;
		break;
		case '!': charClassT = Token.NOT_CODE;
		break;
		case '%': charClassT = Token.MOD_CODE;
		break;
		case '(': charClassT = Token.LEFT_P;
		break;
		case ')': charClassT = Token.RIGHT_P;
		break;
		case '-': charClassT = Token.MINUS_CODE;
		break;
		case '<': charClassT = Token.LESS_THAN;
		break;
		case '>': charClassT = Token.GREATER_THAN;
		break;
		default: charClassT = Token.ERROR;
			break;
		}
		
		return charClassT;
		
	}
	
	private void getChar(){
		if(input.length()>0){
			nextChar = input.charAt(0);
			input.replaceFirst(String.valueOf(nextChar), "");
		}
		else nextChar=';';
		
		charClass = Token.ERROR;
		
		if((nextChar > 64 && nextChar<91) || (nextChar>96 && nextChar<123)){
			charClass = Token.LETTER;
		}
		
		if((nextChar>47 && nextChar<58))
			charClass = Token.DIGIT;
		
		switch(nextChar){
			case ' ': charClass = Token.SPACE;
			break;
			case ';': charClass = Token.STOP;
			break;
			case '+': charClass = Token.PLUS_CODE;
			break;
			case '*': charClass = Token.MULT_CODE;
			break;
			case '/': charClass = Token.DIVI_CODE;
			break;
			case '&': charClass = Token.AND_CODE;
			break;
			case '|': charClass = Token.OR_CODE;
			break;
			case '=': charClass = Token.ASSIGN_CODE;
			break;
			case '!': charClass = Token.NOT_CODE;
			break;
			case '%': charClass = Token.MOD_CODE;
			break;
			case '(': charClass = Token.LEFT_P;
			break;
			case ')': charClass = Token.RIGHT_P;
			break;
			case '-': charClass = Token.MINUS_CODE;
			break;
			default:
				break;
		}
	}
}