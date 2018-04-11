/**
 * 
 */
package com.ethan.marvel.usercards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ethan.marvel.utils.RandomIdGenerator;

/**
 * @author GBTW0011
 * created date: 2018年3月30日
 */
public class UserCardDAO {
    protected Connection conn = null;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        UserCardDAO udao = new UserCardDAO();

        udao.test();
        
        
        
    }
    
    
    public void test() {
        try {
            UserCard card = new UserCard();
//            card.setTokenId(RandomIdGenerator.generateRandomIds(20));
            card.setTokenId("hCbMiHkGoRxGz7ZdPXl2");
            
            String cardUId = RandomIdGenerator.generateRandomIds(20);
            card.setCardUId(cardUId);
            card.setCardId(31);
            card.setLevel(3);
            card.setOption1("7");
            card.setOption2("8");
            card.setOption3("9");
            card.setOption4("10");
            card.setOption5("11");
            card.setOption6("12");
            
            UserCardDAO dao = new UserCardDAO();
            dao.upsertUserCard(card);
            
            
            
            UserCard load = dao.getUserCard(cardUId);
            
            System.out.println(load.getTokenId());
            System.out.println(load.getCardUId());
            System.out.println(load.getCardId());
            System.out.println(load.getLevel());
            
            for(int i=1;i<=6;i++) {
                System.out.println(load.getOptions(i));
            }
            System.out.println(load.getInsertSt());
            System.out.println(load.getUpdateSt());

        }
        catch (Exception er) {
            er.printStackTrace();
            
        }
    }
    
    
    private Connection getConnection() throws SQLException {
        if(conn==null) {
         // db parameters
            String url = "jdbc:h2:tcp://localhost/./mffdb";
            // create a connection to the database
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(url, "sa", "");
        }
        
        return conn;
    }
    
    public void closeConnection()throws SQLException {
        if(conn!=null) {
            try {
                conn.close();
            } catch(Exception ignore) { }
        }
    }
    
    public void upsertUserCard(UserCard card) throws SQLException {
        PreparedStatement psMerge = getConnection().prepareStatement("MERGE INTO card_list (token_id,card_uid,card_id,level,option1,option2,option3,option4,option5,option6,update_st) KEY(token_id,card_uid) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        psMerge.setString(1, card.getTokenId());
        psMerge.setString(2, card.getCardUId());
        psMerge.setInt(3, card.getCardId());
        psMerge.setInt(4, card.getLevel());
        psMerge.setString(5, card.getOption1());
        psMerge.setString(6, card.getOption2());
        psMerge.setString(7, card.getOption3());
        psMerge.setString(8, card.getOption4());
        psMerge.setString(9, card.getOption5());
        psMerge.setString(10, card.getOption6());
        psMerge.setTimestamp(11, new Timestamp(Calendar.getInstance().getTimeInMillis()));
        
        psMerge.executeUpdate();
    }
    
    
    public UserCard getUserCard(String cardUId) throws SQLException {
        
        PreparedStatement ps = getConnection().prepareStatement("select * from card_list where card_uid=?");
        ps.setString(1,  cardUId);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            UserCard card = new UserCard();
            fillUserCard(card, rs);
            return card;
        }
        
        return null;
    }


    /**
     * @param card
     * @param rs
     * @throws SQLException
     */
    private void fillUserCard(UserCard card, ResultSet rs) throws SQLException {
        card.setTokenId(rs.getString("token_id"));
        card.setCardUId(rs.getString("card_uid"));
        card.setCardId(rs.getInt("card_id"));
        card.setLevel(rs.getInt("level"));
        for(int i=1;i<=6;i++) {
            card.setOptions(i, rs.getString("option"+i));
        }
        card.setInsertSt(rs.getDate("insert_st"));
        card.setUpdateSt(rs.getDate("update_st"));
    }
    
    public List<UserCard> getUserCards(String tokenId) throws SQLException {
        List<UserCard> cards = new ArrayList<UserCard>();
        PreparedStatement ps = getConnection().prepareStatement("select * from card_list where token_id=? order by update_st");
        ps.setString(1,  tokenId);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            UserCard card = new UserCard();
            fillUserCard(card,rs);
            cards.add(card);
        }
        
        
        return cards;
    }
    
    public void deleteUserCard(String cardUId) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement("delete from card_list where card_uid=?");
        ps.setString(1, cardUId);
        ps.executeUpdate();
    }




}
