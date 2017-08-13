package documentStorage.service;

import org.springframework.web.multipart.MultipartFile;

import documentStorage.model.IPFSFiles;

public interface DocStorageService {
	public IPFSFiles uploadFiles(MultipartFile[] files);
	
	public String getFile(String hashCode);

	public IPFSFiles getFiles();
}
