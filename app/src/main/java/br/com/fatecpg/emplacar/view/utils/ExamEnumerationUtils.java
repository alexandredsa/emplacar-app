package br.com.fatecpg.emplacar.view.utils;

/**
 * Created by alexandre on 05/11/16.
 */

public class ExamEnumerationUtils {
    public static char getLetterIndex(int val){
        switch (val){
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
        }

        throw new UnsupportedOperationException("Equivalent char isn't declared.");
    }
}
