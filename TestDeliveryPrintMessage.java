package kidde.e1.bssv.JPM04016;

import java.io.ByteArrayInputStream;

import javax.swing.text.Document;

import kidde.e1.bssv.JPM04016.valueobject.CustomerDeliveryInstructionReply;
import kidde.e1.bssv.JPM04016.valueobject.CustomerDeliveryInstructionsRequest;
import kidde.e1.bssv.JPM04016.valueobject.MOItem_Publish;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.ByteArrayInputStream;

public class TestDeliveryPrintMessage {
    public static void main(String[] args) {
        CustomerDeliveryInstructionsRequest req = new CustomerDeliveryInstructionsRequest();
        req.setCustomerNumber(new Integer(70048095));
        
        CustomerDeliveryInstructionReply reply = new CustomerDeliveryInstructionReply();
        
        CustomerDeliveryInstruction mgr = new CustomerDeliveryInstruction();
        
        try{
            
          reply = mgr.getDeliveryMessage(req);  
            
          Integer numberOfMO = reply.getMoItems().length;  
          
            if (numberOfMO >0){
                MOItem_Publish[] collectionMO = new MOItem_Publish[numberOfMO];
                collectionMO = reply.getMoItems();
                for(MOItem_Publish moItem : collectionMO){
                    System.out.println("Content Type: "+moItem.getSzData().getContentType());
                    System.out.println("Data Content: "+(String) moItem.getSzData().getContent());
                    String szMOData = (String) moItem.getSzData().getContent();
                    RTFEditorKit rtfParser = new RTFEditorKit();
                    Document tmpDoc = rtfParser.createDefaultDocument();
                    byte[] b = szMOData.getBytes();
                    ByteArrayInputStream rtfBytes = new ByteArrayInputStream(b);
                    rtfParser.read(rtfBytes, tmpDoc, 0);
                    String contType = rtfParser.getContentType();
                    String txtMOData = tmpDoc.getText(0, tmpDoc.getLength());
                    System.out.println("Text Content: "+txtMOData);
                    
                }
                
                
            }
           
           
           
            
        }catch(Exception exp){
            System.out.println(exp.getMessage());
        }




    }
}
