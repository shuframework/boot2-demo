package com.tt.bcim.im.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * im_用户
 * </p>
 *
 * @author shuheng
 * @since 2019-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**  返回的用户id(uuid) */
    private String userId;

    /**  登录名 */
    private String username;

    /**  名称 */
    private String nickname;

    /**  密码 */
    private String password;

    /**  性别 1男 2女 */
    private String sex;

    /**  头像 */
    private String portrait;

    /**  手机号 */
    private String phone;

    /**  备用号 */
    private String phoneOld;

    /**  电子邮箱 */
    private String email;

    /**  地址 */
    private String address;

    /**  用户类型 */
    private String useType;

    /**  备注 */
    private String remarks;

    /**  创建时间 */
    private LocalDateTime createTime;

    /**  更新时间 */
    private LocalDateTime updateTime;

    /**  状态, 0禁用 1可用 2删除 */
    private String enabled;

    /**  省名称 */
    private String provinceName;

    /**  市名称 */
    private String cityName;

    /**  个性签名 */
    private String signature;


}
