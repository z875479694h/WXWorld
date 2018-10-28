package xyz.weiworld.weixin.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xyz.weiworld.weixin.application.FallInLove;
import xyz.weiworld.weixin.message.resp.Article;
import xyz.weiworld.weixin.message.resp.NewsMessage;
import xyz.weiworld.weixin.message.resp.TextMessage;
import xyz.weiworld.weixin.util.MessageUtil;

/**
* 类名: CoreService </br>
* 描述: 核心服务类 </br>
* 开发人员： souvc </br>
* 创建时间：  2015-9-30 </br>
* 发布版本：V1.0  </br>
 */
public class CoreService {
    /**
     * 处理微信发来的请求
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "success";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            //内容
            String content = requestMap.get("Content");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            /**回复图文消息*/
            NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            
            
            List<Article> articleList = new ArrayList<Article>();  
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	FallInLove f = new FallInLove();
            	if(content.indexOf("再见单身")>-1) {
            		if(content.length()<8) {
            			respContent = "这么短，兄dei你是来捣乱的吧？";
            			// 设置文本消息的内容
                        textMessage.setContent(respContent);
                        // 将文本消息对象转换成xml
                        respXml = MessageUtil.messageToXml(textMessage);
            		}else {
            		respContent=f.putLove(fromUserName, content);
            		// 设置文本消息的内容
                    textMessage.setContent(respContent);
                    // 将文本消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
            		}
        		}else if("求秘籍".equals(content)) {
        			respContent=f.selectLove(fromUserName);
            		// 设置文本消息的内容
                    textMessage.setContent(respContent);
                    // 将文本消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
            	
            	}else if("脱单".equals(content)) {
            		respContent="请按照下面的格式发来你的信息:\r\n" + 
            					"内容+再见单身\r\n" +
            					"例子：那年高二，我和她和她闺蜜做了好朋友，每个回校日都约出来吃饭，平时帮她打热水，于是刚上高三我就对她表白了，曾经啊那么的单纯，再见单身\r\n" +
            					"也可以在秘籍上留下你的微信号让别人取经\r\n"+
            					"回复“求秘籍”即可得到他人或者你的脱单秘籍\r\n"+
            					"我们会不断更新玩法。来啊，来和小哥哥小姐姐快活啊！";
            		// 设置文本消息的内容
                    textMessage.setContent(respContent);
                    // 将文本消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
            	
            	}else if(content.equals("添加微信号")) {
            		Article article = new Article();  
                    article.setTitle("联系我们");  
                    article.setDescription("把你想要的福利告诉我们，一切福利以寻愿部落的确认信息为准，其余无效");  
                    article.setPicUrl("https://www.hcworld.xyz/images/XYBL2.jpg");  
                    article.setUrl("https://www.hcworld.xyz/images/XYBL2.jpg"); 
            	}else if(content.equals("游戏币")||content.equals("领取游戏币")) {
            		Article article = new Article();  
                    article.setTitle("联系我们");  
                    article.setDescription("转发推文至朋友圈，然后添加下方二维码，截图发送至寻愿部落，并把你想要的福利告诉我们，一切福利以寻愿部落的确认信息为准，其余无效");  
                    article.setPicUrl("https://www.hcworld.xyz/images/XYBL2.jpg");  
                    article.setUrl("https://www.hcworld.xyz/images/XYBL2.jpg");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respXml = MessageUtil.messageToXml(newsMessage);
            	}else if(content.indexOf("城建百科")>-1) {
            		Article article1 = new Article();  
                    article1.setTitle("城建百科（从化篇）");  
                    article1.setDescription("");  
                    article1.setPicUrl("https://www.hcworld.xyz/images/CJBKch.jpg");  
                    article1.setUrl("http://mp.weixin.qq.com/s/SkjrfaXKg1-yZF2QfLvccA");  
  
                    Article article2 = new Article();  
                    article2.setTitle("城建百科（赚钱篇）");  
                    article2.setDescription("");  
                    article2.setPicUrl("https://www.hcworld.xyz/images/CJBKzq.jpg");  
                    article2.setUrl("http://mp.weixin.qq.com/s/ueorWh7dXG78-QrdBjuygA");  
  
                    Article article3 = new Article();  
                    article3.setTitle("城建百科（校内篇）");  
                    article3.setDescription("");  
                    article3.setPicUrl("https://www.hcworld.xyz/images/CJBKxn.jpg");  
                    article3.setUrl("http://mp.weixin.qq.com/s/zlD7Ni4Sq3k9-vzlycZZnw");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respXml = MessageUtil.messageToXml(newsMessage);  
            	}else {
            		// 设置文本消息的内容
                    textMessage.setContent(respContent);
                    // 将文本消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
                }
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            	
            	// 设置文本消息的内容
            	
            	textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            	
            	// 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
            	// 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
            	// 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            	// 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            	// 设置文本消息的内容
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml
                respXml = MessageUtil.messageToXml(textMessage);
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "非常感谢你加入寻愿部落，在这里，小寻带你玩遍高校吃喝玩乐，周边景点，附近优惠商家\r\n" + 
                    		"\r\n" + 
                    		"回复“添加微信号”可添加我们一起尬聊";
                 // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    // 将文本消息对象转换成xml
                    respXml = MessageUtil.messageToXml(textMessage);
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
        if(respContent.equals("success")) {
        	return "success";
        }else {
        	return respXml;
        	}
    }
}