package com.codegym.repository;

import com.codegym.models.Blogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface IBlogRepo extends PagingAndSortingRepository<Blogs,Long> {
//    List<Blogs> getAll(Pageable pageable);
}
