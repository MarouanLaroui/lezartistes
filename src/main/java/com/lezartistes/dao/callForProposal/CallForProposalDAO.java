package com.lezartistes.dao.callForProposal;

import com.lezartistes.exceptions.CFPIllegalChangeOfStateException;
import com.lezartistes.exceptions.CallForProposalDeleteImpossibleException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.models.CallForProposal;

import java.util.List;

public abstract class CallForProposalDAO {
    //constructor
    public CallForProposalDAO(){ super(); }

    //methods
    public abstract CallForProposal getCallForProposalById(int id);
    public abstract List<CallForProposal> getAllCallForProposal() throws CallForProposalNotFoundException;
    public abstract List<CallForProposal> getAllPostedAndOverCallForProposal() throws CallForProposalNotFoundException;
    public abstract List<CallForProposal> getCallForProposalByAuthor(String authorMail) throws CallForProposalNotFoundException;

    public abstract int updateStatusOfCallForProposal(CallForProposal cfp, String newStatus) throws CFPIllegalChangeOfStateException;
    public abstract int createCallForProposal(CallForProposal cfp);
    public abstract int updateCallForProposal(int idCFP, CallForProposal cfp) throws CFPIllegalChangeOfStateException;
    public abstract int deleteCallForProposal(CallForProposal cfp) throws CallForProposalDeleteImpossibleException;

    public abstract int getCallForProposalIdByTitle(String title);

}
