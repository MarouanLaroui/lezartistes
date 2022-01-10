package com.lezartistes.tests;

import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.models.Client;
import com.lezartistes.models.Feedback;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FeedbackFacadeTest {
    @Test
    public void addFeedback() throws FeedbackNotFoundException {
        FeedbackFacade feedback = FeedbackFacade.getInstance();
        int sizeBefore = feedback.getAllFeedbacks().size();
        UserInformation.setUser(new Client("mail", "", false));
        Feedback f = new Feedback(5, "New comments", 1);
        feedback.addFeedback(f);

        Assertions.assertTrue(sizeBefore < feedback.getAllFeedbacks().size());
        feedback.deleteFeedback(f);
    }
}
