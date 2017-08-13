package documentStroage.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import documentStorage.dao.DocStroageDAO;
import documentStorage.model.Document;
import documentStorage.model.IPFSFiles;
import io.ipfs.multihash.Multihash;
import ipfs.api.IPFS;
import ipfs.api.MerkleNode;
import ipfs.api.NamedStreamable;

@Service
public class IPFSRepositoryImpl implements IPFSRepository {
	
	@Autowired
	private DocStroageDAO docStroageDAO;

	IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");

	private File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}

	public IPFSFiles uploadFiles(MultipartFile[] files) {
		IPFSFiles ipfsObj = new IPFSFiles();
		List<Document> documents = new ArrayList<Document>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		System.out.println(timestamp);

		for (int i = 0; i < files.length; i++) {
			NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(convert(files[i]));
			String fileName = files[i].getOriginalFilename();
			String ContentType = files[i].getContentType();
			Document document = new Document();
			document.setFileName(fileName);
			document.setContentType(ContentType);
			document.setCreatedOn(Date.from(instant));
			
			try {
				MerkleNode addResult = ipfs.add(file);
				System.out.println(addResult.hash.toString());
				document.setHashCode(addResult.hash.toString());
				documents.add(document);
				docStroageDAO.addDocument(document); 
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		ipfsObj.setDocument(documents);
		return ipfsObj;
	}

	@Override
	public String getFile(String hashCode) {
		String response = "";
		Multihash filePointer = Multihash.fromBase58(hashCode);
		try {
			byte[] fileContents = ipfs.cat(filePointer);
			response = new String(fileContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
