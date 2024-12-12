package com.ssd.api;

import com.ssd.dal.BlogUserDao;
import com.ssd.domain.BlogUser;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

@GraphQLApi
public class UserResolver {

    @Inject
    BlogUserDao blogUserDao;

    @Mutation
    @Description("Create a new user")
    public BlogUser createUser(@Name("userName") String userName, @Name("password") String password) {
        BlogUser user = new BlogUser();
        user.setUserName(userName);
        user.setPassword(password);
        blogUserDao.persist(user);
        return user;
    }

    @Query
    @Description("Get your user by username and password")
    public BlogUser logIn(@Name("userName") String userName, @Name("password") String password) {
        return blogUserDao.find("userName", userName, "password", password)
                .firstResultOptional()
                .orElse(null);
    }
}
