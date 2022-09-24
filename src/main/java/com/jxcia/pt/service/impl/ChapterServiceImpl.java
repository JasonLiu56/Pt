package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.Chapter;
import com.jxcia.pt.mapper.ChapterMapper;
import com.jxcia.pt.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String name, Integer courseId) {
        Chapter chapter = new Chapter(name, courseId);
        return chapterMapper.insert(chapter);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return chapterMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer courseId, String name) {
        return chapterMapper.isExistByNameAndCourseId(name, courseId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer courseId, Integer id) {
        return chapterMapper.isExistByIdAndCourseId(id, courseId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer courseId, Integer id, String name) {
        return chapterMapper.isExistByNonIdAndNameCourseId(id, courseId, name);
    }

    @Override
    public Boolean delete(Integer id) {
        return chapterMapper.delete(id);
    }

    @Override
    public Boolean update(Integer id, String name, Integer courseId) {
        return chapterMapper.update(id, name, courseId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Chapter getById(Integer id) {
        return chapterMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Chapter> getAll(String name, Integer courseId) {
        return chapterMapper.getAll(name, courseId);
    }

}
