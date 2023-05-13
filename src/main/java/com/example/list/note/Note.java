package com.example.list.note;

import com.example.list.user.UserEntity;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "accesstype")
    private AccessType accessType;

//    @Column(name = "user_id")
//    private Long user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Note() {
    }

    public Note(String title, String content, AccessType accessType) {
        this.title = title;
        this.content = content;
        this.accessType = accessType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

//    public Long getUser() {
//        return user_id;
//    }
//
//    public void setUser(Long user_id) {
//        this.user_id = user_id;
//    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Note{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", accessType='").append(accessType).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append('}');
        return sb.toString();
    }
}