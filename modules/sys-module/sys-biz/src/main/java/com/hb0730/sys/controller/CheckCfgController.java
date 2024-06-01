package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.DictItemDto;
import com.hb0730.sys.domain.dto.NoticeDto;
import com.hb0730.sys.domain.dto.RoleDto;
import com.hb0730.sys.domain.dto.SysCheckCfgDto;
import com.hb0730.sys.domain.dto.SysCheckCfgItemDto;
import com.hb0730.sys.domain.entity.SysCheckCfgItem;
import com.hb0730.sys.domain.query.DictItemQuery;
import com.hb0730.sys.domain.query.NoticeQuery;
import com.hb0730.sys.domain.query.SysCheckCfgItemQuery;
import com.hb0730.sys.domain.query.SysCheckCfgQuery;
import com.hb0730.sys.service.SysCheckCfgItemService;
import com.hb0730.sys.service.SysCheckCfgService;
import com.hb0730.sys.service.SysDictItemService;
import com.hb0730.sys.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anx
 * @since 2024/05/18 15:15
 */
@RestController
@RequestMapping("/sys/checkCfg")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：检查配置")
public class CheckCfgController {

    private final SysCheckCfgService sysCheckCfgService;
    private final SysCheckCfgItemService sysCheckCfgItemService;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:query')")
    public R<JsfPage<SysCheckCfgDto>> page(SysCheckCfgQuery query) {
        JsfPage<SysCheckCfgDto> res = sysCheckCfgService.page(query);
        return R.OK(res);
    }

    /**
     * 删除
     *
     * @param id 角色id
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:delete')")
    @Parameters({
        @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "配置ID", required = true)
    })
    public R<String> deleteById(@RequestParam(name = "id") String id) {
        sysCheckCfgService.deleteById(id);
        return R.OK();
    }

    /**
     * 保存
     *
     * @param dto dto
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "新增配置")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:save')")
    public R<String> save(@Valid @RequestBody SysCheckCfgDto dto) {
        sysCheckCfgService.save(dto);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param dto 角色
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:update')")
    public R<String> updateById(@RequestBody SysCheckCfgDto dto) {
        if (dto.getId() == null) {
            return R.NG("配置ID不能为空");
        }
        sysCheckCfgService.updateById(dto);
        return R.OK();
    }


    @GetMapping("/item/page")
    @Operation(summary = "分页查询配置子项信息")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:item:query')")
    public R<JsfPage<SysCheckCfgItemDto>> pageItem(SysCheckCfgItemQuery query) {
        JsfPage<SysCheckCfgItemDto> res = sysCheckCfgItemService.page(query);
        return R.OK(res);
    }

    @PostMapping("/item")
    @Operation(summary = "新增配置子项")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:item:save')")
    public R<String> saveItem(@Valid @RequestBody SysCheckCfgItemDto dto) {
        sysCheckCfgItemService.save(dto);
        return R.OK();
    }

    @PutMapping("/item")
    @Operation(summary = "修改配置子项")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:item:update')")
    public R<String> updateItem(@Valid @RequestBody SysCheckCfgItemDto dto) {
        sysCheckCfgItemService.updateById(dto);
        return R.OK();
    }

    @DeleteMapping ("/item")
    @Operation(summary = "删除配置子项")
    @PreAuthorize("hasAnyAuthority('sys:checkCfg:item:delete')")
    public R<String> delItem(@RequestParam(name = "id") String id) {
        sysCheckCfgItemService.deleteById(id);
        return R.OK();
    }


}
