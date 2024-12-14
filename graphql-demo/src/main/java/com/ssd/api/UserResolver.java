package com.ssd.api;


import com.ssd.dal.BlogUserDao;
import com.ssd.domain.BlogUser;
import io.smallrye.faulttolerance.api.RateLimit;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;


@GraphQLApi
public class UserResolver {

    @Inject
    BlogUserDao blogUserDao;

    @Mutation
    @Description("Create a new user")
    @Transactional
    public BlogUser createUser(@Name("userName") String userName, @Name("password") String password) {
        BlogUser user = new BlogUser();
        user.setUserName(userName);
        user.setPassword(password);
        blogUserDao.persist(user);
        return user;
    }

    @Query
    @Description("Get your user by username and password")
    @RateLimit(
            value = 1, // 1 request
            window = 1 // per second
    )
    public BlogUser logIn(@Name("userName") String userName, @Name("password") String password) {
        return blogUserDao.find("userName = ?1 and password = ?2", userName, password)
                .firstResultOptional()
                .orElse(null);
    }
}
