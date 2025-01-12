package be.pxl.services.builder;

import be.pxl.services.domain.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public final class PostBuilder {
    public static final UUID ID = UUID.randomUUID();
    public static final LocalDateTime CREATION_DATE = LocalDateTime.of(2020, 1, 1, 0, 0);
    public static final String TITLE = "A new product has been developed";
    public static final String AUTHOR = "John Smith";
    public static final boolean PUBLISHED = false;
    public static final String CONTENT = """
            
            
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus cursus venenatis vehicula. Morbi varius pretium felis, at ornare nulla feugiat a. Nunc at lorem euismod, venenatis elit sed, venenatis metus. Donec sed mi ante. Quisque vestibulum dictum magna, eu porttitor dolor sollicitudin vel. Mauris vehicula tortor elit, id sollicitudin ante vehicula sed. Aliquam mollis id metus sed aliquet. Aenean tincidunt, elit pharetra venenatis mattis, magna orci porttitor tellus, sit amet feugiat urna orci vel neque.
            
            Proin sit amet finibus enim. Integer at egestas lorem, quis sollicitudin leo. Integer vitae tincidunt felis. Curabitur dictum nunc ligula, vel rutrum lacus vestibulum in. Nulla euismod vulputate sodales. Quisque feugiat lorem ac euismod viverra. Etiam quis mi nulla. Morbi eu felis semper massa vehicula auctor. Proin efficitur turpis consectetur tortor iaculis, in pulvinar elit rutrum. Vestibulum aliquet magna vel semper fermentum. Ut nec arcu iaculis, pharetra tortor eget, pharetra elit. Maecenas vehicula tortor ut ultrices mattis. Nullam finibus risus vel metus maximus consequat. Etiam ullamcorper faucibus lectus blandit dictum. Cras tempus ut nibh ac hendrerit. Sed non dui eget purus consectetur euismod nec sed libero.
            
            Aliquam sagittis, felis ut feugiat molestie, est lorem laoreet ex, at elementum mi elit eu ipsum. Curabitur at ultricies ipsum. Morbi ac velit eget diam tincidunt porta. Etiam interdum pellentesque magna, et semper lorem vestibulum id. Curabitur faucibus felis felis, nec condimentum sapien imperdiet imperdiet. Integer quis mauris fringilla, volutpat nulla quis, venenatis magna. Donec non nulla risus. Vivamus tincidunt efficitur lacus. Vestibulum pellentesque lectus ac orci iaculis, vitae maximus diam pulvinar. Donec vitae congue massa.
            
            Pellentesque quis elit finibus, ullamcorper orci vel, bibendum dolor. Nam elementum ac dolor vitae tristique. Integer quis volutpat sem, volutpat aliquam arcu. Suspendisse tristique sit amet turpis vitae fringilla. Integer eget nibh ac odio rutrum iaculis eu eget eros. Quisque semper dolor at molestie rhoncus. Suspendisse potenti. Nullam sed consectetur dolor, eu tristique erat. Ut sed ornare diam. Phasellus orci augue, finibus quis felis a, volutpat bibendum massa.
            
            Ut magna tortor, sagittis sit amet gravida vulputate, mattis ut ante. Proin quis faucibus nisi. Donec imperdiet nec lacus ac consequat. Aliquam erat volutpat. Donec rutrum diam eu tortor malesuada, sit amet porttitor libero porttitor. Suspendisse gravida ornare elit, non tempus felis dictum sit amet. Proin sed ipsum velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Cras rutrum cursus accumsan. Donec at erat nisl.\s""";

    private PostBuilder() {}

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public Post build() {
        return Post
                .builder()
                .id(ID)
                .creationDate(CREATION_DATE)
                .title(TITLE)
                .author(AUTHOR)
                .content(CONTENT)
                .build();
    }
}
