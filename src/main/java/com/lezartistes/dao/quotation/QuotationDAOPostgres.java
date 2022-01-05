package com.lezartistes.dao.quotation;

import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.exceptions.ReportNotFoundException;
import com.lezartistes.models.Quotation;
import com.lezartistes.models.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuotationDAOPostgres extends QuotationDAO{

    private static QuotationDAOPostgres quotationDAOPostgres;
    private Connection coToDB;

    private QuotationDAOPostgres(Connection connection){
        this.coToDB=connection;
    }

    public static QuotationDAOPostgres getInstance(Connection connection){
        if(quotationDAOPostgres == null){
            quotationDAOPostgres = new QuotationDAOPostgres(connection);
        }
        return quotationDAOPostgres;
    }

    private Quotation resultSetToQuotation(ResultSet rs) throws SQLException {
        Quotation q = new Quotation(
                rs.getString("company"),
                rs.getString("title"),
                rs.getString("expert"),
                rs.getDouble("capital"),
                rs.getInt("siret_number"),
                rs.getInt("number_business_register"),
                rs.getInt("NAF"),
                rs.getDouble("total_price_TTC")
        );
        q.setId(rs.getInt("id"));
        return q;

    }

    public List<Quotation> getAllQuotation() throws QuotationNotFoundException {
        List<Quotation> quotations = new ArrayList<>();
        String sqlSelect = "SELECT * FROM quotations";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le client si trouvé, rempli le tableau*/
            while (rs.next()){
                quotations.add(this.resultSetToQuotation(rs));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*If empty return not found exception*/
        if(quotations.isEmpty()){
            throw new QuotationNotFoundException();
        }
        return quotations;

    }

    public Quotation getQuotationById(int id) throws QuotationNotFoundException {
        Quotation quotation = null;
        String sqlSelect = "SELECT * FROM quotations WHERE idQuotation=?";

        try {

            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            ResultSet rs = pstatement.executeQuery();

            /*Renvoie le rapport si trouvé, exception sinon*/
            if(rs.next()){
                quotation = this.resultSetToQuotation(rs);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*Si report vide*/
        if(quotation == null){
            throw new QuotationNotFoundException(id);
        }
        return quotation;
    }

    public Quotation createQuotation(Quotation quotation){

    }

    public Quotation deleteQuotation(int id) throws QuotationNotFoundException{

    }
}
