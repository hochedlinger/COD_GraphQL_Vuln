package com.ssd.domain;

import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.graphql.Ignore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class BlogUser {
    @Id
    @GeneratedValue
    public Long id;

    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "author",
            cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY)
    Set<BlogPost> blogPosts = new HashSet<>();

    public String userName;
    @Ignore
    public String password;
}
