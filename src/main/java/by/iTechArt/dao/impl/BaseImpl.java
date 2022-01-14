package by.iTechArt.dao.impl;

import by.iTechArt.dao.connection.ConnectionPool;
import by.iTechArt.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Objects;

abstract class BaseImpl {
    private static final Logger LOG = LogManager.getLogger(BaseImpl.class);
     protected Connection connection;

    public void setConnection() throws DAOException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public void closeConnection() throws SQLException, DAOException {
        ConnectionPool.getInstance().freeConnection(this.connection);}

    protected void closeResultSet(ResultSet... resultSets) {
        if (resultSets != null) {
            for (ResultSet rs : resultSets) {
                if (Objects.nonNull(rs)) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }

    protected void closeStatement(PreparedStatement... statements) {
        if (statements != null) {
            for (PreparedStatement statement : statements) {
                if (Objects.nonNull(statement)) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }

}
