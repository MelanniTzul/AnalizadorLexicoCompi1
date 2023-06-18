package com.mycompany.analizadorlexico;

/**
 *
 * @author melanni
 */

/*Clase token */
public class Token {

    private TokenType type;//nombre
    private String value;//valor

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + type + ": " + value + "]";
    }
}

/*El enum TokenType enumera los posibles tipos de token que se pueden encontrar durante el análisis léxico. 
Cada valor enum representa un tipo específico de token.*/
enum TokenType {
    INT, //int
    STRING,//String 
    ASSIGN,//=
    IDENTIFIER,//y
    STRING_LITERAL, //"a+b"
    OPERATOR,//+
    SEMICOLON //;
}
