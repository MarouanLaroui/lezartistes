package com.lezartistes.tests;

import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.CallForProposalDeleteImpossibleException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.facades.CallForProposalFacade;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.models.CallForProposal;
import com.lezartistes.models.Client;
import com.lezartistes.models.Expert;
import com.lezartistes.models.Feedback;
        import org.junit.Test;
        import org.junit.jupiter.api.Assertions;

public class CallForProposalFacadeTest {
    @Test
    public void addFeedback() throws CallForProposalNotFoundException, CallForProposalDeleteImpossibleException {
        CallForProposalFacade cfp = CallForProposalFacade.getInstance();
        int sizeBefore = cfp.getAllCallForProposal().size();
        UserInformation.setUser(new Expert("", "", "", "", null, false));
        CallForProposal callForProposal = new CallForProposal("Title", "general", new byte[1], 1, 1);
        cfp.createCallForProposal(callForProposal);

        Assertions.assertTrue(sizeBefore < cfp.getAllCallForProposal().size());
        cfp.deleteCallForProposal(callForProposal);
    }
}
