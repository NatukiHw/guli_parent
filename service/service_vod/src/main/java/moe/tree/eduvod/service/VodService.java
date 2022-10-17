package moe.tree.eduvod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {

	public String uploadVideo(MultipartFile file);
}
