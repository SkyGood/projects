package com.uccyou.ta.teacher.resources.repository;

public final class SqlMapping {

    public static final String UPLOAD_RESOURCE = "INSERT INTO teaching.resources(user_id, resName, resLocation, type, alive, create_date) VALUES(?, ?, ?, 'R', 'Y', ?)";
    
    public static final String FIND_RESOURCES_ID = "SELECT id FROM teaching.resources WHERE resLocation = ? AND create_date = ?";
    
    public static final String UPLOAD_CLASS_RESOURCE = "INSERT INTO teaching.classresources(class_id, res_id, alive) VALUES(?, ?, 'Y')";

    public static final String CHANGE_TEACHING_RESOURCE_ALIVE = "UPDATE teaching.classresources SET alive = ? WHERE res_id = ?";
    
    public static final String CHANGE_RESOURCE_ALIVE = "UPDATE teaching.resources SET alive = ? WHERE id = ?";

    public static final String FIND_RESOURCE_BY_ID = "SELECT resName FROM teaching.resources WHERE id = ?";

    public static final String MODIFY_RESOURCE = "UPDATE teaching.resources SET resName = ? WHERE id = ?";

    public static final String FIND_ALL_RESOURCES = "SELECT id, resName, create_date FROM teaching.resources WHERE user_id = ? AND TYPE = 'R' AND alive = 'Y' AND id NOT IN (SELECT res_id FROM teaching.classresources WHERE class_id = ?) limit ?, ?";

    public static final String FIND_ALL_COUNT = "SELECT COUNT(1) FROM teaching.resources WHERE user_id = ? AND TYPE = 'R' AND alive = 'Y' AND id NOT IN (SELECT res_id FROM teaching.classresources WHERE class_id = ?)";

    public static final String RESOURCE_IS_EXISTS = "SELECT COUNT(1) FROM teaching.classresources WHERE class_id = ? AND res_id = ?";

    public static final String REFERENCE_RESOURCE = "INSERT INTO teaching.classresources(class_id, res_id, alive) VALUES(?, ?, 'Y')";

    public static final String RESOURCE_REFERENCE_EXISTS = "SELECT COUNT(1) FROM teaching.classresources WHERE res_id = ?";

    public static final String REMOVE_CLASS_RESOURCES = "DELETE FROM teaching.classresources WHERE class_id = ? AND res_id = ?";

    public static final String REMOVE_RESOURCES = "DELETE FROM teaching.resources WHERE id = ?";

    public static final String FIND_RESOURCES_LOCATION = "SELECT resLocation FROM teaching.resources WHERE id = ?";

}
