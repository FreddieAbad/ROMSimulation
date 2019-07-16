/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejemplo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 *
 * @author Freddy
 */
public class Memory {
     //Texto A Hexadecimal
    public static String toHexadecimal(String text) throws UnsupportedEncodingException {
        StringBuilder buf = new StringBuilder(200);
        for (char ch : text.toCharArray()) {
            if (buf.length() > 0) {
                buf.append(' ');
            }
            buf.append(String.format("%2x", (int) ch));
        }
        return buf.toString();
    }
    //Texto Hexadecimal a Matriz
    public static String[][] string2Matrix(String texto) {
        String[] splited = texto.split("\\s+");
        String[][] stringHex = new String[30][16];
        int count = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 16; j++) {
                if (count == splited.length) {
                    break;
                }
                stringHex[i][j] = splited[count];
                count++;
            }
        }
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 16; j++) {
                if (stringHex[i][j] == null) {
                    stringHex[i][j]="ff";
                }
            }
        }
        prettyMatrix(stringHex);
        return stringHex;
    }
    //Impresion matriz beauty
    public static void prettyMatrix(String[][] table) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(String.format("%8s", table[i][j]));
            }
            System.out.println("");
        }
    }
    //Cargar archivo
    public static String[][] loadFile() throws URISyntaxException, FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Freddy\\Documents\\NetBeansProjects\\ejemplo\\src\\archivoLectura\\archivoPrueba.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            everything = toHexadecimal(everything);
            String[][] hexMatrix = string2Matrix(everything);
            return hexMatrix;
        } finally {
            br.close();
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        loadFile();
    }
}
