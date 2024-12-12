package com.ssd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class BlogPost {
    @Id
    @GeneratedValue
    public Long id;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(optional = false,
            cascade = {CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    public BlogUser author;

    public String title;
    public String content;
}
