package com.ssd.api;

import com.ssd.domain.BlogPost;
import com.ssd.dal.BlogPostDao;
import com.ssd.dal.BlogUserDao;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class BlogPostResolver {

    @Inject
    BlogPostDao blogPostDao;

    @Inject
    BlogUserDao blogUserDao;

    @Query
    @Description("Retrieve all blog posts")
    public List<BlogPost> getAllPosts() {
        return blogPostDao.listAll();
    }

    @Mutation
    @Description("Create a new blog post")
    @Transactional
    public BlogPost createPost(@Name("authorId") Long authorId,
                               @Name("title") String title,
                               @Name("content") String content) {
        var author = blogUserDao.findById(authorId);
        if (author == null) {
            throw new IllegalArgumentException("Author with ID " + authorId + " does not exist");
        }

        BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(author);
        blogPost.setTitle(title);
        blogPost.setContent(content);

        blogPostDao.persist(blogPost);
        return blogPost;
    }

    @Mutation
    @Description("Delete a blog post by ID")
    @Transactional
    public boolean deletePost(@Name("postId") Long postId) {
        BlogPost post = blogPostDao.findById(postId);
        if (post != null) {
            blogPostDao.delete(post);
            return true;
        }
        return false;
    }

    @Query
    @Description("Retrieve blog posts by specific user IDs")
    public List<BlogPost> getPostsByUserIds(@Name("userIds") List<Long> userIds) {
        return blogPostDao.findByUserIds(userIds);
    }
}
