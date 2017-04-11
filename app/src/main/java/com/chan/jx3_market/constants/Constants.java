package com.chan.jx3_market.constants;

/**
 * Created by qianlei on 2016-03-28.16:34
 * class description:
 */
public interface Constants {

    int LOGIN_SUCCESS = 0;
    int USER_EXIST = 3;
    int USER_NOT_EXIST = 1;
    int NAME_OR_PWD_ERR = 2;
    int NORMAL = 4;

    //发布信息类型
    int PUBLISH_INFO_TYPE_ACCOUNT = 0;
    int PUBLISH_INFO_TYPE_GOLD = 1;
    int PUBLISH_INFO_TYPE_SERVICE = 2;
    int PUBLISH_INFO_TYPE_OTHER = 3;

    //每个列表加载20条数据
    int PAGE_SIZE = 20;

    //===============Bmob request err code======================
//    9001	AppKey is Null, Please initialize BmobSDK.	Application Id为空，请初始化.
//9002	Parse data error	解析返回数据出错
//9003	upload file error	上传文件出错
//9004	upload file failure	文件上传失败
//9005	A batch operation can not be more than 50	批量操作只支持最多50条
//9006	objectId is null	objectId为空
//9007	BmobFile File size must be less than 10M.	文件大小超过10M
//9008	BmobFile File does not exist.	上传文件不存在
//9009	No cache data.	没有缓存数据
//9010	The network is not normal.(Time out)	网络超时
//9011	BmobUser does not support batch operations.	BmobUser类不支持批量操作
//9012	context is null.	上下文为空
//9013	BmobObject Object names(database table name) format is not correct.	BmobObject（数据表名称）格式不正确
//9014	第三方账号授权失败	第三方账号授权失败
//9015	其他错误均返回此code	其他错误均返回此code
//9016	The network is not available,please check your network!	无网络连接，请检查您的手机网络.
//9017	与第三方登录有关的错误，具体请看对应的错误描述	与第三方登录有关的错误，具体请看对应的错误描述
//9018	参数不能为空	参数不能为空
//9019	格式不正确：手机号码、邮箱地址、验证码	格式不正确：手机号码、邮箱地址、验证码
    int APP_KEY_ERR = 9001;
    int PARSE_DATA_ERR = 9002;
    int UPLOAD_FILE_ERR = 9003;
    int UPLOAD_FILE_FAILURE = 9004;
    int OPERATION_LIMIT = 9005;
    int OBJECT_ID_NULL = 9006;
    int FILE_SIZE_LIMIT = 9007;
    int FILE_UNDEFINE = 9008;
    int NO_CACHE_DATA = 9009;
    int TIME_OUT = 9010;
    int OPERATIONS_NOT_SUPPORT = 9011;
    int CONTEXT_IS_NULL = 9012;
    int OBJECT_FORMAT_ERR = 9013;
    int LOGIN_CERTIFICATE_ERR = 9014;
    int DEFAULT_ERR_CODE = 9015;
    int NETWORK_UNAVAILABLE = 9016;
    int LOGIN_ERR = 9017;
    int PARAMS_NULL = 9018;
    int FORMAT_ERR = 9019;

    /**
     * 1-成男, 2-成女,3-正太,4-萝莉
     */
    interface BodyType{
        int male = 1;
        int female = 2;
        int boy = 3;
        int girl = 4;
    }
}
