package com.mycompany.analizadorlexico;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;

public class AnalizadorLexico {

    public static void main(String[] args) {

        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }

    /*
     */
    public static void analyze(String textoEntrada, JTextArea txtArea2) {

        List<Token> tokens = new ArrayList<>();  // Crea una lista vacía llamada tokens para almacenar los tokens encontrados.
        /*clase Pattern permite compilar una expresión regular en un objeto Pattern que luego puede ser utilizado para realizar 
        operaciones de búsqueda y coincidencia en cadenas de texto.*/
        Pattern intPattern = Pattern.compile("\\bint\\b"); //int
        Pattern stringPattern = Pattern.compile("\\bString\\b"); //String
        Pattern assignPattern = Pattern.compile("="); //=
        Pattern stringLiteralPattern = Pattern.compile("\"[^\"]*\"");// "a+b"
        Pattern identifierPattern = Pattern.compile("[a-zA-Z]+"); //x
        Pattern operatorPattern = Pattern.compile("\\+");//+
        Pattern semicolonPattern = Pattern.compile(";");//;

        // Crear un patrón combinado para identificar todos los tokens
        Pattern tokenPattern = Pattern.compile(
                /*La expresión String.format() se utiliza para construir una cadena de texto que contiene los patrones de expresiones
                regulares de cada tipo de token. El formato utilizado es (%s)|(%s)|(%s)|(%s)|(%s)|(%s)|(%s), donde %s representa un
                marcador de posición para un patrón de expresión regular.*/
                String.format("(%s)|(%s)|(%s)|(%s)|(%s)|(%s)|(%s)",
                        intPattern.pattern(),
                        stringPattern.pattern(),
                        assignPattern.pattern(),
                        stringLiteralPattern.pattern(),
                        identifierPattern.pattern(),
                        operatorPattern.pattern(),
                        semicolonPattern.pattern()));

        /*Obtener los tokens del textoEntrada
        Matcher llamado matcher que se utiliza para buscar coincidencias entre el patrón tokenPattern y el textoEntrada
         */
        Matcher matcher = tokenPattern.matcher(textoEntrada);// Se crea un objeto 
        while (matcher.find()) {//Se inicia un bucle mientras haya coincidencias del patrón en el textoEntrada,  find() buscar la siguiente coincidencia del patrón en una cadena de texto.
            String tokenValue = matcher.group().trim();//obtiene el valor del token encontrado en el textoEntrada. matcher.group() devuelve la subcadena coincidente y trim()  eliminar los espacios en blanco alrededor del token.
            TokenType tokenType;// variable tokenType del tipo TokenType, que se utilizará para almacenar el tipo de token correspondiente al valor del token.

            /*matches() de un objeto Matcher para verificar si el tokenValue coincide exactamente con el patrón definido en intPattern*/
            if (intPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.INT;
            } else if (stringPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.STRING;
            } else if (assignPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.ASSIGN;
            } else if (stringLiteralPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.STRING_LITERAL;
            } else if (identifierPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.IDENTIFIER;
            } else if (operatorPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.OPERATOR;
            } else if (semicolonPattern.matcher(tokenValue).matches()) {
                tokenType = TokenType.SEMICOLON;
            } else {
                // Token no reconocido
                continue;
            }

            //Se crea un nuevo token y se guardamos los tokens en lista tokens
            tokens.add(new Token(tokenType, tokenValue));
        }

        StringBuilder result = new StringBuilder();//Almacenar los tokens
        //Recorre cada token 
        for (Token token : tokens) {
            result.append("Token --> "+token.getType()).append(": ").append(token.getValue()).append("\n");
        }

        // Muestra el resultado en txtArea2
        txtArea2.setText(result.toString());
    }

}
