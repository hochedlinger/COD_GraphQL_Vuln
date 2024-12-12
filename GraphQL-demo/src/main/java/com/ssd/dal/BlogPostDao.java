package com.ssd.dal;

import com.ssd.domain.BlogPost;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BlogPostDao implements BaseDao<BlogPost> {

    public List<BlogPost> findByUserIds(List<Long> userIds) {
        return list("author.id in ?1", userIds);
    }
}
