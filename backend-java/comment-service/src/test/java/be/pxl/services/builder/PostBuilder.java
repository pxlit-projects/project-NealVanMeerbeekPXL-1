package be.pxl.services.builder;

import be.pxl.services.domain.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public final class PostBuilder {
    public static final UUID ID = UUID.randomUUID();
    private PostBuilder() {}

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public Post build() {
        return Post
                .builder()
                .id(ID)
                .build();
    }
}
