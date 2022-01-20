package by.iTechArt.dao.connection;

import by.iTechArt.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ConnectionPool {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/library";
    private static String DB_DRIVER = "org.postgresql.Driver";
    private int maxSize;
    private int checkConnectionTimeout;

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

    private final Stack<Connection> availableConnections = new Stack<>();
    private final Set<Connection> takenConnections = new HashSet<>();

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    public synchronized void init(int newMaxSize, int newCheckConnectionTimeout, int startSize) throws DAOException {
        try {
            destroy();
            Class.forName(DB_DRIVER);
            this.maxSize = newMaxSize;
            this.checkConnectionTimeout = newCheckConnectionTimeout;
            for (int counter = 0; counter < startSize; counter++) {
                availableConnections.add(createConnection());
            }
        } catch (ClassNotFoundException | SQLException e) {

            LOG.error("It is impossible to initialize connection pool", e);
            throw new DAOException(e);
        }
    }

    public synchronized void freeConnection(Connection connection) throws DAOException {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
     ////////////////////////////////////////////
              connection.clearWarnings();
                connection.setAutoCommit(true);
                takenConnections.remove(connection);
                availableConnections.add(connection);
            }
        } catch (SQLException e1) {
            LOG.error("It is impossible to return database connection into pool", e1);
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new DAOException(e2);
            }
        }
    }

    public synchronized Connection getConnection() throws DAOException {
        Connection connection = null;
        while (connection == null) {
            try {
                if (!availableConnections.isEmpty()) {
                    connection = availableConnections.pop();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            throw new DAOException(e);
                        }
                        connection = null;
                    }
                } else if (takenConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    LOG.error("The limit of number of database connections is exceeded");
                    throw new DAOException();
                }
            } catch (SQLException e) {
                LOG.error("It is impossible to connect to a database");
                throw new DAOException(e);
            }
        }
        takenConnections.add(connection);
        return connection;
    }

    private Connection createConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        } catch (Exception e) {
            LOG.info("Error while loading JDBC Driver");
        }
        return conn;
    }

    public synchronized void destroy() throws DAOException {
        takenConnections.addAll(availableConnections);
        availableConnections.clear();
        for (Connection connection : takenConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        takenConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

}
