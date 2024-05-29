package com.hb0730.sys.domain.entity;

import com.hb0730.data.core.domain.BaseEntity;
import com.hb0730.data.core.identifier.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/10
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "sys_check_cfg")
public class SysCheckCfg extends BaseEntity {
    @Id
    @IdGenerator
    private String id;

    /**
     * 字典项
     */
    @OneToMany(mappedBy = "sysCheckCfg", cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    private List<SysCheckCfgItem> sysCheckCfgItemList;

    /**
     * 字典名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String projectName;

    /**
     * worktile项目
     */
    @NotBlank(message = "worktile项目ID不能为空")
    private String worktileProjectId;

    /**
     * worktime账号
     */
    @NotBlank(message = "worktime账号")
    private String worktileAccId;

    /**
     * worktime密码
     */
    @NotBlank(message = "项目名称不能为空")
    private String worktileAccPwd;

    /**
     * svn账号
     */
    @NotBlank(message = "svn账号不能为空")
    private String svnAccId;

    /**
     * svn密码
     */
    @NotBlank(message = "svn密码不能为空")
    private String svnAccPwd;


    /**
     * 拥有者手机号码
     */
    @NotBlank(message = "拥有者手机号码不能为空")
    private String ownPhone;

    /**
     * 状态
     */
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;
}
