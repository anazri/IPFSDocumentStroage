package documentStroage.docstroage.repository;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import documentStorage.model.IPFSFiles;
import documentStroage.repository.IPFSRepository;
import documentStroage.repository.IPFSRepositoryImpl;
import ipfs.api.IPFS;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IPFSRepositoryImpl.class })
// @SpringBootTest
@WebMvcTest
public class IPFSRepositoryImplTester {

	// @Rule
	// public ResourceFile res = new
	// ResourceFile("file:///Users/Sj/Desktop/res.txt");

	@Autowired
	private IPFSRepository ipfsRepository;

	@Test
	public void test() throws Exception {
//		File f = new File("/Users/Sj/Desktop/Java.txt");
//		f.createNewFile();

		MultipartFile[] files = new MultipartFile[1];
		FileInputStream inputFile = new FileInputStream("/Users/Sj/Desktop/Java.txt");
		files[0] = new MockMultipartFile("file", "Java.txt", "multipart/form-data", inputFile);

		IPFSFiles res = this.ipfsRepository.uploadFiles(files);
		int value = 1;

		System.out.println(res.getDocument().get(0).getHashCode());
		
		String text = this.ipfsRepository.getFile(res.getDocument().get(0).getHashCode());
		System.out.println(text);

		assertTrue("Error, random is too high", res.getDocument().size() == value);
		// assertEquals(message, expected, actual);
		// assertTrue(res.getFile().exists());
	}

	@Test
	public void testGetFile() throws Exception {
		String text = this.ipfsRepository.getFile("QmbFMke1KXqnYyBBWxB74N4c5SBnJMVAiMNRcGu6x1AwQH");
		System.out.println(text);
	}
}
