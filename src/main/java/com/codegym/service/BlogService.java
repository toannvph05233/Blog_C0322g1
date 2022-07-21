package com.codegym.service;

import com.codegym.models.Blogs;
import com.codegym.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class BlogService {
    @Autowired
    IBlogRepo iBlogRepo;

    public Page<Blogs> getAll(Pageable pageable) {
        return  iBlogRepo.findAll(pageable);
    }

    public void save(Blogs blogs) {
        iBlogRepo.save(blogs);
    }

    public void delete(long id) {
        iBlogRepo.deleteById(id);
    }

    public Optional<Blogs> findById(long id) {
        return iBlogRepo.findById(id);
    }


}
