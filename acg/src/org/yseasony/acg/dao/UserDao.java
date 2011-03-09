package org.yseasony.acg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;

public interface UserDao {

	User findUserByLoginName(String loginName);

	void insert(User user);

	User get(long id);

	int count(@Param("filters") Map<String, ?> filters);

	List<User> page(Page<User> page);

	void delete(long id);

	void update(User user);
}
