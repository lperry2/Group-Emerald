package src.model;

/**
 * A JournalEntry is the title of the journal and the content of the journal.
 *
 * @author Owen Orlic
 */
public class JournalEntry {

    /** The title of the journal entry. */
    private String myTitle;

    /** The content of the journal entry. */
    private String myContent;

    /**
     * The public constructor for the JournalEntry class.
     * @param theTitle The title of the journal entry
     * @param theContent The content of the journal entry
     */
    public JournalEntry(String theTitle, String theContent) {
        myTitle = theTitle;
        myContent = theContent;
    }

    /**
     * Returns the title of the journal entry.
     * @return myTitle The title of the journal entry
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * Sets the title of the journal entry.
     * @param theTitle The title of the journal entry
     */
    public void setTitle(String theTitle) {
        myTitle = theTitle;
    }

    /**
     * Returns the content of the journal entry.
     * @return myContent The content of the journal entry
     */
    public String getContent() {
        return myContent;
    }

    /**
     * Sets the content of the journal entry.
     * @param theContent The content of the journal entry
     */
    public void setContent(String theContent) {
        myContent = theContent;
    }

    /**
     * String representation of a journal entry that looks like
     * 'Title: Content".
     *
     * @return the journal entry
     */
    @Override
    public String toString() {
        return myTitle + ": " + myContent;
    }
}
