package ua.rozborsky.tollRoadClient.classes;

/**
 * Created by roman on 19.01.2017.
 */
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

public class JTextFieldLimit extends PlainDocument {
    private int limit = 10;

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
