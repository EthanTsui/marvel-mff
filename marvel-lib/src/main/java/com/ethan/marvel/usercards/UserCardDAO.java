/**
 * 
 */
package com.ethan.marvel.usercards;

import java.sql.*;
import java.util.*;

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

//        udao.test();
        udao.testCollection();


        try {
            udao.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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


    public void testCollection() {
        try {
            UserCardDAO dao = new UserCardDAO();

            UserCollection collection = new UserCollection();

            collection.setTokenId("ETHAN6BcbgNDQFAnXkax");

            collection.setCollectionUId(RandomIdGenerator.generateRandomIds(20));

            collection.setSlotCard(1, dao.getUserCard("4n1VRq4Q2IUQFwITcM1g"));

            collection.setSlotCard(2, dao.getUserCard("Ldc3VJ1V8yZASqffjQyO"));

            collection.setSlotCard(3, dao.getUserCard("sywgSJOsEYvv4lknjcgV"));

            collection.setSlotCard(4, dao.getUserCard("nU1Oo3EEKYnjSx8cc0L8"));

            collection.setSlotCard(5, dao.getUserCard("2vyr7tndx7UPZHYwHwW0"));

            collection.reCalculateSkills();

            dao.upsertUserCollection(collection);

            dao.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
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


    public List<UserCollection> getAllUserCollection(String tokenId) throws SQLException {
        List<UserCollection> collections = new ArrayList<UserCollection>();
        PreparedStatement ps = getConnection().prepareStatement("select * from card_collection where token_id=? order by insert_st");

        ps.setString(1, tokenId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            UserCollection collection = new UserCollection();

            fillUserCollection(rs, collection);

            collections.add(collection);

        }

        return collections;
    }

    private void fillUserCollection(ResultSet rs, UserCollection collection) throws SQLException {
        collection.setTokenId(rs.getString("token_id"));
        collection.setCollectionUId(rs.getString("collection_uid"));

        for(int i=1;i<=5;i++) {
            String slotCard = rs.getString("slot"+i+"_id");
            if(slotCard!=null) {
                collection.setSlotCard(i, getUserCard(slotCard));
            }
        }

        for(String skillId:UserCollection.SKILL_ID) {
            float v = rs.getFloat("sk_"+skillId);
            collection.setSkill(skillId, v);
        }

        collection.setInsertSt(rs.getDate("insert_st"));

        collection.setUpdateSt(rs.getDate("update_st"));
    }

    public void upsertUserCollection(UserCollection collection) throws SQLException {
        PreparedStatement psMerge = getConnection().prepareStatement("MERGE INTO card_collection " +
                "(token_id,collection_uid,slot1_id,slot2_id,slot3_id,slot4_id,slot5_id," +
                "sk_9,sk_26,sk_25,sk_9_26,sk_9_25,sk_10,sk_27,sk_28,sk_10_27,sk_10_28," +
                "sk_4,sk_5,sk_103,sk_32,sk_36,sk_34,sk_35,sk_33,sk_11,sk_29,sk_6,sk_7," +
                "sk_19,sk_8,sk_20,update_st) KEY(token_id,collection_uid) VALUES " +
                "(?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?,?," +
                "?,?,?)");

        psMerge.setString(1, collection.getTokenId());
        psMerge.setString(2, collection.getCollectionUId());

        for(int i=1;i<=5;i++) {
            if (collection.getSlotCard(i) != null) {
                psMerge.setString(2 + i, collection.getSlotCard(i).getCardUId());
            } else {
                psMerge.setString(2 + i, null);
            }
        }

        for(int i=0;i<UserCollection.SKILL_ID.length;i++) {
            psMerge.setFloat(8 + i, collection.getSkill(UserCollection.SKILL_ID[i]));
        }


        psMerge.setTimestamp(33, new Timestamp(Calendar.getInstance().getTimeInMillis()));

        psMerge.executeUpdate();
    }


    public UserCollection getUserCollection(String collectionUId) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement("select * from card_collection where collection_uid=?");
        ps.setString(1, collectionUId);
        UserCollection collection = new UserCollection();
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            fillUserCollection(rs, collection);
        }

        collection.reCalculateSkills();

        return collection;

    }

    public List<UserCollection> getTop(int topN, String sortBy, float upperBound) throws SQLException {
        List<UserCollection> collections = new ArrayList<UserCollection>();

        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from card_collection where slot1_id is not null and slot2_id is not null and slot3_id is not null and slot4_id is not null and slot5_id is not null and "+sortBy +" <=" + upperBound+" order by "+sortBy+" desc limit "+topN);

        while(rs.next()) {
            UserCollection collection = new UserCollection();
            fillUserCollection(rs, collection);
            collections.add(collection);

        }
        return collections;
    }

    public void removeRepeatCard() throws SQLException {
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from card_collection");

        while(rs.next()) {
            UserCollection collection = new UserCollection();
            fillUserCollection(rs, collection);

            boolean modified=false;
            for(int i=1;i<=4;i++) {

                if(collection.getSlotCard(i)==null) {
                    continue;
                }

                for(int j=i+1;j<=5;j++) {
                    if(collection.getSlotCard(j)==null) {
                        continue;
                    }
                    if(collection.getSlotCard(i).getCardId()==collection.getSlotCard(j).getCardId()) {
                        collection.setSlotCard(j, null);
                        modified=true;
                    }
                }


            }
            if(modified) {
                this.upsertUserCollection(collection);
            }

        }
    }

}
