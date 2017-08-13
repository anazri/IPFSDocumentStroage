package documentStorage.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import documentStorage.dao.DocStroageDAO;
import documentStorage.model.Document;
import documentStorage.model.IPFSFiles;
import documentStroage.repository.IPFSRepository;

@Service
public class DocStorageServiceImpl implements DocStorageService {
	
	@Autowired
	private IPFSRepository ipfsRepository;
	
	@Autowired
	private DocStroageDAO docStroageDAO;

	@Override
	public IPFSFiles uploadFiles(MultipartFile[] files) {
		if(files != null && files.length>0){
			return this.ipfsRepository.uploadFiles(files);
		}
		return new IPFSFiles();
	}
	
	@Override
	public String getFile(String hashCode){
		String responseObj = "";
		if(StringUtils.isNotEmpty(hashCode)){
			return this.ipfsRepository.getFile(hashCode);
		}
		return responseObj;
	}

	@Override
	public IPFSFiles getFiles() {
		return this.docStroageDAO.getDocuments();
	}

}
