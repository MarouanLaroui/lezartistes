package com.lezartistes.dao.quotation;

import com.lezartistes.dao.company.CompanyDAOPostgres;
import com.lezartistes.exceptions.QuotationNotFoundException;
import com.lezartistes.facades.CompanyFacade;
import com.lezartistes.models.Quotation;

import java.sql.*;
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
                rs.getInt("idcompany"),
                rs.getString("title"),
                rs.getString("expert"),
                rs.getDouble("capital"),
                rs.getInt("siret_number"),
                rs.getInt("number_business_register"),
                rs.getInt("NAF"),
                rs.getDouble("total_price_TTC"),
                rs.getInt("callforproposal")
        );
        q.setId(rs.getInt("idquotation"));
        q.setStatusQuotation(rs.getString("statusquotation").toUpperCase().trim());
        return q;

    }

    public List<Quotation> getAllQuotation() throws QuotationNotFoundException {
        List<Quotation> quotations = new ArrayList<>();
        String sqlSelect = "SELECT * FROM quotations";

        try {
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            ResultSet rs = pstatement.executeQuery();

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
            ps = this.coToDB.prepareStatement("INSERT INTO quotations(idcompany,title,expert,capital,siret_number,number_business_register,NAF,total_price_ttc,callforproposal,statusquotation) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)");
            ps.setInt(1,quotation.getCompany());
            ps.setString(2,quotation.getTitle());
            ps.setString(3,quotation.getExpert());
            ps.setDouble(4,quotation.getCapital());
            ps.setInt(5,quotation.getSiret_number());
            ps.setInt(6,quotation.getNumber_business_register());
            ps.setInt(7,quotation.getNAF());
            ps.setDouble(8,quotation.getTotal_price_TTC());
            ps.setInt(9,quotation.getcallforproposal());
            ps.setString(10,quotation.getStatusQuotation());
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
        String sqlSelect = "SELECT * FROM quotations WHERE idcompany=?";
        List<Quotation> quotations = new ArrayList<>();

        try{
            PreparedStatement pstatement = this.coToDB.prepareStatement(sqlSelect);
            pstatement.setInt(1, idc);
            ResultSet resultSet = pstatement.executeQuery();

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

    public int setStatusQuotation(Quotation quotation, String status){
        int affectRows = 0;
        String sqlInsert = "UPDATE quotations " +
                "SET statusquotation=? " +
                "WHERE idquotation=?";
        try{
            PreparedStatement pstmt = this.coToDB.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,status.toUpperCase().trim());
            pstmt.setInt(2,quotation.getId());

            affectRows = pstmt.executeUpdate();
            pstmt.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectRows;
    };
}
