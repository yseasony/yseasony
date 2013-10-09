package org.yseasony.edm.dao;

import org.yseasony.edm.entiy.EdmSendList;

import java.util.List;

/**
 * User: yseasony
 * Date: 13-10-5
 * Time: 下午1:28
 */
public interface EdmSendListDao {
    int batchInsert(List<EdmSendList> edmSendLists);
}
