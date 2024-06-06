package src.view;

import src.model.FileGroup;
import src.model.SingleFile;
import src.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FilePage extends AbstractPage implements PropertyChangeListener {

    private FileGroup myCurrentFiles;

    private String myProjectName;

    private JFileChooser myFileChooser;

    /**
     * Sends parameters to the super class constructor and calls setup().
     * Also instantiates myCurrentBudget.
     *
     * @author Owen Orlic
     * @param theUser user using the application
     * @param theProjectName just the name of the project, not the pathname
     * @param theType type of txt file we need
     */
    public FilePage(User theUser, String theProjectName, String theType) {
        super(theUser, theProjectName, theType);
        myCurrentFiles = myCurrentProject.getFiles();
        myProjectName = theProjectName;
        if (myProjectName.charAt(0) == '~') {
            myProjectName = myProjectName.substring(1);
        }
        myFileChooser = new JFileChooser();
        setup();
    }

    /**
     * Calls the other individual setup methods and makes the JFrame visible.
     *
     * @author Owen Orlic
     */
    private void setup() {
        setupHeader();
        setupFiles();
        setupButtons();
        this.setVisible(true);
    }

    /**
     * Creates a header that shows the files in the form:
     * Files for projectName.
     */
    private void setupHeader() {

        myTitleLabel = new JLabel("Files for " + myProjectName);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        myTitlePanel = new JPanel();
        myTitlePanel.add(myTitleLabel);
        this.add(myTitlePanel, BorderLayout.NORTH);
    }

    /**
     * Displays the individual files about the frame.
     */
    private void setupFiles() {
        //will show if there are no entries yet
        //String allEntries = "There are currently no journal entries for this project!";
        myContentPanel = new JPanel(new GridLayout(6, 5, 1, 1));
        //if there are entries, list them
        if (myCurrentFiles.getFiles().size() != 0.0) {
            ArrayList<SingleFile> files = myCurrentFiles.getFiles();
            for (int i = 0; i < files.size(); i++) {
                JButton btn = new JButton(files.get(i).getName());
                int finalI = i;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent theEvent) {
                        try {
                            Desktop.getDesktop().open(files.get(finalI).getFile());
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                });
                myContentPanel.add(btn);
            }
//            allEntries = "<html>";
//            ArrayList<SingleFile> files = myCurrentFiles.getFiles();
//            for (int i = 0; i < files.size(); i++) {
//                allEntries += entries.get(i).toString() + "<br/>"; //<br/> is line break for JLabel
//            }
//            allEntries += "</html>";
        }
        //myContentLabel = new JLabel(allEntries);
        //myContentPanel = new JPanel(new GridLayout(6, 5, 0, 1));

        //myContentPanel.add(myContentLabel);
        this.add(myContentPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the add, edit, delete, and save buttons.
     *
     * @author Owen Orlic
     */
    private void setupButtons() {
        myButtonPanel = new JPanel();
        JButton addBtn = new JButton("Add Item");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./");
                int result = fileChooser.showOpenDialog(null);
                while (result != JFileChooser.APPROVE_OPTION) {
                    result = fileChooser.showOpenDialog(null);
                }
                File file = fileChooser.getSelectedFile();
                String[] split = file.getName().split("/");
                String fileName = split[split.length - 1];
                //File file = fileChooser.getSelectedFile();
                myCurrentFiles.addFile(fileName, file);
                System.out.println(file.toString());
                writeFiles();
            }
        });
        myButtonPanel.add(addBtn, new FlowLayout());

        JButton editBtn = new JButton("Edit Item");
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EditSelectionFrame(myCurrentBudget);
//                writeExpenses();
                //openEditSelection(myCurrentBudget);
                String title = "Please Select an Expense to Edit.";
                FileSelectionFrame frame = new FileSelectionFrame(myCurrentFiles, title, FileSelectionFrame.EDIT_OPTION);
                frame.addPropertyChangeListener(FilePage.this);
                writeFiles();

            }
        });
        myButtonPanel.add(editBtn, new FlowLayout());

        JButton deleteBtn = new JButton("Delete Item");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = "Please Select an Expense to Delete.";
                FileSelectionFrame frame = new FileSelectionFrame(myCurrentFiles, title, FileSelectionFrame.DELETE_OPTION);
                frame.addPropertyChangeListener(FilePage.this);
                writeFiles();

            }
        });
        myButtonPanel.add(deleteBtn, new FlowLayout());

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCurrentUser.save();
            }
        });
        myButtonPanel.add(saveBtn, new FlowLayout());

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilePage.this.dispose();
            }
        });
        myButtonPanel.add(backBtn, new FlowLayout());

        this.add(myButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Used for updating the page when journal info has changed.
     *
     * @author Owen Orlic
     */
    private void writeFiles() {
        String allEntries = "<html>";
        //ArrayList<SingleFile> files = myCurrentFiles.getFiles();

        //remove labels
        myTitlePanel.removeAll();
        myContentPanel.removeAll();

        if (myCurrentFiles.getFiles().size() != 0.0) {
            ArrayList<SingleFile> files = myCurrentFiles.getFiles();
            for (int i = 0; i < files.size(); i++) {
                JButton btn = new JButton(files.get(i).getName());
                int finalI = i;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent theEvent) {
                        try {
                            Desktop.getDesktop().open(files.get(finalI).getFile());
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                });
                myContentPanel.add(btn);
            }
        }

        //myContentLabel = new JLabel(allEntries);
        myTitleLabel = new JLabel("Files for " + myProjectName);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        //add everything back
        myTitlePanel.add(myTitleLabel);
        //myContentPanel.add(myContentLabel);
        this.add(myContentPanel, BorderLayout.CENTER);
        this.add(myTitlePanel, BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("repaintPageFileEdit")) {
            myCurrentFiles.editFile((String) theEvent.getOldValue(), (File) theEvent.getNewValue());
            writeFiles();
        } else if (theEvent.getPropertyName().equals("repaintPageFileDelete")) {
            myCurrentFiles.deleteFile((String) theEvent.getOldValue());
            writeFiles();
        }
    }
}
