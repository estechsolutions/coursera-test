package kidde.e1.bssv.JPM04016.valueobject;

import java.io.Serializable;

import kidde.e1.bssv.J5603B01.valueobject.InternalPrintMessageD5603B01A;
import kidde.e1.bssv.JM4016A.valueobject.GT4016A_PrintMessage;

import kidde.e1.bssv.JM4016A.valueobject.MOItem_Internal;

import oracle.e1.bssvfoundation.base.ValueObject;
import oracle.e1.bssvfoundation.util.E1MessageList;
import oracle.e1.bssvfoundation.util.MathNumeric;

/**
 * TODO: Java Doc comments for Value Object here
 */
public class CustomerDeliveryInstructionsRequest extends ValueObject implements Serializable {
    /**
     * Address Number
     * <p>
     * TODO: Description using Glossary Text from EnterpriseOne if appropriate.
     * </p>
     * TODO: EnterpriseOne Key Field: false <br>
     * EnterpriseOne Alias: AN8 <br>
     * EnterpriseOne field length:  8 <br>
     * EnterpriseOne decimal places: 0 <br>
     */
    private Integer customerNumber = null;

    /**
     * TODO: Default public constructor for instantiating: CustomerDeliveryInstructionsRequest
     */
    public CustomerDeliveryInstructionsRequest() {
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }
  
    public E1MessageList mapPublishToInternal(InternalPrintMessageD5603B01A internalVO){
        E1MessageList message = new E1MessageList();
        internalVO.setMnAddressNumber(new MathNumeric(this.getCustomerNumber()));
        return message;
    }
    
    public E1MessageList mapToInternal(GT4016A_PrintMessage internalMO, InternalPrintMessageD5603B01A internalPrintMsg){
        E1MessageList messages = new E1MessageList();
        
        internalMO.setSzPrintmessage1(internalPrintMsg.getSzPrintMessage1().trim());
        
        //Create the internal MOItem and populate with values
        MOItem_Internal[] moItems = new MOItem_Internal[1];
        MOItem_Internal internalMOItem = new MOItem_Internal();
        internalMOItem.setMoSeqNo(1);
        internalMOItem.setSzItemName("Text1");
        internalMOItem.setSzMoType("TEXT");
        moItems[0] = internalMOItem; 
        internalMO.setMoItems(moItems);

        return messages;
    }
    
}
