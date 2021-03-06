package xyz.weiworld.weixin.thread;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.weiworld.weixin.pojo.Token;
import xyz.weiworld.weixin.util.CommonUtil;
import xyz.weiworld.weixin.util.TokenUtil;

/**
* 类名: TokenThread </br>
* 描述: 定时获取微信access_token的线程 </br>
* 开发人员： souvc </br>
* 创建时间：  Oct 6, 2015 </br>
* 发布版本：V1.0  </br>
 */
public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    // 第三方用户唯一凭证
    public static String appid = "wxba2dd812a0e2b467";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "63c57966ad1a28a50813dca0fd1030ab";
    public static Token accessToken = null;

    public void run() {
        while (true) {
            try {
                accessToken = CommonUtil.getToken(appid, appsecret);
                if (null != accessToken) {
                    //调用存储到数据库
                    TokenUtil.saveToken(accessToken);
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getAccessToken());
                    // 休眠7000秒
                    Thread.sleep((accessToken.getExpiresIn() - 200)*1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException | SQLException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }
}