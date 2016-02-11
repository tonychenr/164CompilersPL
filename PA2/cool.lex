/*
 * CS164: Spring 2004
 * Programming Assignment 2
 *
 * The scanner definition for Cool.
 *
 */

import java_cup.runtime.Symbol;

%%

/* Code enclosed in %{ %} is copied verbatim to the lexer class definition.
 * All extra variables/methods you want to use in the lexer actions go
 * here.  Don't remove anything that was here initially.  */
%{
    // Max size of string constants
    static int MAX_STR_CONST = 1024;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    // For line numbers
    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }

    private AbstractSymbol filename;

    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
	return filename;
    }

    /* keeps track of how deep we currently are in nested comments
     * we can exit multiline comment when this reaches 0 */
    int comment_depth = 0;


    private boolean str_too_long() {
        return string_buf.length() > MAX_STR_CONST;
    }
    private void clear_buf() {
        string_buf.delete(0, string_buf.length());
    }
    private Symbol get_string() {
        return new Symbol(TokenConstants.STR_CONST, 
            AbstractTable.stringtable.addString(string_buf.toString()));
    }

    Symbol ret_error(String str) {
        return new Symbol(TokenConstants.ERROR,str);
    }
%}


/*  Code enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here. */
%init{
    // empty for now
%init}

/*  Code enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work. */
%eofval{
    switch(yystate()) {
    case YYINITIAL:
        yybegin(EOF);
        break;
    case MULTILINE_COMMENT:
        yybegin(EOF);
        return ret_error("EOF in comment");
    case STRING:
        yybegin(EOF);
        return ret_error("EOF in string constant");
    case LINE_COMMENT:
        yybegin(EOF);
    case STRING_ERROR:
        yybegin(EOF);
        return ret_error("EOF in string constant");
    }
    return new Symbol(TokenConstants.EOF);
%eofval}

/* Do not modify the following two jlex directives */
%class CoolLexer
%cup





/* additional states */
%state LINE_COMMENT
%state MULTILINE_COMMENT
%state STRING
%state EOF
%state STRING_ERROR


/* lexical rules: */
%%

<YYINITIAL>"=>"		{ return new Symbol(TokenConstants.DARROW); }

<YYINITIAL>[0-9][0-9]*  { /* Integers */
                          return new Symbol(TokenConstants.INT_CONST,
					    AbstractTable.inttable.addString(yytext())); }

<YYINITIAL>[Cc][Aa][Ss][Ee]	{ return new Symbol(TokenConstants.CASE); }
<YYINITIAL>[Cc][Ll][Aa][Ss][Ss] { return new Symbol(TokenConstants.CLASS); }
<YYINITIAL>[Ee][Ll][Ss][Ee]  	{ return new Symbol(TokenConstants.ELSE); }
<YYINITIAL>[Ee][Ss][Aa][Cc]	{ return new Symbol(TokenConstants.ESAC); }
<YYINITIAL>f[Aa][Ll][Ss][Ee]	{ return new Symbol(TokenConstants.BOOL_CONST, Boolean.FALSE); }
<YYINITIAL>[Ff][Ii]             { return new Symbol(TokenConstants.FI); }
<YYINITIAL>[Ii][Ff]  		{ return new Symbol(TokenConstants.IF); }
<YYINITIAL>[Ii][Nn]             { return new Symbol(TokenConstants.IN); }
<YYINITIAL>[Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss] { return new Symbol(TokenConstants.INHERITS); }
<YYINITIAL>[Ii][Ss][Vv][Oo][Ii][Dd] { return new Symbol(TokenConstants.ISVOID); }
<YYINITIAL>[Ll][Ee][Tt]         { return new Symbol(TokenConstants.LET); }
<YYINITIAL>[Ll][Oo][Oo][Pp]  	{ return new Symbol(TokenConstants.LOOP); }
<YYINITIAL>[Nn][Ee][Ww]		{ return new Symbol(TokenConstants.NEW); }
<YYINITIAL>[Nn][Oo][Tt] 	{ return new Symbol(TokenConstants.NOT); }
<YYINITIAL>[Oo][Ff]		{ return new Symbol(TokenConstants.OF); }
<YYINITIAL>[Pp][Oo][Oo][Ll]  	{ return new Symbol(TokenConstants.POOL); }
<YYINITIAL>[Tt][Hh][Ee][Nn]   	{ return new Symbol(TokenConstants.THEN); }
<YYINITIAL>t[Rr][Uu][Ee]	{ return new Symbol(TokenConstants.BOOL_CONST, Boolean.TRUE); }
<YYINITIAL>[Ww][Hh][Ii][Ll][Ee] { return new Symbol(TokenConstants.WHILE); }

