package documentStorage.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

//import com.mysql.jdbc.Connection;

import documentStorage.model.Document;
import documentStorage.model.IPFSFiles;
import documentStorage.model.mapper.DocumentMapper;

@Service
public class DocStroageDAOImpl implements DocStroageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin";
	
	//
	//

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	private boolean checkTableExists() {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = getDBConnection();
			DatabaseMetaData dbm = dbConnection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "documents", null);
			if (tables.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}
	
	//
	//

	@Override
	public int addDocument(Document document) {
		int insertValue = 0;

		//jdbcTemplate.execute("DROP TABLE documents");
		boolean flag = checkTableExists();

		if (!flag) {
			jdbcTemplate .execute("CREATE TABLE documents(id SERIAL, hash_code VARCHAR(255), created_on VARCHAR(255), file_name VARCHAR(255), content_type VARCHAR(255))");
		}
		
		String sql = "Insert into documents(hash_code, created_on, file_name, content_type) values(?,?,?,?)";
		insertValue = jdbcTemplate.update(sql, new Object[] { document.getHashCode(), document.getCreatedOn(), document.getFileName(), document.getContentType() });
		System.out.println("insert val:" + insertValue);

		return insertValue;
	}

	@Override
	public int deleteDocument(Document document) {
		String sql = "delete from documents where hash_code =?";
		jdbcTemplate.update(sql, new Object[] { document.getHashCode() });
		return 0;
	}

	@Override
	public IPFSFiles getDocuments() {
		IPFSFiles ipfsObj = new IPFSFiles();
		String sql = "SELECT * FROM documents";
		List<Document> documents = jdbcTemplate.query(sql, new DocumentMapper());
		ipfsObj.setDocument(documents);
		return ipfsObj;
	}

	@Override
	public int getDocumentsCount() {
		String sql = "SELECT COUNT(*) FROM documents";
		return jdbcTemplate.queryForList(sql).size();
	}

}
