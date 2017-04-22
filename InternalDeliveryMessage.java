package kidde.e1.bssv.JM4016A;

import java.io.ByteArrayInputStream;

import java.util.ArrayList;
import java.util.Iterator;

import javax.activation.DataHandler;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import kidde.e1.bssv.JM4016A.valueobject.GT4016A_PrintMessage;

import kidde.e1.bssv.JM4016A.valueobject.MOItem_Internal;

import oracle.e1.bssvfoundation.base.BusinessService;
import oracle.e1.bssvfoundation.base.IContext;
import oracle.e1.bssvfoundation.base.MOInfo;
import oracle.e1.bssvfoundation.connection.IConnection;
import oracle.e1.bssvfoundation.exception.MOServiceException;
import oracle.e1.bssvfoundation.services.IMOService;
import oracle.e1.bssvfoundation.util.E1Message;
import oracle.e1.bssvfoundation.util.E1MessageList;

/**
 * TODO: Java Doc for BusinessService
 */
public abstract class InternalDeliveryMessage extends BusinessService {
    /**
     * TODO: Create Java Doc for: getCustomerDeliveryMessage
     * Method getCustomerDeliveryMessage is used for...?
     * @param context conditionally provides the connection for the database operation and logging information
     * @param connection can either be an explicit connection or null. If null the default connection is used.
     * @param internalVO represents data that is passed to EnterpriseOne for processing TODO: complete javadoc for VO.
     * @return an E1Message containing the text of any errors or warnings that may have occurred
     */
    public static E1MessageList getCustomerDeliveryMessage(IContext context, IConnection connection, GT4016A_PrintMessage internalVO) {

        //Call start internal method, passing the context (which was passed from PublishedBusinessService).
        startInternalMethod(context, "getCustomerDeliveryMessage", internalVO);
        //Create new message list for BusinessService processing.
        E1MessageList messages = new E1MessageList();

        //TODO: call method (created by the wizard), which then executes Business Function or Database operation.
        messages.addMessages(selectMediaObject(context, connection, internalVO));
        //TODO:  add messages returned from E1 processing to BusinessService message list.


        //Call finish internal method passing context.
        finishInternalMethod(context, "getCustomerDeliveryMessage");

        //Call finish internal method passing context.
        return messages;
    }

    /**
     * Calls the SELECT media object.
     * @param context conditionally provides the connection for the media object call and logging information
     * @param connection can either be an explicit connection or null. If null the default connection is used.
     * @return A list of messages if there were application errors, warnings, or informational
     * messages. Returns null if there were no messages.
     */
    private static E1MessageList selectMediaObject(IContext context, IConnection connection, GT4016A_PrintMessage internalVO) {
        //create return object
        E1MessageList returnMessages = new E1MessageList();

        //specify condition records must meet to be selected
        ArrayList result;

        ArrayList results = new ArrayList();

        MOItem_Internal[] moItems = internalVO.getMoItems();
        for(int i = 0;i<moItems.length;i++)
        {
            MOItem_Internal moItem = moItems[i];
            MOInfo moInfo = new MOInfo();
            moInfo.setSzMoName(internalVO.getSzMoName());
            moInfo.setSzMoKey(internalVO.getSzMoKey());
            moInfo.setDownloadMediaObject(internalVO.isDownloadMediaObject());
            moInfo.setMoSeqNo(moItem.getMoSeqNo());
            moInfo.setSzMoType(moItem.getSzMoType());
            moInfo.setSzItemName(moItem.getSzItemName());
            try {
                //get moService from context
                IMOService moService = context.getMOService();
                //execute mo select operation
                result = moService.select(context, connection, moInfo);
                if (result.size() > 0)
                results.addAll(result);
            } catch (MOServiceException me) {
                //TODO take some action in response to the media object exception
                returnMessages.addMessage(new E1Message(context, "007FIS", me.getMessage()));
            }
            catch (Exception e) {
                //TODO take some action in response to the media object exception
                returnMessages.addMessage(new E1Message(context, "007FIS", e.getMessage()));
            }

        }
        Iterator iterator = results.iterator();
        MOItem_Internal[] output = new MOItem_Internal[results.size()];
        int i = 0;
        while(iterator.hasNext())
        {
            MOInfo moInfo = (MOInfo)iterator.next();
            MOItem_Internal moItem = new MOItem_Internal();
            moItem.setSzItemName(moInfo.getSzItemName());
            moItem.setSzData(convertRTFtoText((DataHandler)moInfo.getSzData()));
            moItem.setMoSeqNo(moInfo.getMoSeqNo());
            moItem.setSzMoType(moInfo.getSzMoType());
            output[i] = moItem;
            i++;
        }
        //map results to internal vo
        internalVO.setMoItems(output);
        return returnMessages;
    }


    private static DataHandler convertRTFtoText(DataHandler dhMO){
        
        String txtMOData;
        CharSequence cs = "rtf";
        try{
            String strMOData = (String) dhMO.getContent();
            if (strMOData.contains(cs)){
            RTFEditorKit rtfParser = new RTFEditorKit();
            Document tmpDoc = rtfParser.createDefaultDocument();
            byte[] b = strMOData.getBytes();
            ByteArrayInputStream rtfBytes = new ByteArrayInputStream(b);
            rtfParser.read(rtfBytes, tmpDoc, 0);
            txtMOData = tmpDoc.getText(0, tmpDoc.getLength());
            }else{
                txtMOData = strMOData;
            }
        }catch(Exception exp){
            System.out.println(exp.getLocalizedMessage());
            txtMOData = "";
        }

        return new DataHandler(txtMOData,"text/xml");
    }

}
