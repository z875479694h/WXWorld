package xyz.weiworld.weixin.user;

/**
 * 用户类
 * @date 创建时间：2017年11月28日 下午9:01:43 
 * @author zhang
 *
 */
public class User {
	/**发送者openID*/
	private String fromUserName;
	/**内容*/
	private String[] content;
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
	
}
