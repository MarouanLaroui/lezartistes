package com.lezartistes.dao.quotation;

import com.lezartistes.dao.company.CompanyDAOPostgres;
import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Quotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuotationDAOPostgres extends QuotationDAO{

    private static QuotationDAOPostgres quotationDAOPostgres;
    private Connection coToDB;
    private CompanyDAOPostgres companyDAOPostgres;


    private QuotationDAOPostgres(Connection connection){
        this.coToDB=connection;
        this.companyDAOPostgres = new CompanyDAOPostgres(connection);
    }

    public static QuotationDAOPostgres getInstance(Connection connection){
        if(quotationDAOPostgres == null){
            quotationDAOPostgres = new QuotationDAOPostgres(connection);
        }
        return quotationDAOPostgres;
    }
    private final CompanyFacade companyFacade = CompanyFacade.getInstance();
    private Quotation resultSetToQuotation(ResultSet rs) throws SQLException {
        Quotation q = new Quotation(
                rs.getInt("company"),
                rs.getString("title"),
                rs.getInt("expert"),
                rs.getDouble("capital"),
                rs.getInt("siret_number"),
                rs.getInt("number_business_register"),
                rs.getInt("NAF"),
                rs.getDouble("total_price_TTC"),
                rs.getInt("callforproposal")
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
        ArrayList<byte[]> files = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = this.coToDB.prepareStatement("INSERT INTO quotation VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)");
            ps.setInt(1,quotation.getCompany());
            ps.setString(2,quotation.getTitle());
            ps.setInt(3,quotation.getExpert());
            ps.setDouble(4,quotation.getCapital());
            ps.setInt(5,quotation.getSiret_number());
            ps.setInt(6,quotation.getNumber_business_register());
            ps.setInt(7,quotation.getNAF());
            ps.setDouble(8,quotation.getTotal_price_TTC());
            ps.setInt(9,quotation.getcallforproposal());
            int rows = ps.executeUpdate();
            ps.close();
            System.out.println(rows);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return quotation;
    }

    public Quotation deleteQuotation(int id) throws QuotationNotFoundException{
        String sqlSelect = "DELETE FROM quotations WHERE idQuotation=?";
        PreparedStatement pstatement = null;
        try {
            pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1,id);
            int affectedRows = pstatement.executeUpdate(sqlSelect);
            if(affectedRows == 0){
                throw new QuotationNotFoundException(id);
            }
            return null;

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public List<Quotation> getQuotationByCAP(int idcap) throws QuotationNotFoundException{
        String sqlSelect = "SELECT * FROM quotations WHERE callforproposal=?";
        List<Quotation> quotations = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1, idcap);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                Quotation q = resultSetToQuotation(resultSet);
                quotations.add(q);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(quotations.isEmpty()){
            throw new QuotationNotFoundException();
        }
        return quotations;
    }


    public List<Quotation> getQuotationByCompany(int idc) throws QuotationNotFoundException{

        /**
         * Récupération des quotations associés à la company
         */
        String sqlSelect = "SELECT * FROM quotations WHERE company=?";
        List<Quotation> quotations = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1, idc);
            ResultSet resultSet = pstatement.executeQuery();

            /*Transforme toutes les lignes en feedback*/
            while(resultSet.next()){
                Quotation q = resultSetToQuotation(resultSet);
                quotations.add(q);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(quotations.isEmpty()){
            throw new QuotationNotFoundException();
        }
        return quotations;
    }
}
