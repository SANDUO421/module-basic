package com.module.authority.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.module.authority.app.domain.DeptDTO;
import com.module.authority.app.entity.SysDept;
import com.module.authority.app.vo.DeptTreeVo;


import java.io.Serializable;
import java.util.List;

/**
 * @description:    部门管理 服务类
 * @author:         sanduo
 * @date:           2020/3/4 12:20
 * @version:        1.0
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 查询部门信息
     * @return
     */
    List<SysDept> selectDeptList();

    /**
     * 更新部门
     * @param entity
     * @return
     */
    boolean updateDeptById(DeptDTO entity);

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据部门id查询部门名称
     * @param deptId
     * @return
     */
    String selectDeptNameByDeptId(int deptId);

    /**
     * 根据部门名称查询
     * @param deptName
     * @return
     */
    List<SysDept> selectDeptListBydeptName(String deptName);

    /**
     * 通过此部门id查询于此相关的部门ids
     * @param deptId
     * @return
     */
    List<Integer> selectDeptIds(int deptId);

    /**
     * 获取部门树
     * @return
     */
    List<DeptTreeVo> getDeptTree();


}
