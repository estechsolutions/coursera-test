package kidde.e1.bssv.JPM04016.valueobject;

import java.io.Serializable;

import kidde.e1.bssv.JM4016A.valueobject.GT4016A_PrintMessage;

import kidde.e1.bssv.JM4016A.valueobject.MOItem_Internal;

import oracle.e1.bssvfoundation.base.MessageValueObject;


/**
 * TODO: Java Doc comments for Value Object here
 */
public class CustomerDeliveryInstructionReply extends MessageValueObject implements Serializable {
    /**
     * Media Object Array <br>
     */
    private MOItem_Publish[] moItems = null;

    /**
     * Print Message
     * <p>
     * TODO: Description using Glossary Text from EnterpriseOne if appropriate.
     * </p>
     * EnterpriseOne Key Field: false <br>
     * EnterpriseOne Alias: INMG <br>
     * EnterpriseOne field length:  10 <br>
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
     */
    private String szLanguagepreference = null;

    /**
     * TODO: Default public constructor for instantiating: CustomerDeliveryInstructionReply
     */
    public CustomerDeliveryInstructionReply() {
    }
    
    public CustomerDeliveryInstructionReply(GT4016A_PrintMessage internalMO){
        
        this.setSzLanguagepreference(internalMO.getSzLanguagepreference());
        this.setSzPrintmessage1(internalMO.getSzPrintmessage1());
        
        MOItem_Publish[] moItems = new MOItem_Publish[internalMO.getMoItems().length];
         
         MOItem_Internal[] moItemArray = new MOItem_Internal[internalMO.getMoItems().length];
         moItemArray = internalMO.getMoItems();
         
         if(internalMO.getMoItems() != null)
         {
           if(internalMO.getMoItems().length > 0)
           {
             this.moItems = new MOItem_Publish[internalMO.getMoItems().length];
             for(int i=0; i<internalMO.getMoItems().length; i++)
             {
               this.moItems[i] = new MOItem_Publish();
               this.moItems[i].setMoSeqNo(moItemArray[i].getMoSeqNo());
               this.moItems[i].setSzItemName(moItemArray[i].getSzItemName());
               this.moItems[i].setSzMoType(moItemArray[i].getSzMoType());         
               this.moItems[i].setSzData(moItemArray[i].getSzData());
             }
           }
         }                
        
    }

    public void setMoItems(MOItem_Publish[] moItems) {
        this.moItems = moItems;
    }

    public MOItem_Publish[] getMoItems() {
        return moItems;
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
