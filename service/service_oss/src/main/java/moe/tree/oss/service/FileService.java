package moe.tree.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String uploadAvatar(String tag, MultipartFile file);
}
