package com.ssd;

import com.ssd.domain.BlogPost;
import com.ssd.domain.BlogUser;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class DbInitializer {
    @Inject
    EntityManager em;

    @Transactional
    public void init(@Observes StartupEvent ev) {
        var user1 = new BlogUser();
        user1.setUserName("Michael J");
        user1.setPassword("pw123");

        var user2 = new BlogUser();
        user2.setUserName("Tobias Knödlinger");
        user2.setPassword("pw123");

        user1 = em.merge(user1);
        user2 = em.merge(user2);

        var post1 = new BlogPost();
        post1.setAuthor(user1);
        post1.setTitle("Cyber Offense and Defense");
        post1.setContent("This is a very good course! I learned a lot about cyber security.");

        var post2 = new BlogPost();
        post2.setAuthor(user1);
        post2.setTitle("Java");
        post2.setContent("Java is a high-level, class-based, object-oriented programming language that is very fun to use.");

        var post3 = new BlogPost();
        post3.setAuthor(user2);
        post3.setTitle("GraphQL security issues");
        post3.setContent("GraphQL is a query language for APIs and a runtime for executing those queries with existing data. It is very powerful, which is why some developers may not be aware of how to secure it properly.");

        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
    }
}
