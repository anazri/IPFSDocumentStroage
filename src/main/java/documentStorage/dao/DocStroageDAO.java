package documentStorage.dao;

import documentStorage.model.Document;
import documentStorage.model.IPFSFiles;

public interface DocStroageDAO {
	public int addDocument(Document document);
	public int deleteDocument(Document document);
	public int getDocumentsCount();
	public IPFSFiles getDocuments();
}
