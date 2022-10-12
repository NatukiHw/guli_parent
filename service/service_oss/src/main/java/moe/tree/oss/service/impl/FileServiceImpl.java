package moe.tree.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.utils.StringUtils;
import moe.tree.oss.service.FileService;
import moe.tree.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
	@Override
	public String uploadAvatar(String tag, MultipartFile file) {
		//获取阿里云存储相关常量
		String endPoint = ConstantPropertiesUtil.END_POINT;
		String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
		String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
		String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

		String uploadUrl = null;
		try {
			OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

			InputStream inputStream = file.getInputStream();
			String filePath = "";
			if(!StringUtils.isEmpty(tag)) {
				filePath += tag + "/";
			}
			filePath += new DateTime().toString("yyyy/MM/dd");

			String original = file.getOriginalFilename();
			String fileName = UUID.randomUUID().toString();
			String fileType = original.substring(original.lastIndexOf("."));
			String newName = fileName + fileType;
			String fileUrl = filePath + "/" + newName;

			ossClient.putObject(bucketName, fileUrl, inputStream);
			ossClient.shutdown();

			uploadUrl = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return uploadUrl;
	}
}
