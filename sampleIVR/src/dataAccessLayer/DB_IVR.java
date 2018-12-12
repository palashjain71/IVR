package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sampleIVR.Locales.IVR_ModalClass;

public class DB_IVR {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public DB_IVR(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertIVR(IVR_ModalClass ivr) throws SQLException {
		String sql = "INSERT INTO ivr_system.ivr_locale_entrymsg (locales, locale_EntryMsg) VALUES (?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, ivr.getLocales());
		statement.setString(2, ivr.getDescription());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<IVR_ModalClass> listAllLoacles() throws SQLException {
		List<IVR_ModalClass> list_IVR = new ArrayList<>();

		String sql = "SELECT * FROM `ivr_system`.`ivr_locale_entrymsg`";
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String locale = resultSet.getString("locale");
			String locale_EntryMsg = resultSet.getString("locale_EntryMsg");
			IVR_ModalClass IVR = new IVR_ModalClass(locale, locale_EntryMsg);
			list_IVR.add(IVR);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return list_IVR;
	}

	public boolean deleteIVR(IVR_ModalClass ivr) throws SQLException {
		String sql = "DELETE FROM `ivr_system`.`ivr_locale_entrymsg` where locale = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, ivr.getLocales());
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateIVR(IVR_ModalClass ivr) throws SQLException {
		String sql = "UPDATE `ivr_system`.`ivr_locale_entrymsg` SET locale = ?, locale_EntryMsg = ?";
		sql += " WHERE locale = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, ivr.getLocales());
		statement.setString(2, ivr.getDescription());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public IVR_ModalClass getLocales(String ivrLocales) throws SQLException {
		IVR_ModalClass ivr = null;
		String sql = "SELECT * FROM book WHERE locale = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, "Locales");
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			String locale = resultSet.getString("locale");
			String locale_EntryMsg = resultSet.getString("locale_EntryMsg");
			ivr = new IVR_ModalClass(locale, locale_EntryMsg);
		}
		resultSet.close();
		statement.close();
		return ivr;
	}

}
