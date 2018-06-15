package org.onete.framework.bean;

import org.onete.framework.util.CastUtil;
import org.onete.framework.util.CollectionUtil;
import org.onete.framework.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数对象
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/12.
 */
public class Param {
    private List<FormParam> formParamList;
    private List<FileParam> fileParamList;
    public Param(List<FormParam> formParamList){
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }
    /*
       获取请求参数映射
     */
    public Map<String,Object> getFieldMap(){
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        if(CollectionUtil.isNotEmpty(formParamList)){
            for(FormParam formParam : formParamList){
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                if(fieldMap.containsKey(fieldName)){
                    //对于同名的请求参数用一个特殊的分隔符进行处理
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName,fieldValue);
            }
        }
        return fieldMap;
    }
    /*
       获取上传文件映射
     */
    public Map<String,List<FileParam>> getFileMap(){
        Map<String,List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
        if(CollectionUtil.isNotEmpty(fileParamList)){
            for(FileParam fileParam : fileParamList){
                String filedName = fileParam.getFileName();
                List<FileParam> fileParamList;
                if(fileMap.containsKey(filedName)){
                    fileParamList = fileMap.get(filedName);
                }else {
                    fileParamList = new ArrayList<FileParam>();
                }
                fileParamList.add(fileParam);
                fileMap.put(filedName,fileParamList);
            }
        }
        return fileMap;
    }
    /*
       获取所有上传文件
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }
    /*
       获取唯一上传文件
     */
    public FileParam getFile(String fieldName){
        List<FileParam> fileParamList = getFileList(fieldName);
        if(CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }
    /*
   验证参数是否为空
 */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
    }
    /*
       根据参数名获取String型参数值
     */
    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }
    /*
       根据参数名获取Double型参数值
     */
    public double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }
    /*
       根据参数名获取long型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }
    /*
      根据参数名获取int型参数值
    */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }
    /*
      根据参数名获取boolean型参数值
    */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }
}
