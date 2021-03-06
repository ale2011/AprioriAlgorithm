package edu.njit.cs634.apriori.external;

import edu.njit.cs634.apriori.gui.GUI;
import edu.njit.cs634.apriori.helper.Mapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DatabaseUtils.java
 * This class contains methods that interact with the database
 * 
 * @author Ashley Le
 * @version 20180220
 */
public class DatabaseUtils 
{
    public static Connection connection;
    final private String ENDPOINT = "alestore1.cxuvb3hzawez.us-east-1.rds.amazonaws.com";
    final private String PORTNUMBER = "3306";
    final private String USERNAME = "ale";
    final private String PASSWORD = "Computer123#";    
    
    public Statement statement;
    public PreparedStatement prep_statement;
    public ResultSet resultSet;
    
    public ArrayList <String []> transactions = new ArrayList <String []> ();
    
    public DatabaseUtils()
    {
        loadDriver();
        connectToDatabase();    
    }
    
    /**
     * Load mysql driver to this program
     */
    public void loadDriver() {      
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            GUI.fileDir.append("JDBC driver are successfully loaded.\n");  
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Connect to the database
     */
    public void connectToDatabase() {
        try {
            GUI.fileDir.append("Connecting to: \n jdbc:mysql://alestore1.cxuvb3hzawez.us-east-1.rds.amazonaws.com:3306/alestore1\n\n");    
            
            //connect = DriverManager.getConnection(url + "?user=" + db_username + "&password=" + db_password);
            connection = DriverManager.getConnection("jdbc:mysql://" + ENDPOINT + ":" + PORTNUMBER + 
                                                    "/alestore1?user=" + USERNAME + "&password=" + PASSWORD);
            
            GUI.fileDir.append("Database connected...\n");    
            statement = connection.createStatement();
            getTransactions();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Execute a query on the database from the user's query
     */
    public void getTransactions() throws SQLException
    {
        for(int index = 1; index <= 5; index++)
        {
            String toTempFile = "";
            String query = "SELECT alestore" + index + ".transactions.idtransactions, alestore" + index + ".items.description \n" +
                                "FROM alestore" + index + ".items, alestore" + index + ".transactions \n" +
                                "WHERE alestore" + index + ".items.iditems = alestore" + index + ".transactions.purchased_item\n" +
                                "ORDER BY alestore" + index + ".transactions.idtransactions ;";
            
            resultSet = statement.executeQuery(query);  
            
            String item = "";
            
            GUI.fileDir.append("Processing transactions from alestore" + index + " database...");  
            GUI.transactionTextArea.append("Transactions from alestore" + index + " database: \n");
            
            String [] trans = new String [20];            
            int prevID = 0;
            
            while(resultSet.next())
            {
                int newID = resultSet.getInt("idtransactions");
                
                if(newID != prevID)
                {
                    item = resultSet.getString("description") + " ";
                    prevID = newID;
                }
                else
                {
                    // newID = prevID 
                    // same transaction
                    item += resultSet.getString("description") + " ";
                }
                trans[newID-1] = item;                
            }
            
            for(String items : trans)
            {
                GUI.transactionTextArea.append(items + "\n");
            }
            GUI.transactionTextArea.append("\n");
            
            transactions.add(trans);
            GUI.fileDir.append(" Completed!\n"); 
            
            for(String items : trans)
            {
                toTempFile += items + "\n";
            }
            
            toTempFile = toTempFile.replace("null", "");
            toTempFile = Mapping.getLineMapping(toTempFile);
            Reader.writeToTempFile(toTempFile, index-1);
        }      
        
        connection.close();
        GUI.fileDir.append("\nRetrieved all transactions from database.\n\nConnection is closed.\n\nEnter the Support and Confidence percentage and then click Run Apriori Algorithm to begin.");     
        
    }
}
