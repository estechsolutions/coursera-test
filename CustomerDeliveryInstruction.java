package kidde.e1.bssv.JPM04016;

import kidde.e1.bssv.J5603B01.InternalPrintMessageProcessor;
import kidde.e1.bssv.J5603B01.valueobject.InternalPrintMessageD5603B01A;
import kidde.e1.bssv.JM4016A.InternalDeliveryMessage;
import kidde.e1.bssv.JM4016A.valueobject.GT4016A_PrintMessage;
import kidde.e1.bssv.JPM04016.valueobject.CustomerDeliveryInstructionReply;
import kidde.e1.bssv.JPM04016.valueobject.CustomerDeliveryInstructionsRequest;

import oracle.e1.bssvfoundation.base.IContext;
import oracle.e1.bssvfoundation.base.PublishedBusinessService;
import oracle.e1.bssvfoundation.connection.IConnection;
import oracle.e1.bssvfoundation.exception.BusinessServiceException;
import oracle.e1.bssvfoundation.util.E1MessageList;

/**
 * TODO: Java Doc for PublishedBusinessService
 */
public class CustomerDeliveryInstruction extends PublishedBusinessService {
    /**
     * TODO: PublishedBusinessService Public Constructor
     */
    public CustomerDeliveryInstruction() {
    }

    /**
     * Published method for getDeliveryMessage TODO: Document method
     * Acts as wrapper method, passing null context and null connection,
     * will call protected getDeliveryMessage
     * @param vo the value object representing input data for TODO:complete javadoc
     * @return confirmVO the response data from the business process TODO:complete javadoc
     * @throws BusinessServiceException
     */
    public CustomerDeliveryInstructionReply getDeliveryMessage(CustomerDeliveryInstructionsRequest vo) throws BusinessServiceException {
        return getDeliveryMessage(null,null,vo);
    }

    /**
     * Protected method for CustomerDeliveryInstruction PublishedBusinessService. getDeliveryMessage will call make calls to BusinessService classes
     * for completing business process.
     * TODO: Comment parameters
     * @param vo the value object representing input data for TODO:complete javadoc
     * @param context conditionally provides the connection for the database operation and logging information
     * @param connection can either be an explicit connection or null. If null the default connection is used.
     * @return response value object is the data returned from the business process TODO:complete javadoc
     * @throws BusinessServiceException
     */
    protected CustomerDeliveryInstructionReply getDeliveryMessage(IContext context, IConnection connection, CustomerDeliveryInstructionsRequest vo) throws BusinessServiceException {
        //perform all work within try block, finally will clean up any connections.
        try {
            //Call start published method, passing context of null
            //will return context object so BSFN or DB operation can be called later.
            //Context will be used to indicate default transaction boundary, as well as access
            //to formatting and logging operations.
            context = startPublishedMethod(context, "getDeliveryMessage", vo);

            //Create new PublishedBusinessService messages object for holding errors and warnings that occur during processing.
            E1MessageList messages = new E1MessageList();
            //TODO: Create a new internal value object.
            InternalPrintMessageD5603B01A internalPrintMsg = new InternalPrintMessageD5603B01A();
            messages.addMessages(vo.mapPublishToInternal(internalPrintMsg));
            //TODO: Call BusinessService passing context, connection and internal VO
            messages.addMessages(InternalPrintMessageProcessor.getPrintMessageID(context, connection, internalPrintMsg));
            //TODO: Add messages returned from BusinessService to message list for PublishedBusinessService.

            GT4016A_PrintMessage internalGT4016A = new GT4016A_PrintMessage();
            messages.addMessages(vo.mapToInternal(internalGT4016A, internalPrintMsg));
            //PublishedBusinessService will send either warnings in the Confirm Value Object or throw a BusinessServiceException.
            //If messages contains errors, throw the exception
            messages.addMessages(InternalDeliveryMessage.getCustomerDeliveryMessage(context, connection, internalGT4016A));
            if (messages.hasErrors()) {
                //Get the string representation of all the messages.
                String error = messages.getMessagesAsString();
                //Throw new BusinessServiceException
                throw new BusinessServiceException(error, context);
            }

            //Exception was not thrown, so create the confirm VO from internal VO
            CustomerDeliveryInstructionReply confirmVO = new CustomerDeliveryInstructionReply(internalGT4016A);
            confirmVO.setE1MessageList(messages);
            finishPublishedMethod(context, "getDeliveryMessage");
            //return outVO, filled with return values and messages
            return confirmVO;
        } finally {
            //Call close to clean up all remaining connections and resources.
            close(context, "getDeliveryMessage");
        }
    }
}
