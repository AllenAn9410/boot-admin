package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.common.api.JsfPage;
import com.hb0730.data.core.service.BaseService;
import com.hb0730.query.jpa.QueryHelper;
import com.hb0730.sys.domain.dto.DictItemDto;
import com.hb0730.sys.domain.dto.DictSmallDto;
import com.hb0730.sys.domain.dto.SysCheckCfgDto;
import com.hb0730.sys.domain.dto.SysCheckCfgItemDto;
import com.hb0730.sys.domain.entity.SysCheckCfg;
import com.hb0730.sys.domain.entity.SysCheckCfgItem;
import com.hb0730.sys.domain.entity.SysDict;
import com.hb0730.sys.domain.entity.SysDictItem;
import com.hb0730.sys.domain.query.DictItemQuery;
import com.hb0730.sys.domain.query.SysCheckCfgItemQuery;
import com.hb0730.sys.repository.SysCheckCfgItemRepository;
import com.hb0730.sys.service.mapstruct.SysCheckCfgItemMapstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/5/5
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysCheckCfgItemService extends BaseService<SysCheckCfgItemRepository, SysCheckCfgItem, String> {
    private final SysCheckCfgItemMapstruct mapstruct;

    private final SysCheckCfgService sysCheckCfgService;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<SysCheckCfgItemDto> page(SysCheckCfgItemQuery query) {
        Pageable page = QueryHelper.toPage(query);
        Specification<SysCheckCfgItem> specification = QueryHelper.ofBean(query);
        Page<SysCheckCfgItem> pageData = baseRepository.findAll(specification, page);
        List<SysCheckCfgItemDto> res = mapstruct.toDtoList(pageData.getContent());
        return QueryHelper.toJsfPage(pageData, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<SysCheckCfgItemDto> list(DictItemQuery query) {
        Specification<SysCheckCfgItem> specification = QueryHelper.ofBean(query);
        List<SysCheckCfgItem> list = baseRepository.findAll(specification);
        return mapstruct.toDtoList(list);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackOn = Exception.class)
    public String save(SysCheckCfgItemDto dto) {
        SysCheckCfgItem entity = mapstruct.toEntity(dto);
        save(entity);
        return entity.getId();
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void updateById(SysCheckCfgItemDto dto) {
        SysCheckCfgDto cfg = dto.getSysCheckCfg();
        if (null == cfg) {
            throw new ServiceException("配置项不能为空");
        }
        SysCheckCfg sysCheckCfgById = sysCheckCfgService.findById(cfg.getId());
        if (null == sysCheckCfgById) {
            throw new ServiceException("配置项不存在");
        }
        String id = dto.getId();
        if (null == id) {
            throw new ServiceException("id不能为空");
        }
        SysCheckCfgItem sysCheckCfgItem = baseRepository.findById(id).orElseThrow(() -> new ServiceException("数据不存在"));

        BeanUtil.copyProperties(dto, sysCheckCfgItem, CopyOptions.create().ignoreNullValue());
        updateById(sysCheckCfgItem);
    }

    /**
     * 根据ID删除
     *
     * @param id id
     */
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        SysCheckCfgItem byId = getById(id);
        if (null == byId) {
            throw new ServiceException("数据不存在");
        }
        remove(byId);
    }

}
