package com.lezartistes.dao.callForProposal;

import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.models.CallForProposal;

import java.util.List;

public abstract class CallForProposalDAO {
    //constructor
    public CallForProposalDAO(){ super(); }

    //methods
    public abstract CallForProposal getCallForProposalById(int id);
    public abstract List<CallForProposal> getAllCallForProposal() throws CallForProposalNotFoundException;
    public abstract List<CallForProposal> getAllPostedCallForProposal() throws CallForProposalNotFoundException;
    public abstract List<CallForProposal> getCallForProposalByAuthor(int id);

    public abstract CallForProposal createCallForProposal(CallForProposal cfp);
    public abstract CallForProposal deleteCallForProposal(CallForProposal cfp);
    public abstract CallForProposal modifyCallForProposal(CallForProposal cfp);


}
