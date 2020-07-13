package org.jit.sose.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jit.sose.config.FileConfig;
import org.jit.sose.constant.FileConstant;
import org.jit.sose.entity.FileInfo;
import org.jit.sose.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件操作工具类
 * 
 * @author: dylan
 * @date: 2019-08-01 23:43:32
 */
@Slf4j
public class FileUtil {

	/**
	 * 上传文件列表
	 * 
	 * @param fileList 文件列表
	 * @param fileType 文件类型
	 * @return 文件实体对象集合
	 */
	public static List<FileInfo> uploadFileList(MultipartFile[] fileList, String fileType) {
		List<FileInfo> FileInfoList = new ArrayList<FileInfo>();
		for (MultipartFile file : fileList) {
			// 获取原始文件名
			String originalFileNameString = file.getOriginalFilename();// 1 (1).jpg
			// 获取自定义文件名称(不包含基础上传路径):文件父目录+自定义文件名称
			String fileName = FileUtil.getFileName(originalFileNameString, fileType);
			// 获取文件页面访问路径
			String accessUrl = getAccessUrl(fileName);
			// 数据库对应实体类对象
			FileInfo f = new FileInfo();
			f.setFileName(originalFileNameString);
			f.setAccessUrl(accessUrl);
			f.setType(fileType); // 未定
			FileInfoList.add(f);

			// 根据文件名获取文件对象
			File newFile = getNewFile(fileName);
			try {
				// 写入
				file.transferTo(newFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				log.error("文件写入失败");
			}
		}
		return FileInfoList;
	}

	/**
	 * 获取自定义文件名(不包含基础上传路径)
	 * 
	 * @param file 文件对象
	 * @Param fileType 文件类型
	 * @return 文件名
	 */
	public static String getFileName(String originalFileNameString, String fileType) {
		String fileName = null;
		// 获取当前时间字符串
		String fileDateCode = DateFormatUtil.formatCode(new Date());
		// 获取文件后缀
		String suffix = originalFileNameString.substring(originalFileNameString.lastIndexOf(".") + 1);// jpg
		// 自定义允许上传的文件String数组
		String allowFileSuffix[] = null;
		// 根据文件类型获取对应枚举类型
		FileTypeEnum fileTypeEnum = FileTypeEnum.getByType(fileType);
		// 文件上传目录
		String fileParentDir = fileTypeEnum.getTypeName() + File.separator;
		// 根据文件类型获取对应的文件后缀数组
		switch (fileTypeEnum) {
		case PICTURE:
			allowFileSuffix = FileConstant.ALLOW_PICTURE_SUFFIX;
			break;
		case VIDEO:
			allowFileSuffix = FileConstant.ALLOW_VIDEO_SUFFIX;
			break;
		case TEXT:
			allowFileSuffix = FileConstant.ALLOW_TEXT_SUFFIX;
			break;
		default:
			log.error("文件类型错误");
			break;
		}
		// 判断当前文件类型(后缀)是否允许上传
		boolean b = isHasSuffix(suffix, allowFileSuffix);
		if (b) {
			// 文件名称：时间字符串+文件名全名
			fileName = fileParentDir + fileDateCode + "_" + originalFileNameString;
		} else {
			log.error("当前文件类型禁止上传");
		}
		return fileName;
	}

	/**
	 * 根据文件名获取文件对象
	 * 
	 * @param fileName
	 * @return
	 */
	public static File getNewFile(String fileName) {
		// 文件上传路径
		String uploadPath = FileConfig.UPLOAD_PATH;
		// 根据上传路径和文件名创建 文件对象
		File newFile = new File(uploadPath, fileName);
		// 获取新对象的父目录对象
		File parentFile = newFile.getParentFile();
		// 判断父目录对象是否存在
		boolean isParentFileExist = parentFile.exists();
		// 父目录不存在，创建父目录对象
		if (!isParentFileExist) {
			parentFile.mkdirs();
		}
		// 判断新对象是否存在
		boolean isNewFileExist = newFile.exists();
		// 新对象不存在，创建新对象
		if (!isNewFileExist) {
			try {
				boolean isCreateNewFile = newFile.createNewFile();
				if (!isCreateNewFile) {
					System.out.println("文件创建失败");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newFile;
	}

	/**
	 * 根据文件名(不包含基础上传路径) 获取页面访问路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getAccessUrl(String originalFileNameString) {
		return FileConfig.VIRTUAL_PATH + "/" + originalFileNameString.replace(File.separator, "/");
	}

	/**
	 * 判断文件后缀是否在自定义后缀数组中
	 * 
	 * @param suffix
	 * @param allowPicSuffix
	 * @return
	 */
	public static boolean isHasSuffix(String suffix, String allowPicSuffix[]) {
		// 后缀不为空
		if (suffix == null || "".equals(suffix) || suffix.trim().length() == 0) {
			return false;
		}
		// 判断数据中书否存在后缀。使用Arrays类中asList()方法将数组转化为List()列表
		return Arrays.asList(allowPicSuffix).contains(suffix);
	}

}
