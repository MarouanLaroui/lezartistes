package com.lezartistes.tests;

import com.lezartistes.controllers.user.UserInformation;
import com.lezartistes.exceptions.FeedbackNotFoundException;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.facades.FeedbackFacade;
import com.lezartistes.facades.UserFacade;
import com.lezartistes.models.Client;
import com.lezartistes.models.Feedback;
import com.lezartistes.models.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
