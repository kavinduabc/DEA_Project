package com.opentutor.classroomservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.opentutor.classroomservice.util.ClassroomUtil;

import java.util.UUID;

public class ClassroomRequestDTO {

    @NotNull(message = ClassroomUtil.MSG_TEACHER_ID_REQUIRED)
    private UUID teacherId;

    @NotBlank(message = ClassroomUtil.MSG_TITLE_REQUIRED)
    @Length(min = ClassroomUtil.TITLE_MIN_LENGTH, max = ClassroomUtil.TITLE_MAX_LENGTH, message = ClassroomUtil.MSG_TITLE_LENGTH)
    private String title;

    @NotBlank(message = ClassroomUtil.MSG_SUBJECT_REQUIRED)
    @Length(min = ClassroomUtil.SUBJECT_MIN_LENGTH, max = ClassroomUtil.SUBJECT_MAX_LENGTH, message = ClassroomUtil.MSG_SUBJECT_LENGTH)
    private String subject;

    private String bannerImage;

    @Length(min = ClassroomUtil.INVITE_CODE_MIN_LENGTH, max = ClassroomUtil.INVITE_CODE_MAX_LENGTH, message = ClassroomUtil.MSG_INVITE_CODE_LENGTH)
    private String inviteCode;

    public ClassroomRequestDTO(UUID teacherId, String title, String subject, String bannerImage, String inviteCode) {
        this.teacherId = teacherId;
        this.title = title;
        this.subject = subject;
        this.bannerImage = bannerImage;
        this.inviteCode = inviteCode;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
