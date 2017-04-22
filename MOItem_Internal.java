package kidde.e1.bssv.JM4016A.valueobject;

import java.io.Serializable;

import javax.activation.DataHandler;

import oracle.e1.bssvfoundation.base.ValueObject;

/**
 * TODO: Java Doc comments for Value Object here
 */
public class MOItem_Internal extends ValueObject implements Serializable {
    /**
     * Media Object Attachment Type <br>
     */
    private String szMoType = null;

    /**
     * Media Object Attachment File Name <br>
     */
    private String szItemName = null;

    /**
     * Media Object Sequence Number <br>
     */
    private int moSeqNo = 0;

    /**
     * Media Object Data <br>
     */
    private DataHandler szData = null;

    /**
     * TODO: Default public constructor for instantiating: MOItem_Internal
     */
    public MOItem_Internal() {
    }

    /**
     * TODO: Default public constructor for instantiating: MOItem_Internal
     */
    public MOItem_Internal(String szMoType) {
        this.szMoType = szMoType;
    }

    /**
     * TODO: Default public constructor for instantiating: MOItem_Internal
     */
    public MOItem_Internal(int moSeqNo) {
        this.moSeqNo = moSeqNo;
    }

    /**
     * TODO: Default public constructor for instantiating: MOItem_Internal
     */
    public MOItem_Internal(String szMoType, String szItemName, DataHandler szData) {
        this.szMoType = szMoType;
        this.szItemName = szItemName;
        this.szData = szData;
    }

    public void setSzMoType(String szMoType) {
        this.szMoType = szMoType;
    }

    public String getSzMoType() {
        return szMoType;
    }

    public void setSzItemName(String szItemName) {
        this.szItemName = szItemName;
    }

    public String getSzItemName() {
        return szItemName;
    }

    public void setMoSeqNo(int moSeqNo) {
        this.moSeqNo = moSeqNo;
    }

    public int getMoSeqNo() {
        return moSeqNo;
    }

    public void setSzData(DataHandler szData) {
        this.szData = szData;
    }

    public DataHandler getSzData() {
        return szData;
    }
}
