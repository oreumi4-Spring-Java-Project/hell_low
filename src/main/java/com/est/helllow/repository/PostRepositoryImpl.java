package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import static com.est.helllow.domain.QPost.post;

import java.util.List;

public class PostRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> search(PostSearchCondition postSearchCondition) {
        return queryFactory
                .selectFrom(post)
                .where(titleLike(postSearchCondition.getSearchText())
                        .or(writerEq(postSearchCondition.getSearchText()))
                        .or(categoryEq(postSearchCondition.getSearchText())))
                .orderBy(post.createdAt.desc())
                .fetch();
    }


    //조건 1 게시물 제목이 포함된 입력
    private BooleanExpression titleLike(String title) {
        // 입력받은 title에 공백 제거 후 비교
        if (StringUtils.hasText(title)) {
            String titleWithoutSpaces = title.replace(" ", "");
            return Expressions.stringTemplate("function('replace',{0},{1},{2})", post.postTitle, " ", "").contains(titleWithoutSpaces);
        } else {
            return null;
        }
    }

    // 조건 2  게시물 카테고리명(전체) 포합된 입력
    private BooleanExpression categoryEq(String category) {
        return StringUtils.hasText(category) ? post.category.eq(category) : null;
    }

    //조건 3 게시물 작성자 이름(전체) 포함된 입력
    private BooleanExpression writerEq(String writer) {
        return StringUtils.hasText(writer) ? post.user.userName.eq(writer) : null;
    }

}
