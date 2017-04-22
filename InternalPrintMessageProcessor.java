package kidde.e1.bssv.J5603B01;

import kidde.e1.bssv.J5603B01.valueobject.InternalPrintMessageD5603B01A;

import oracle.e1.bssvfoundation.base.BSFNParameters;
import oracle.e1.bssvfoundation.base.BusinessService;
import oracle.e1.bssvfoundation.base.IContext;
import oracle.e1.bssvfoundation.connection.IConnection;
import oracle.e1.bssvfoundation.exception.BSFNServiceInvalidArgException;
import oracle.e1.bssvfoundation.exception.BSFNServiceSystemException;
import oracle.e1.bssvfoundation.services.IBSFNService;
import oracle.e1.bssvfoundation.util.E1Message;
import oracle.e1.bssvfoundation.util.E1MessageList;

/**
 * TODO: Java Doc for BusinessService
 */
public abstract class InternalPrintMessageProcessor extends BusinessService {
    /**
     * TODO: Create Java Doc for: getPrintMessageID
     * Method getPrintMessageID is used for...?
     * @param context conditionally provides the connection for the database operation and logging information
     * @param connection can either be an explicit connection or null. If null the default connection is used.
     * @param internalVO represents data that is passed to EnterpriseOne for processing TODO: complete javadoc for VO.
     * @return an E1Message containing the text of any errors or warnings that may have occurred
     */
    public static E1MessageList getPrintMessageID(IContext context, IConnection connection, InternalPrintMessageD5603B01A internalVO) {

        //Call start internal method, passing the context (which was passed from PublishedBusinessService).
        startInternalMethod(context, "getPrintMessageID", internalVO);
        //Create new message list for BusinessService processing.
        E1MessageList messages = new E1MessageList();

        //TODO: call method (created by the wizard), which then executes Business Function or Database operation.
        messages.addMessages(callGetCustomerPrintMessage(context, connection, internalVO));
        //TODO:  add messages returned from E1 processing to BusinessService message list.


        //Call finish internal method passing context.
        finishInternalMethod(context, "getPrintMessageID");

        //Call finish internal method passing context.
        return messages;
    }

    /**
     * Calls the GetCustomerPrintMessage(N5603B01) business function which has the D5603B01A datastructure.
     * @param context conditionally provides the connection for the business function call and logging information
     * @param connection can either be an explicit connection or null. If null the default connection is used.
     * @param TODO document input parameters
     * @return A list of messages if there were application errors, warnings, or informational
     * messages. Returns null if there were no messages.
     */
        private static E1MessageList callGetCustomerPrintMessage(IContext context, IConnection connection, InternalPrintMessageD5603B01A internalVO) {
        BSFNParameters bsfnParams = new BSFNParameters();

        // map input parameters from input value object
        bsfnParams.setValue("mnAddressNumber", internalVO.getMnAddressNumber());

        try {
            //get bsfnService from context
            IBSFNService bsfnService = context.getBSFNService();

            //execute business function
            bsfnService.execute(context, connection, "GetCustomerPrintMessage", bsfnParams);
        } catch (BSFNServiceInvalidArgException invalidArgEx) {
            //Create error message for Invalid Argument exception and return it in ErrorList
            E1MessageList returnMessages = new E1MessageList();
            returnMessages.addMessage(new E1Message(context, "018FIS", invalidArgEx.getMessage()));
            return returnMessages;
        }
        catch (BSFNServiceSystemException systemEx) {
            //Create error message for System exception and return it in ErrorList
            E1MessageList returnMessages = new E1MessageList();
            returnMessages.addMessage(new E1Message(context, "019FIS", systemEx.getMessage()));
            return returnMessages;
        }


        //map output parameters to output value object
        internalVO.setSzPrintMessage1((String)bsfnParams.getValue("szPrintMessage1"));

        //return any errors, warnings, or informational messages to the caller
        return bsfnParams.getE1MessageList();
    }
}
