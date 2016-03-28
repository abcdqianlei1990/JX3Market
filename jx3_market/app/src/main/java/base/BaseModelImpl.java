package base;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-03-28.19:22
 * class description:
 */
public abstract class BaseModelImpl {

    public static final String USER_TABLE_NAME = "user_t";
    /**
     * 用户信息验证
     * @return 返回验证结果code,成功返回0，未注册返回1，信息错误返回2（用户名或密码错误）
     */
    public void verifyUser(UserInfo info){};
}
