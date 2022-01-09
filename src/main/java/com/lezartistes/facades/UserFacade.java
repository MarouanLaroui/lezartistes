package com.lezartistes.facades;

import com.lezartistes.dao.AbstractFactory;
import com.lezartistes.dao.UserDAO;
import com.lezartistes.database.PostgresFactory;
import com.lezartistes.exceptions.UserNotFoundException;
import com.lezartistes.models.User;

public class UserFacade {
    /*attribute*/

    ClientFacade clientFacade = ClientFacade.getInstance();
    ExpertFacade expertFacade = ExpertFacade.getInstance();

    /*constructor*/
    /*methods*/

    /**
     * Given an email and a password, check if the credential correspond to a user. If yes, the function returns it, otherwise it throws an excpetion
     * @param mail: String is the mail to check
     * @param password: String is the password to check
     * @return the user if the pair (username, password) exists in the database
     * @throws UserNotFoundException if the pair is not found
     */
    public User login (String mail, String password) throws UserNotFoundException{

        AbstractFactory factory = PostgresFactory.getInstance();
        //UserDAO dao = factory.createUserDAO();
        //User u = dao.getUserByMail(mail);

        User u = clientFacade.getClientByEmail(mail);
        if (u == null) { //On le chercher parmis les clients
            System.out.println("On ne le trouve pas parmis les clients");
            u = expertFacade.getExpertByEmail(mail); //Sinon parmis les SP
        }

        /*Check if the user credentials are the same as in the db, returns Exceptions if not found, or not matching*/
        if( (u != null) && compareCredentials(mail, password, u)) {
            System.out.println("Credentials are matching !");
            return u;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    /**
     * Compare mail and password to user's informations
     * @param mail is the mail to check
     * @param password is the passowrd to check
     * @param user is the User we want to compare the informations with
     * @return true if the informations matches, false otherwise
     */
    public boolean compareCredentials (String mail, String password, User user) {
        return (user.getMail().equals(mail)) && (user.getPassword().equals(user.encrypt(password)));
    }
}
