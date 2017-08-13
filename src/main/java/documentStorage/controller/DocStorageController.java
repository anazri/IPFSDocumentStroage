package documentStorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import documentStorage.model.api.GenericResponse;
import documentStorage.model.api.IPFSResponse;
import documentStorage.service.DocStorageService;

@RequestMapping(value = "docStorage")
@RestController
public class DocStorageController {

	@Autowired
	private DocStorageService docStorageService;

	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@PostMapping("/upload") // new annotation since 4.3
	public ResponseEntity<? extends GenericResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("test", "123");
		return new ResponseEntity<>(new IPFSResponse(this.docStorageService.uploadFiles(files)), responseHeaders,
				HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getFile/{hashCode}") // new annotation since 4.3
	public String uploadFiles(@PathVariable String hashCode) {
		return this.docStorageService.getFile(hashCode);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/getFiles") // new annotation since 4.3
	public ResponseEntity<? extends GenericResponse> getFiles() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("test", "123");
		return new ResponseEntity<>(new IPFSResponse(this.docStorageService.getFiles()), responseHeaders, HttpStatus.OK);
	}

}
