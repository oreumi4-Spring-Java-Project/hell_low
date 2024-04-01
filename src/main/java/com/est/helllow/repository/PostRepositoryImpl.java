package com.est.helllow.repository;

import com.est.helllow.domain.Post;
import com.est.helllow.domain.dto.PostSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import static com.est.helllow.domain.QPost.post;
import static com.est.helllow.domain.QUser.user;

import java.util.List;

public class PostRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory =new JPAQueryFactory(em);
    }

    @Override
    public List<Post> search(PostSearchCondition postSearchCondition) {
        return queryFactory
                .selectFrom(post)
                .where(searchBuilder(postSearchCondition))
                .orderBy(post.postCreated.desc())
                .fetch();
    }


    //조건1 게시물 제목이 포함된 입력
    private BooleanExpression titleLike(String title){
        // 입력받은 title에 공백 제거 후 비교
        if(StringUtils.hasText(title)){
            String titleWithoutSpaces = title.replace(" ", "");
            return Expressions.stringTemplate("function('replace',{0},{1},{2})", post.postTitle, " ", "").contains(titleWithoutSpaces);
        }else {
            return null;
        }
    }
    // 조건3 게시물 카테고리명(전체) 포합된 입력 -> 카테고리명 확정 시 구현

//    private BooleanExpression categoryEq(String category){
//
//    }
    //조건 3 게시물 작성자 이름(전체) 포함된 입력
    private BooleanExpression writerEq(String writer){
        return StringUtils.hasText(writer) ?user.userName.eq(writer) : null;
    }

    //        // 입력 받은 writer에서 공백 제거 후 비교 / 작성자는 일치해야 검색 가능하게 구현. 필요 시 로직 수정.
//        if(postSearchCondition.getWriter() !=null){
//            String writerWithoutSpaces = postSearchCondition.getWriter().replace(" ", "");
//            builder.and(Expressions.stringTemplate("function('replace',{0},{1},{2})", post.user.userName, " ", "").contains(writerWithoutSpaces));
//        }

    private BooleanBuilder searchBuilder(PostSearchCondition postSearchCondition){
        BooleanBuilder builder=new BooleanBuilder();

        return builder
                .and(titleLike(postSearchCondition.getTitle()))
                .and(writerEq(postSearchCondition.getWriter()));

    }
}