<YYINITIAL>"+"			{ return new Symbol(TokenConstants.PLUS); }
<YYINITIAL>"/"			{ return new Symbol(TokenConstants.DIV); }
<YYINITIAL>"-"			{ return new Symbol(TokenConstants.MINUS); }
<YYINITIAL>"*"			{ return new Symbol(TokenConstants.MULT); }
<YYINITIAL>"="			{ return new Symbol(TokenConstants.EQ); }
<YYINITIAL>"<"			{ return new Symbol(TokenConstants.LT); }
<YYINITIAL>"."			{ return new Symbol(TokenConstants.DOT); }
<YYINITIAL>"~"			{ return new Symbol(TokenConstants.NEG); }
<YYINITIAL>","			{ return new Symbol(TokenConstants.COMMA); }
<YYINITIAL>";"			{ return new Symbol(TokenConstants.SEMI); }
<YYINITIAL>":"			{ return new Symbol(TokenConstants.COLON); }
<YYINITIAL>"("			{ return new Symbol(TokenConstants.LPAREN); }
<YYINITIAL>")"			{ return new Symbol(TokenConstants.RPAREN); }
<YYINITIAL>"@"			{ return new Symbol(TokenConstants.AT); }
<YYINITIAL>"}"			{ return new Symbol(TokenConstants.RBRACE); }
<YYINITIAL>"{"			{ return new Symbol(TokenConstants.LBRACE); }





<YYINITIAL> {
    /* newline and whitespace */
    \n                  { curr_lineno ++; }
    [\ \f\r\t\v]+       {  }

    /* start of comments and strings */
    \-\-                { yybegin(LINE_COMMENT); }
    \(\*                { yybegin(MULTILINE_COMMENT);
                          comment_depth = 1; }
    \"                  { yybegin(STRING); }

    /* unmatched close comment error */
    \*\)                { return ret_error("Unmatched *)"); }
}


/* handles content of single line comments and exiting from them */
<LINE_COMMENT> {
    \n                  { yybegin(YYINITIAL);
                          curr_lineno ++; }
    .*                  {  }
}    


/* handles content of multi line comments and exiting from them */
<MULTILINE_COMMENT> {
    \(\*                { comment_depth ++; }
    \*\)                { comment_depth --;
                          if (comment_depth == 0) yybegin(YYINITIAL); }
    \n                  { curr_lineno ++; }
    [^]                 {  }
}


/* handles the content, exiting, and errors of strings */
<STRING> {
    \"                  { yybegin(YYINITIAL);
                          if (str_too_long()) {
                              clear_buf();
                              return ret_error("String constant too long");
                          }
                          Symbol ret = get_string();
                          clear_buf();
                          return ret; }           
    \\t                 { string_buf.append('\t'); } 
    \\n                 { string_buf.append('\n');} 
    \\r                 { string_buf.append('\r'); }
    \\b                 { string_buf.append('\b'); }
    \\f                 { string_buf.append('\f'); }
    \\\"                { string_buf.append('\"'); } 
    \\                  { string_buf.append('\\'); } 
    \\\n                { string_buf.append('\n');
                          curr_lineno ++; }
    \n                  { yybegin(YYINITIAL);
                          curr_lineno ++; 
                          return ret_error("Unterminated string constant"); }
    \0                  { yybegin(STRING_ERROR);
                          return ret_error("String contains null character"); }
    \\.                 { string_buf.append(yytext().charAt(1)); }
    [^]                 { string_buf.append(yytext()); }
}


/* this state is entered after a null character is reached inside a string
 * does nothing until the end of the string so we can resume lexing after */
<STRING_ERROR> {
    \n                  { yybegin(YYINITIAL);
                          curr_lineno ++; }
    \"                  { yybegin(YYINITIAL); }
    [^]                 {  }
}


/* handles object IDs and type IDs
 * since this is after all the keywords, those will be matched instead if encountered */
<YYINITIAL> {
    [a-z][A-Za-z0-9\_]* { return new Symbol(TokenConstants.OBJECTID, AbstractTable.stringtable.addString(yytext())); }
    [A-Z][A-Za-z0-9\_]* { return new Symbol(TokenConstants.TYPEID, AbstractTable.stringtable.addString(yytext())); }
}





.                { /* any character reached at this point is unmatched and is an error */
                   System.err.println("LEXER BUG - UNMATCHED: " + yytext());
                   return ret_error(yytext()); }
