package org.onete.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.onete.framework.bean.FileParam;
import org.onete.framework.bean.FormParam;
import org.onete.framework.bean.Param;
import org.onete.framework.util.CollectionUtil;
import org.onete.framework.util.FileUtil;
import org.onete.framework.util.StreamUtil;
import org.onete.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传助手类
 * 封装Apache Commons FileUpload
 *
 * Created by wangcheng  on 2018/6/15.
 */
public final class UploadHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
    /*
       Apache Commons FileUpload提供的Servlet文件上传对象
     */
    private static ServletFileUpload servletFileUpload;
    /*
       初始化
     */
    public static void init(ServletContext servletContext){
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(
                new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD,repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if(uploadLimit != 0){
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }
    /*
       判断请求是否为multipart类型
       只有在上传文件时对应的请求才时multipart类型，可以用来判断是否为文件上传请求
     */
    public static boolean isMultipart(HttpServletRequest request){
        return ServletFileUpload.isMultipartContent(request);
    }
    /*
       创建请求对象
     */
    public static Param createParam(HttpServletRequest request) throws IOException{
        List<FormParam> formParamList = new ArrayList<FormParam>();
        List<FileParam> fileParamList = new ArrayList<FileParam>();
        try {
            Map<String,List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
            if(CollectionUtil.isNotEmpty(fileItemListMap)){
                for(Map.Entry<String,List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();
                    if(CollectionUtil.isNotEmpty(fileItemList)){
                        for(FileItem fileItem : fileItemList){
                            if(fileItem.isFormField()){
                                String fieldValue = fileItem.getString("UTF-8");
                                formParamList.add(new FormParam(fieldName,fieldValue));
                            }else {
                                String fileName = FileUtil.getRealFileName(
                                        new String(fileItem.getName().getBytes(),"UTF-8"));
                                if(StringUtil.isNotEmpty(fieldName)){
                                    long fileSize = fileItem.getSize();
                                    String contentType = fileItem.getContentType();
                                    InputStream inputStream = fileItem.getInputStream();
                                    fileParamList.add(new FileParam(fieldName,fileName,fileSize,contentType,inputStream));
                                }
                            }
                        }
                    }
                }
            }
        }catch (FileUploadException e){
            LOGGER.error("create param failure",e);
            throw new RuntimeException(e);
        }
        return new Param(formParamList,fileParamList);
    }
    /*
       上传文件
     */
    public static void uploadFile(String basePath,FileParam fileParam){
        try {
            if(fileParam != null){
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtil.copyStream(inputStream,outputStream);
            }
        }catch (Exception e){
            LOGGER.error("upload file failure",e);
            throw new RuntimeException(e);
        }
    }
    /*
       批量上传文件
     */
    public static void uploadFile(String basePath,List<FileParam> fileParamList){
        try {
            if(CollectionUtil.isNotEmpty(fileParamList)){
                for(FileParam fileParam : fileParamList){
                    uploadFile(basePath,fileParam);
                }
            }
        }catch (Exception e){
            LOGGER.error("upload file failure",e);
            throw new RuntimeException(e);
        }
    }
}
