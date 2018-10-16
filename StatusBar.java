import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StatusBar extends JLabel {
	public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        super.setHorizontalAlignment(SwingConstants.LEFT);
        super.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    public void setMessage(String message) {
    	setForeground(Color.BLACK);
        setText(" "+message);        
    }
    
    public void setErrorMessage(String message) {
    	setForeground(Color.RED);
        setText(" "+message);        
    } 
}
