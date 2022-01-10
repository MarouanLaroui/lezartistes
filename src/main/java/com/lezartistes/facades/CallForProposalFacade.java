package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.callForProposal.CallForProposalDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.CFPIllegalChangeOfStateException;
import com.lezartistes.exceptions.CallForProposalDeleteImpossibleException;
import com.lezartistes.exceptions.CallForProposalNotFoundException;
import com.lezartistes.models.CallForProposal;

import java.util.List;

public class CallForProposalFacade {
    /*attributes*/
    private static CallForProposalFacade callForProposalFacade;
    private final CallForProposalDAO callForProposalDAO;

    /*constructeur*/
    private CallForProposalFacade(){
        AbstractFactory factory = PostgresFactory.getInstance();
        this.callForProposalDAO = factory.createCallForProposalDAO();
    }

    /*methods*/
    public static CallForProposalFacade getInstance(){
        if(callForProposalFacade == null){
            callForProposalFacade = new CallForProposalFacade();
        }
        return callForProposalFacade;
    }

    public CallForProposal getCallForProposalById(int id){
        return this.callForProposalDAO.getCallForProposalById(id);
    }

    public CallForProposal getCallForProposalByTitle(String title) throws CallForProposalNotFoundException {
        return this.callForProposalDAO.getCallForProposalByTitle(title);
    }

    public List<CallForProposal> getAllCallForProposal() throws CallForProposalNotFoundException{
        return this.callForProposalDAO.getAllCallForProposal();
    }

    public List<CallForProposal> getAllPostedCallForProposal() throws CallForProposalNotFoundException{
        return this.callForProposalDAO.getAllPostedCallForProposal();
    }

    public List<CallForProposal> getAllPostedAndOverCallForProposal() throws CallForProposalNotFoundException{
        return this.callForProposalDAO.getAllPostedAndOverCallForProposal();
    }

    public List<CallForProposal> getCallForProposalByAuthor(String authorMail) throws CallForProposalNotFoundException{
        return this.callForProposalDAO.getCallForProposalByAuthor(authorMail);
    }

    //public int updateStatusOfCallForProposal(CallForProposal cfp, String newStatus) throws CFPIllegalChangeOfStateException;
    public int setStatusToDraft(CallForProposal cfp) throws CFPIllegalChangeOfStateException {
        return this.callForProposalDAO.updateStatusOfCallForProposal(cfp, "DRAFT");
    }

    public int setStatusToPosted(CallForProposal cfp) throws CFPIllegalChangeOfStateException {
        return this.callForProposalDAO.updateStatusOfCallForProposal(cfp, "POSTED");
    }

    public int setStatusToOver(CallForProposal cfp) throws CFPIllegalChangeOfStateException {
        return this.callForProposalDAO.updateStatusOfCallForProposal(cfp, "OVER");
    }

    public int setStatusToArchived(CallForProposal cfp) throws CFPIllegalChangeOfStateException {
        return this.callForProposalDAO.updateStatusOfCallForProposal(cfp, "ARCHIVED");
    }

    public int createCallForProposal(CallForProposal cfp){
        return this.callForProposalDAO.createCallForProposal(cfp);
    }

    public int updateCallForProposal(int idCFP, CallForProposal cfp) throws CFPIllegalChangeOfStateException{
        return this.callForProposalDAO.updateCallForProposal(idCFP, cfp);
    }

    public int deleteCallForProposal(CallForProposal cfp) throws CallForProposalDeleteImpossibleException{
        return this.callForProposalDAO.deleteCallForProposal(cfp);
    }


    public int getCallForProposalIdByTitle(String cfpChosen) {
        return this.callForProposalDAO.getCallForProposalIdByTitle(cfpChosen);
    }
}
