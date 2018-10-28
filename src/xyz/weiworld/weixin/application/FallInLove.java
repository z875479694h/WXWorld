package xyz.weiworld.weixin.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.junit.Test;

import xyz.weiworld.weixin.db.DBManager;

/** 
* @classDesc: 功能描述：(堕入爱河（脱单）)
* @author : 张红尘
* @date 创建时间：2018年4月5日 下午9:01:43 
* @version 1.0 
*/
public class FallInLove {
	
		public String putLove(String fromUserName,String content) throws SQLException {
			String str =fromUserName;
			Connection conn = null;
			String sql="SELECT useropenid FROM FallInLove WHERE useropenid=?";
			/**得到数据库连接*/
			try {
				conn = DBManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, str);
				ResultSet rs = pst.executeQuery();
				if(rs.next()){
					str = "你已经分享过了";
					return str;
				}
				sql = "INSERT INTO FallInLove(useropenid,content) VALUES(?,?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, str);
				pst.setString(2, content);
				str=(pst.executeUpdate()!=0)?"分享成功":"服务异常，请联系寻愿";
			} catch (SQLException e) {
				e.printStackTrace();
				return "服务异常，请联系寻愿";
			} finally {
				conn.close();
				}
			
			return str;
		}
		
		public String selectLove(String fromUserName) throws SQLException {
			String str =fromUserName;
			Connection conn = null;
			String sql="SELECT useropenid FROM FallInLove WHERE useropenid=?";
			/**得到数据库连接*/
			try {
				conn = DBManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, str);
				ResultSet rs = pst.executeQuery();
				if(rs.next()){
					sql="SELECT COUNT(ID) FROM FallInLove";
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();
					/**数据库数据总数*/
					int max=0;
					while(rs.next()){
							max =  rs.getInt("COUNT(ID)");
						}
					/**随机数*/
					int n=(int)(Math.random()*max+1);
					str= Integer.toString(n);
					sql="SELECT content FROM FallInLove WHERE ID=?;";
					pst = conn.prepareStatement(sql);
					pst.setString(1, str);
					rs = pst.executeQuery();
					if(rs.next()){
						str = rs.getString("content");
					}
					return str;
				}else {
					str = "先去分享你的脱单经验，再来看别人的经验吧。";
					return str;
				}
		} catch (SQLException e) {
			e.printStackTrace();
			return "服务异常，请联系寻愿";
		} finally {
			conn.close();
			}
		}
		
		/*@Test
		public void test() throws SQLException{
			FallInLove t= new FallInLove();
			//System.out.println(t.putLove("1zx89xf4566", "还想脱单？不存在的！2"));
			System.out.println(t.selectLove("1zx89xf4564"));
		}*/
		
}
