package com.shuframework.boot2.mp3.im.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuframework.boot2.mp3.im.mapper.TUserMapper;
import com.shuframework.boot2.mp3.im.model.TUser;
import com.shuframework.boot2.mp3.im.query.TUserDTO;
import com.shuframework.boot2.mp3.im.service.TUserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * im_用户 服务实现类
 * </p>
 *
 * @author shuheng
 * @since 2019-05-21
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Override
    public Page<TUser> selectPage(Page<TUser> page, TUserDTO tUserDTO) {
        List<TUser> list = baseMapper.selectPageList(page, tUserDTO);
        page.setRecords(list);
        return page;
    }

}

