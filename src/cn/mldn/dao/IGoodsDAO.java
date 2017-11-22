package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Goods;

public interface IGoodsDAO extends IBaseDAO<Long, Goods> {

	/**
	 * 获取最后一次增长数据的id  
	 * @return
	 * @throws SQLException
	 */
	public Long findCreateLastId() throws SQLException;
	/**
	 * 商品标记的追加
	 * @param id 商品编号 
	 * @param tid 标记标号 
	 * @return
	 * @throws SQLException
	 */
	public boolean doCreateGoodsTag(Long id,Set<Long> tid)throws SQLException;
	
	public boolean doEditDflag(Set<Long> gids ,Integer flag) throws SQLException;
	
	
	public boolean doRemoveGoodsTag(Long id) throws SQLException;
	public Set<Long> findGoodsTas(Long id) throws SQLException;
}
