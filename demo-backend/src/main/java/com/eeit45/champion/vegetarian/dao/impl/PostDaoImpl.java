package com.eeit45.champion.vegetarian.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.eeit45.champion.vegetarian.dao.PostDao;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.model.PostLike;
import com.eeit45.champion.vegetarian.rowmapper.PostRowMapper;

@Component
public class PostDaoImpl implements PostDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// 新增文章&圖片
	public boolean addPostImage(Post post) {

		String sql = "INSERT INTO post ( title, postedDate, postedText, imgUrl, postStatus,postCategory,userId, postUpdateDate )"
				+ "VALUES (:title, :postedDate, :postedText, :imgUrl, :postStatus,:postCategory, :userId, :postUpdateDate )";

		Map<String, Object> map = new HashMap<>();
		map.put("title", post.getTitle());
		map.put("postedDate", post.getPostedDate());
		map.put("postedText", post.getPostedText());
		map.put("imgUrl", post.getImgurl());
		map.put("postStatus", post.getPostStatus());
		map.put("postCategory", post.getPostCategory());
		map.put("userId", post.getUserId());
		map.put("postUpdateDate", post.getPostUpdateDate());

		namedParameterJdbcTemplate.update(sql, map);

		return true;

	}

	// 刪除文章
	public boolean deletePost(int id) {

		Map<String, Object> map = new HashMap<>();
		map.put("postId", id);

		String SQL = "DELETE from post where postId = :postId ";
		namedParameterJdbcTemplate.update(SQL, map);
		return true;

	}

	// 更新文章
	public boolean updatePost(Post post) {

		String sql = "UPDATE post SET title = :title, postedText = :postedText , imgUrl = :imgUrl ,postCategory = :postCategory,postStatus = :postStatus, postUpdateDate = :postUpdateDate "
				+ " WHERE postId = :postId ";

		Map<String, Object> map = new HashMap<>();
		map.put("postId", post.getPostId());
		map.put("title", post.getTitle());
		map.put("postedText", post.getPostedText());
		map.put("imgUrl", post.getImgurl());
		map.put("postCategory", post.getPostCategory());
		map.put("postStatus", post.getPostStatus());
		map.put("postUpdateDate", post.getPostUpdateDate());

		namedParameterJdbcTemplate.update(sql, map);

		return true;

	}

	// 更新文章狀態
	public Post updateCondition(Post post) {

		String sql = "UPDATE post SET postStatus = :postStatus, postAuditDate = :postAuditDate WHERE postId = :postId ";

		Map<String, Object> map = new HashMap<>();
		map.put("postStatus", post.getPostStatus());
		map.put("postAuditDate", post.getPostAuditDate());
		map.put("postId", post.getPostId());

		namedParameterJdbcTemplate.update(sql, map);

		return post;
	}

	// 搜尋一篇文章
	public Post findPost(int id) {

		String sql = "SELECT * FROM post WHERE postId = :postId";
		Map<String, Object> map = new HashMap<>();
		map.put("postId", id);

		List<Post> postList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());
		if (postList.size() > 0) {
			return postList.get(0);
		} else {
			return null;
		}

	}

	// 搜尋全部文章
	public List<Post> findallPost() {

		String sql = "SELECT * FROM post order by postId DESC";

		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());

		return postList;
	}

	// 搜尋發布中文章(前台)
	public List<Post> findPostByStatus() {

		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '發布中' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;

	}

	// 搜尋待審核文章(後台)
	public List<Post> findPostByNoAudit() {

		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '待審核' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;

	}

	// 搜尋未通過文章(後台)
	public List<Post> findPostByNoPass() {

		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '未通過' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;

	}

	// 查詢使用者發表文章(發布中)
	public List<Post> findPostByUser(int uid) {

		String sql = "SELECT *  FROM post WHERE userId = :userId AND postStatus = :postStatus ORDER BY postId DESC ";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("postStatus", "發布中");
		List<Post> postList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());
		return postList;

	}

	// 查詢使用者發表文章(待審核)
	public List<Post> findPostByUserNoAudit(int uid) {

		String sql = "SELECT *  FROM post WHERE userId = :userId AND postStatus = :postStatus ORDER BY postId DESC";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("postStatus", "待審核");
		List<Post> postList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());
		return postList;

	}

	// 查詢使用者發表文章(未通過)
	public List<Post> findPostByUserNoPass(int uid) {

		String sql = "SELECT *  FROM post WHERE userId = :userId AND postStatus = :postStatus ORDER BY postId DESC ";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("postStatus", "未通過");
		List<Post> postList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());
		return postList;

	}

	// 新增收藏文章
	public void addFavPost(int pid, int uid) {

		String sql = "INSERT INTO fav_post ( userId, favDate, postId )" + "VALUES (:userId, :favDate, :postId)";

		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("favDate", new Date());
		map.put("postId", pid);

		namedParameterJdbcTemplate.update(sql, map);

	}

	// 新增按讚文章
	public void addLikePost(int pid, int uid) {

		String sql = "INSERT INTO like_post ( userId, likeDate, postId )" + "VALUES (:userId, :likeDate, :postId)";

		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);
		map.put("likeDate", new Date());
		map.put("postId", pid);

		namedParameterJdbcTemplate.update(sql, map);

	}

	// 計算按讚數量
	public int findCountByPid(int pid) {
		String sql = "SELECT COUNT(*) FROM like_post WHERE postId = :postId";

		Map<String, Object> map = new HashMap<>();
		map.put("postId", pid);

		return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
	}

	// 依讚數排序文章
	public List<Post> findPostbyLike() {

		String sql = "SELECT COUNT(like_post.postId) AS numOfLike,"
				+ "post.title,post.postId,post.postedText,post.imgUrl,post.postStatus,post.postCategory,post.userId,post.postedDate,post.postAuditDate,post.postUpdateDate "
				+ " FROM post INNER JOIN like_post ON post.postId = like_post.postId GROUP BY like_post.postId ORDER BY numOfLike DESC";
		Map<String, Object> map = new HashMap<>();
		// map.put("userId", uid);
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

	// 取消收藏文章
	public boolean delFavPost(int pid, int uid) {

		String sql = "DELETE FROM fav_post where postId = :postId AND userId = :userId";

		Map<String, Object> map = new HashMap<>();
		map.put("postId", pid);
		map.put("userId", uid);

		namedParameterJdbcTemplate.update(sql, map);
		return true;

	}

	// 取消按讚文章
	public boolean delLikePost(int pid, int uid) {

		String sql = "DELETE FROM like_post where postId = :postId AND userId = :userId";

		Map<String, Object> map = new HashMap<>();
		map.put("postId", pid);
		map.put("userId", uid);

		namedParameterJdbcTemplate.update(sql, map);
		return true;

	}

	// 搜尋收藏文章
	public PostFavorite findByFavorite(int pid, int uid) {

		String sql = "SELECT * FROM fav_post where postId = :postId and userId = :userId ";
		Map<String, Object> map = new HashMap<>();
		map.put("postId", pid);
		map.put("userId", uid);

		PostFavorite pFavorite;

		try {
			pFavorite = namedParameterJdbcTemplate.queryForObject(sql, map,
					new BeanPropertyRowMapper<PostFavorite>(PostFavorite.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return pFavorite;

	}

	// 搜尋收藏文章(使用者後台)
	public List<Post> findFavoritePost(int uid) {

		String sql = "SELECT * FROM post LEFT JOIN fav_post ON post.postId = fav_post.postId where fav_post.userId = :userId ORDER BY post.postId DESC";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", uid);

		List<Post> postList = namedParameterJdbcTemplate.query(sql, map, new PostRowMapper());

		return postList;

	}

	// 搜尋按讚文章
	public PostLike findByLike(int pid, int uid) {

		String sql = "SELECT * FROM like_post where postId = :postId and userId = :userId ";
		Map<String, Object> map = new HashMap<>();
		map.put("postId", pid);
		map.put("userId", uid);

		PostLike pLike;

		try {
			pLike = namedParameterJdbcTemplate.queryForObject(sql, map,
					new BeanPropertyRowMapper<PostLike>(PostLike.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return pLike;

	}

	// 前台文章分類(全素)
	@Override
	public List<Post> findPostByCategory1() {
		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '發布中' AND postCategory = '全素' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

	// 前台文章分類(蛋素)
	@Override
	public List<Post> findPostByCategory2() {
		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '發布中' AND postCategory = '蛋素' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

	// 前台文章分類(奶素)
	@Override
	public List<Post> findPostByCategory3() {
		String sql = "SELECT *  FROM post where 1=1  AND postStatus = '發布中' AND postCategory = '奶素' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

	// 前台文章分類(蛋奶素)
	@Override
	public List<Post> findPostByCategory4() {
		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '發布中' AND postCategory = '蛋奶素' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

	// 前台文章分類(植物五辛素)
	@Override
	public List<Post> findPostByCategory5() {
		String sql = "SELECT *  FROM post where 1=1 AND postStatus = '發布中' AND postCategory = '植物五辛素' order by postId desc";
		List<Post> postList = namedParameterJdbcTemplate.query(sql, new PostRowMapper());
		return postList;
	}

}
