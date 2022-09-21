package com.jxcia.pt.service.impl;

import com.github.pagehelper.PageHelper;
import com.jxcia.pt.entity.Category;
import com.jxcia.pt.mapper.CategoryMapper;
import com.jxcia.pt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String name) {
        Category category = new Category(name);
        return categoryMapper.insert(category);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return categoryMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(String name) {
        return categoryMapper.isExistByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, String name) {
        return categoryMapper.isExistByNonIdAndName(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return categoryMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String name) {
        return categoryMapper.update(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> getByPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum - 1, pageSize);
        return categoryMapper.getByPage(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countByPage(String name) {
        return categoryMapper.countByPage(name);
    }

}
