package com.itqf.mapper;

import com.itqf.dto.SysMenuDTO;
import com.itqf.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenuDTO> findMenu(@Param("search") String search, @Param("order")String order);

    List<SysMenu> findTreeMenu();

    int del(List<Long> ids);

    List<SysMenuDTO> findDirByUserId(long userId);

    List<SysMenuDTO> findMenuByUserIdAndParentId(@Param("userId")long userId,@Param("parentId") long menuId);

}