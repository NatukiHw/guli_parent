package moe.tree.eduvod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import lombok.extern.slf4j.Slf4j;
import moe.tree.eduvod.service.VodService;
import moe.tree.eduvod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
