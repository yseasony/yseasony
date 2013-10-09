package org.yseasony.edm.dao;

import org.yseasony.edm.entiy.EdmEmailGroup;

import java.util.List;

/**
 * User: yseasony
 * Date: 13-10-1
 * Time: 下午12:44
 */
public interface GroupDao {
    int insert(String groupName);

    Integer findByGroupName(String groupName);

    List<EdmEmailGroup> getList();
}
