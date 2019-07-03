package com.sendi.db_library.course;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
@Entity
public class CourseEntity {
    @Id(autoincrement = true)
    private Long id;
    private String courseInfo;
    @Generated(hash = 67329330)
    public CourseEntity(Long id, String courseInfo) {
        this.id = id;
        this.courseInfo = courseInfo;
    }
    @Generated(hash = 483818505)
    public CourseEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCourseInfo() {
        return this.courseInfo;
    }
    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }
}
