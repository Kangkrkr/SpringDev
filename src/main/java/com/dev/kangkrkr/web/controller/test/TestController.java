package com.dev.kangkrkr.web.controller.test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.kangkrkr.repositories.redis.repository.TestRedisRepository;
import com.dev.kangkrkr.repositories.redis.vo.Test;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;

@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private TestRedisRepository testRedisReposytory;

	@Autowired
	private Storage storage;
	
	@Value("${gcp.bucket.name}")
	private String bucketName;
	
	@GetMapping("/storage/{filename}")
	public ResponseEntity getTextFromFile(@PathVariable("filename") String filename) {
		Blob blob = storage.get(bucketName, filename);
		
		Path downloadPath = Paths.get("download/");
		File downloadDir  = downloadPath.toFile();
		
		if (! downloadDir.exists()) {
			downloadDir.mkdirs();
		}
		
		blob.downloadTo(downloadPath.resolve(filename));
		
		return ResponseEntity.ok(blob.toString());
	}
	
	@GetMapping("/save/{id}")
	public Test saveTest(@PathVariable("id") int id) {
		Test toSave = Test.builder()
						  .id(id)
						  .value(LocalDateTime.now().toString())
						  .build();
		
		Test saved = testRedisReposytory.save(toSave);
		
		return saved;
	}
}
