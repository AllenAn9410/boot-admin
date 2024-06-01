package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.common.api.JsfPage;
import com.hb0730.data.core.service.BaseService;
import com.hb0730.query.jpa.QueryHelper;
import com.hb0730.security.domain.dto.UserInfoDto;
import com.hb0730.sys.domain.dto.DictDto;
import com.hb0730.sys.domain.dto.RoleDto;
import com.hb0730.sys.domain.dto.SysCheckCfgDto;
import com.hb0730.sys.domain.entity.SysCheckCfg;
import com.hb0730.sys.domain.entity.SysDict;
import com.hb0730.sys.domain.entity.SysRole;
import com.hb0730.sys.domain.query.DictQuery;
import com.hb0730.sys.domain.query.SysCheckCfgQuery;
import com.hb0730.sys.repository.SysCheckCfgItemRepository;
import com.hb0730.sys.repository.SysCheckCfgRepository;
import com.hb0730.sys.repository.SysDictRepository;
import com.hb0730.sys.service.mapstruct.SysCheckCfgMapstruct;
import com.hb0730.sys.service.mapstruct.SysDictMapstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/5
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysCheckCfgService extends BaseService<SysCheckCfgRepository, SysCheckCfg, String> {

    private final SysCheckCfgMapstruct mapstruct;


    private final SysCheckCfgItemRepository sysCheckCfgItemRepository;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<SysCheckCfgDto> page(SysCheckCfgQuery query) {
        Pageable page = QueryHelper.toPage(query);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDto userInfoDto = JSONObject.parseObject(JSON.toJSONString(authentication.getPrincipal()), UserInfoDto.class);
        if (!userInfoDto.getRoles().contains("admin")) {
            query.setOwnPhone(authentication.getName());
        }
        Specification<SysCheckCfg> specification = QueryHelper.ofBean(query);
        Page<SysCheckCfg> pageData = baseRepository.findAll(specification, page);
        List<SysCheckCfgDto> res = mapstruct.toDtoList(pageData.getContent());
        return QueryHelper.toJsfPage(pageData, res);
    }


    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackOn = Exception.class)
    public String save(SysCheckCfgDto dto) {
        SysCheckCfg entity = mapstruct.toEntity(dto);
        save(entity);
        return entity.getId();
    }

    /**
     * 删除
     *
     * @param id id
     */
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        boolean exists = sysCheckCfgItemRepository.existsCheckCfgPath(id);
        if (exists) {
            throw new ServiceException("配置存在路径，无法删除");
        }
        SysCheckCfg sysCheckCfg = getById(id);
        if (sysCheckCfg == null) {
            throw new ServiceException("未找到对应配置");
        }
        remove(sysCheckCfg);
    }

    /**
     * 更新
     *
     * @param dto dto
     */
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void updateById(SysCheckCfgDto dto) {
        SysCheckCfg sysCheckCfg = mapstruct.toEntity(dto);
        SysCheckCfg entity = getById(sysCheckCfg.getId());
        if (entity == null) {
            throw new ServiceException("未找到对应实体");
        }
        BeanUtil.copyProperties(dto, entity, CopyOptions.create().setIgnoreNullValue(true));
        updateById(entity);
    }


}
