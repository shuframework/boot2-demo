package com.tt.bcim.im.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tt.bcim.im.model.TUser;
import com.tt.bcim.im.query.TUserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * im_用户 Mapper 接口
 * </p>
 *
 * @author shuheng
 * @since 2019-05-21
 */
public interface TUserMapper extends BaseMapper<TUser> {

    List<TUser> selectPageList(Pagination page, TUserDTO tUserDTO);

}

