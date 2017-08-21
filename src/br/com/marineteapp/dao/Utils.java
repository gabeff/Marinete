package br.com.marineteapp.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.marineteapp.jdbc.DataSource;

public class Utils {

	protected Connection getConnection() throws IOException, SQLException, PropertyVetoException {
		Connection conn = DataSource.getInstance().getConnection();
		return conn;
	}

	protected int getRowCount(ResultSet resultSet) {
		if (resultSet == null) {
			return 0;
		}
		try {
			resultSet.last();
			return resultSet.getRow();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			try {
				resultSet.beforeFirst();
			} catch (SQLException exp) {
				exp.printStackTrace();
			}
		}
		return 0;
	}

}
