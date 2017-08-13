package documentStroage.repository;

import org.springframework.web.multipart.MultipartFile;

import documentStorage.model.IPFSFiles;

public interface IPFSRepository {

	public IPFSFiles uploadFiles(MultipartFile[] files);

	public String getFile(String hashCode);
}
