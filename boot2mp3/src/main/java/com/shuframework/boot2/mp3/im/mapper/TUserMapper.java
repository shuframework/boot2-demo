package com.shuframework.boot2.mp3.im.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shuframework.boot2.mp3.im.model.TUser;
import com.shuframework.boot2.mp3.im.query.TUserDTO;

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

    List<TUser> selectPageList(Page page, TUserDTO tUserDTO);

}

