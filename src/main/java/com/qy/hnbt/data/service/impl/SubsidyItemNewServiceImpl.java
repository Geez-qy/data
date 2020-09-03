package com.qy.hnbt.data.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qy.hnbt.data.entity.SubsidyItemNew;
import com.qy.hnbt.data.mapper.SubsidyItemNewMapper;
import com.qy.hnbt.data.service.ISubsidyItemNewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qy
 * @since 2020-08-24
 */
@DS("#session.tenantName")
@Service
public class SubsidyItemNewServiceImpl extends ServiceImpl<SubsidyItemNewMapper, SubsidyItemNew> implements ISubsidyItemNewService {

}
