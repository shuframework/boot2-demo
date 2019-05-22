package com.shuframework.boot2.mp3.im.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shuframework.boot2.mp3.im.model.TUser;
import com.shuframework.boot2.mp3.im.query.TUserDTO;

/**
 * <p>
 * im_用户 服务类
 * </p>
 *
 * @author shuheng
 * @since 2019-05-21
 */
public interface TUserService extends IService<TUser> {

    Page<TUser> selectPage(Page<TUser> page, TUserDTO tUserDTO);

}

