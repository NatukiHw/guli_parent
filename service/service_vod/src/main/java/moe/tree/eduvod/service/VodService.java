package moe.tree.eduvod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {

	public String uploadVideo(MultipartFile file);

	public boolean deleteVideo(String videoId);

	public boolean deleteBatchVideos(List<String> videoIdList);

	public String getPlayAuth(String videoId);
}
