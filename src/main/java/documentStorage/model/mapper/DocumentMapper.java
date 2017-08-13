package documentStorage.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import documentStorage.model.Document;

public class DocumentMapper implements RowMapper<Document> {
	
	public Document mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Document document = new Document();
		document.setHashCode(resultSet.getString("hash_code"));
		document.setCreatedOn(resultSet.getDate("created_on"));
		document.setFileName(resultSet.getString("file_name"));
		document.setContentType(resultSet.getString("content_type"));
		return document;
	}
}
