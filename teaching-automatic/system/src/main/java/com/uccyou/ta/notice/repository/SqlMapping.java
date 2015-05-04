package com.uccyou.ta.notice.repository;

public final class SqlMapping {

    public static final String ADD_NOTICE = "INSERT INTO teaching.systemnotice(admin_id, title, content, reader, create_date) VALUE(?, ?, ?, ?, ?)";
    public static final String FIND_NOTICE_TO_CHANGE = "SELECT id, title, content, reader FROM teaching.systemnotice WHERE id = ?";
    public static final String UPDATE_NOTICE = "UPDATE teaching.systemnotice SET title = ?, reader = ?, content = ? WHERE id = ?";
    public static final String REMOVE_NOTICE = "DELETE FROM teaching.systemnotice WHERE id = ?";
    public static final String RECENT_NOTICE = "SELECT title FROM teaching.systemnotice WHERE reader = ? ORDER BY create_date DESC";
}
