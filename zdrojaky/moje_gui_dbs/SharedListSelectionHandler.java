package moje_gui_dbs;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SharedListSelectionHandler implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        /*int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting(); 
        ZobrazeniePohlavie.textArea_1.append("Event for indexes "
                      + firstIndex + " - " + lastIndex
                      + "; isAdjusting is " + isAdjusting
                      + "; selected indexes:");
*/
		
        if (lsm.isSelectionEmpty()) {
        	//HelloWorldGui.textArea_1.append(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                	int pom = i + 1;
                	//HelloWorldGui.textArea_1.setText("Oznaceny riadok: " + pom);
                	//HelloWorldGui.fruitListScrollPane(null, "Oznaceny riadok: " + pom);
                	
                }
            }
        }
        //HelloWorldGui.textArea_1.append("\n");
        //HelloWorldGui.textArea_1.setCaretPosition(HelloWorldGui.textArea_1.getDocument().getLength());

        

	}

}
