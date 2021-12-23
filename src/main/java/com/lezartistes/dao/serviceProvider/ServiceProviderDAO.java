package com.lezartistes.dao.serviceProvider;

import com.lezartistes.models.Expert;

import java.io.Serializable;

public abstract class ServiceProviderDAO implements Serializable {

    public ServiceProviderDAO() {super();}

    public abstract int createServiceProvider(Expert e);

    public abstract Expert getServiceProviderByEmail(String mail);
}
