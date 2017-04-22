package kidde.e1.bssv.JM4016A.valueobject;

import java.io.Serializable;

import oracle.e1.bssvfoundation.base.ValueObject;

/**
 * TODO: Java Doc comments for Value Object here
 */
public class GT4016A_PrintMessage extends ValueObject implements Serializable {
    /**
     * Media Object Array <br>
     */
    private MOItem_Internal[] moItems = null;

    /**
     * Download Attachments <br>
     */
    private boolean downloadMediaObject = true;

    /**
     * Print Message
     * <p>
     * TODO: Description using Glossary Text from EnterpriseOne if appropriate.
     * </p>
     * EnterpriseOne Key Field: false <br>
     * EnterpriseOne Alias: INMG <br>
     * EnterpriseOne field length:  10 <br>
     * EnterpriseOne User Defined Code: 40/PM <br>
     */
    private String szPrintmessage1 = null;

    /**
     * Language
     * <p>
     * TODO: Description using Glossary Text from EnterpriseOne if appropriate.
     * </p>
     * EnterpriseOne Key Field: false <br>
     * EnterpriseOne Alias: LNGP <br>
     * EnterpriseOne field length:  2 <br>
     * EnterpriseOne User Defined Code: 01/LP <br>
     */
    private String szLanguagepreference = null;

    /**
     * Builds and returns the Media Object Key with the media object attributes
     */
    public String getSzMoKey() {
        String key = String.valueOf(szPrintmessage1)+"|"+String.valueOf(szLanguagepreference);
        if (key.startsWith("null|"))
        {
            key = key.substring(4, key.length());
        }
        if (key.endsWith("|null"))
        {
            key = key.substring(0, key.length() - 4);
        }
        while(key.indexOf("|null|") != -1)
        {
            key = key.replace("|null|", "||");
        }
        return key;
    }

    /**
     * Returns the Media Object name
     */
    public String getSzMoName() {
        return "GT4016A";
    }

    public void setMoItems(MOItem_Internal[] moItems) {
        this.moItems = moItems;
    }

    public MOItem_Internal[] getMoItems() {
        return moItems;
    }

    public void setDownloadMediaObject(boolean downloadMediaObject) {
        this.downloadMediaObject = downloadMediaObject;
    }

    public boolean isDownloadMediaObject() {
        return downloadMediaObject;
    }

    public void setSzPrintmessage1(String szPrintmessage1) {
        this.szPrintmessage1 = szPrintmessage1;
    }

    public String getSzPrintmessage1() {
        return szPrintmessage1;
    }

    public void setSzLanguagepreference(String szLanguagepreference) {
        this.szLanguagepreference = szLanguagepreference;
    }

    public String getSzLanguagepreference() {
        return szLanguagepreference;
    }
}
