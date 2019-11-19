package com.itqf.dto;

import com.itqf.entity.SysMenu;
import lombok.Data;

import java.util.List;


@Data
public class SysMenuDTO  extends SysMenu {

    private String parentName;
    private List<SysMenuDTO> list;
}
