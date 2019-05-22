package com.tt.bcim.im.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.tt.bcim.im.model.TUser;
import com.tt.bcim.im.query.TUserDTO;
import com.tt.bcim.im.mapper.TUserMapper;
import com.tt.bcim.im.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

