package moe.tree.eduvod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import moe.tree.eduvod.service.VodService;
import moe.tree.eduvod.utils.ConstantPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class VodServiceImpl implements VodService {
	@Override
	public String uploadVideo(MultipartFile file) {
		String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
		String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
		String regionId = ConstantPropertiesUtil.REGION_ID;
		String storageLocation = ConstantPropertiesUtil.STORAGE_LOCATION;

		String fileName = file.getOriginalFilename();
		String title = fileName.substring(0, fileName.lastIndexOf("."));

		try {
			InputStream inputStream = file.getInputStream();
			UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
			request.setApiRegionId(regionId);
			request.setStorageLocation(storageLocation);
			UploadVideoImpl uploader = new UploadVideoImpl();
			UploadStreamResponse response = uploader.uploadStream(request);
			if (response.isSuccess()) {
				return response.getVideoId();
			} else {
				log.error("VideoId=" + response.getVideoId() + ", ErrorCode=" + response.getCode() + ", ErrorMessage=" + response.getMessage());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteVideo(String videoId) {
		DefaultAcsClient client = initVodClient();
		DeleteVideoRequest request = new DeleteVideoRequest();
		request.setVideoIds(videoId);
		try {
			client.getAcsResponse(request);
			return true;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBatchVideos(List<String> videoIdList) {
		DefaultAcsClient client = initVodClient();
		DeleteVideoRequest request = new DeleteVideoRequest();
		request.setVideoIds(StringUtils.join(videoIdList, ","));
		try {
			client.getAcsResponse(request);
			return true;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getPlayAuth(String videoId) {
		if(videoId == null) {
			return null;
		}
		DefaultAcsClient client = initVodClient();
		GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
		request.setVideoId(videoId);
		try {
			GetVideoPlayAuthResponse response = client.getAcsResponse(request);
			String playAuth = response.getPlayAuth();
			return playAuth;
		} catch (ClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	public DefaultAcsClient initVodClient() {
		String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
		String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
		String regionId = ConstantPropertiesUtil.REGION_ID;
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		return client;
	}
}
