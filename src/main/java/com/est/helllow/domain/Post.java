package com.est.helllow.domain;

import com.est.helllow.domain.dto.PostResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID", updatable = false)
    private Long postId;
  
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name="uuid2", strategy = "uuid2")
//    @Column(name = "post_id", columnDefinition = "BINARY(16)", nullable = false)
//    private UUID post_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "POST_TITLE", nullable = false)
    private String postTitle;
  
    @Column(name = "POST_CONTENT", nullable = false)
    private String postContent;

    @ColumnDefault("0") //디폴트 값 0으로 지정 가능
    @Column(name = "LIKE_COUNTS")
    private Integer likeCounts;

    @ColumnDefault("0")
    @Column(name = "VIEW_COUNTS")
    private Integer viewCounts;

    @Column(name = "POST_FILE")
    private String postFile;

    @CreatedDate
    @Column(name = "POST_CREATED")
    private LocalDateTime postCreated;

    @LastModifiedDate
    @Column(name = "POST_MODIFIED")
    private LocalDateTime postModified;
  
    
    @Builder
    public Post(String category, String title, String content) {
        this.category = category;
        this.postTitle = title;
        this.postContent = content;
    }

    public PostResponseDto toResponse() {
        return PostResponseDto.builder()
                .category(category)
                .postTitle(postTitle)
                .postContent(postContent)
                .likeCounts(likeCounts)
                .viewCounts(viewCounts)
                .postFile(postFile)
                .postCreated(postCreated)
                .postModified(postModified)
                .build();
    }

    public void update(String title, String content){
        this.postTitle = title;
        this.postContent = content;
    }

    public int incLikeCount(){
        return ++likeCounts;
    }

    public int decLikeCount(){
        return --likeCounts;
    }

//    디폴트값 설정
    @PrePersist
    public void prePersist() {
        this.likeCounts = this.likeCounts == null ? 0 : this.likeCounts;
        this.viewCounts = this.viewCounts == null ? 0 : this.viewCounts;
        this.postFile = this.postFile == null ? "logo.png" : this.postFile;
    }

}
