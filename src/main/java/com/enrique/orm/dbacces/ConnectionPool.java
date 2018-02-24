package com.enrique.orm.dbacces;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool implements Runnable {
    // Number of initial connections to make.

    private int m_InitialConnectionCount = 20;
    // A list of available connections for use.
    @SuppressWarnings("rawtypes")
    private Vector m_AvailableConnections = new Vector();
    // A list of connections being used currently.
    @SuppressWarnings("rawtypes")
	private Vector m_UsedConnections = new Vector();
    // The URL string used to connect to the database
    private String m_URLString = null;
    // The username used to connect to the database
    private String m_UserName = null;
    // The password used to connect to the database
    private String m_Password = null;
    // The cleanup thread
    private Thread m_CleanupThread = null;

    //Constructor
    @SuppressWarnings("unchecked")
	public ConnectionPool(String urlString, String user, String passwd) throws SQLException {
        // Initialize the required parameters
        m_URLString = urlString;
        m_UserName = user;
        m_Password = passwd;

        for (int cnt = 0; cnt < m_InitialConnectionCount; cnt++) {
            // Add a new connection to the available list.
            m_AvailableConnections.addElement(getConnection());
        }

        // Create the cleanup thread
        m_CleanupThread = new Thread(this);
        m_CleanupThread.start();
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(m_URLString, m_UserName, m_Password);
    }

    @SuppressWarnings("unchecked")
    public synchronized Connection checkout() throws SQLException {
        Connection newConnxn = null;

        if (m_AvailableConnections.isEmpty()) {
            // Im out of connections. Create one more.
            newConnxn = getConnection();
            // Add this connection to the "Used" list.
            m_UsedConnections.addElement(newConnxn);
            // We dont have to do anything else since this is
            // a new connection.
        } else {
            // Connections exist !
            // Get a connection object
            newConnxn = (Connection) m_AvailableConnections.lastElement();
            // Remove it from the available list.
            m_AvailableConnections.removeElement(newConnxn);
            try {
                Statement stmt = newConnxn.createStatement();
                ResultSet rs = stmt.executeQuery("select 1");
                rs.close();
                stmt.close();
                rs = null;
                stmt = null;
                
            } catch (SQLException ex) {
                newConnxn = getConnection();
            }
            
            // Add it to the used list.
            m_UsedConnections.addElement(newConnxn);
        }

        // Either way, we should have a connection object now.
        return newConnxn;
    }

    @SuppressWarnings("unchecked")
    public synchronized void checkin(Connection c) {
        if (c != null) {
            // Remove from used list.
            m_UsedConnections.removeElement(c);
            // Add to the available list
            m_AvailableConnections.addElement(c);
        }
    }

    public int availableCount() {
        return m_AvailableConnections.size();
    }

    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (m_AvailableConnections.size() > m_InitialConnectionCount) {
                        // Clean up extra available connections.
                        Connection c = (Connection) m_AvailableConnections.lastElement();
                        m_AvailableConnections.removeElement(c);

                        // Close the connection to the database.
                        c.close();
                    }

                    // Clean up is done
                }

                Logger.getLogger(com.enrique.orm.dbacces.ConnectionPool.class.getName()).log(Level.INFO, "CLEANUP : Available Connections : {0}", availableCount());

                // Now sleep for 1 minute
                Thread.sleep(60000 * 1);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(com.enrique.orm.dbacces.ConnectionPool.class.getName()).log(Level.SEVERE, "Error Sql: ", sqle);
        } catch (Exception e) {
            Logger.getLogger(com.enrique.orm.dbacces.ConnectionPool.class.getName()).log(Level.SEVERE, "Error ConnectionPool: ", e);
        }
    }
}
