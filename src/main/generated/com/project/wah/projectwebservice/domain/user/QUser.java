package com.project.wah.projectwebservice.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 80943050L;

    public static final QUser user = new QUser("user");

    public final com.project.wah.projectwebservice.domain.QBaseTimeEntity _super = new com.project.wah.projectwebservice.domain.QBaseTimeEntity(this);

    public final StringPath aboutme = createString("aboutme");

    public final StringPath blogaddress = createString("blogaddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath githubemail = createString("githubemail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> phonenumber = createNumber("phonenumber", Integer.class);

    public final StringPath picture = createString("picture");

    public final ListPath<com.project.wah.projectwebservice.domain.message.Message, com.project.wah.projectwebservice.domain.message.QMessage> readMessage = this.<com.project.wah.projectwebservice.domain.message.Message, com.project.wah.projectwebservice.domain.message.QMessage>createList("readMessage", com.project.wah.projectwebservice.domain.message.Message.class, com.project.wah.projectwebservice.domain.message.QMessage.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<com.project.wah.projectwebservice.domain.message.Message, com.project.wah.projectwebservice.domain.message.QMessage> sendMessage = this.<com.project.wah.projectwebservice.domain.message.Message, com.project.wah.projectwebservice.domain.message.QMessage>createList("sendMessage", com.project.wah.projectwebservice.domain.message.Message.class, com.project.wah.projectwebservice.domain.message.QMessage.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

