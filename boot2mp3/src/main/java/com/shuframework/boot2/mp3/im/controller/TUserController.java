package com.tt.bcim.im.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tt.bcim.im.model.TUser;
import com.tt.bcim.im.query.TUserDTO;
import com.tt.bcim.im.service.TUserService;
import com.tt.bcim.commonbase.enums.FailureEnum;
import com.tt.bcim.commonbase.result.Result;
import com.tt.bcim.commonbase.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * im_用户 前端控制器
 * </p>
 *
 * @author shuheng
 * @since 2019-05-21
 */
@RestController
@RequestMapping("/im/tUser")
public class TUserController {

    @Autowired
    private TUserService tUserService;


    /**
     * 获取数据列表
     */
    @RequestMapping("/selectPage")
    public Result selectPage(@RequestBody TUserDTO tUserDTO){
        Page<TUser> page = new Page(tUserDTO.getPageIndex(), tUserDTO.getPageSize());
        page = tUserService.selectPage(page, tUserDTO);
        return ResultUtil.success(page);
    }

    /**
     * 根据ID查找数据
     */
    @RequestMapping("/detail")
    public Result detail(@RequestParam("id") Long id){
        TUser tUser = tUserService.selectById(id);
        if(tUser == null){
            return ResultUtil.failure(FailureEnum.NOTEXIST_FAILURE);
        }
        return ResultUtil.success(tUser);
    }

    /**
     * 添加数据
     */
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody TUser tUser){
        boolean flag = tUserService.insert(tUser);
        if(flag){
            return ResultUtil.successOfInsert(tUser);
        }
        return ResultUtil.failureOfInsert(tUser);
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody TUser tUser){
        boolean isOk = tUserService.updateAllColumnById(tUser);
        if(isOk){
            return ResultUtil.successOfUpdate(tUser);
        }
        return ResultUtil.failureOfUpdate(tUser);
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public Result delete(@RequestParam("ids") List<Long> ids){
        boolean isOk = tUserService.deleteBatchIds(ids);
        if(isOk){
            return ResultUtil.successOfDelete(ids);
        }
        return ResultUtil.failureOfDelete(ids);
    }

}
