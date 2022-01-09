package com.lezartistes.dao.expert;

import com.lezartistes.exceptions.ExpertNotFoundException;
import com.lezartistes.models.Expert;

import java.io.Serializable;
import java.util.List;

public abstract class ExpertDAO implements Serializable {

    public ExpertDAO(){}

    public abstract List<Expert> getAllExperts() throws ExpertNotFoundException;

    public abstract Expert getExpertById(int id) throws ExpertNotFoundException;

    public abstract int createExpert(Expert expert);

    public abstract Expert getExpertByEmail(String mail);
}
