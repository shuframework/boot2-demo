package com.tt.bcim.im.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tt.bcim.im.model.TUser;
import com.tt.bcim.im.query.TUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

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

