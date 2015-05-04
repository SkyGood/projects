package com.tianwen.commons.questionnaire.repository;

public final class SqlMapping {

    public final static String SAVE_UUID = "INSERT INTO tianwen.codes (uuid) VALUES (?)";

    public final static String GET_UUID_ID = "SELECT id FROM tianwen.codes WHERE uuid = ?";
    
    public final static String SAVE_QUESTIONNAIRE_INFO = "INSERT INTO tianwen.questionnaire (code,topic,create_date,create_by,category_id,alive) VALUES (?,?,?,?,?,'Y')";
    
    public final static String GET_QUESTIONNAIRE_ID = "SELECT id FROM tianwen.questionnaire WHERE topic = ? AND create_by = ? AND create_date = ?";
    
    public final static String SAVE_QUESTIONS = "INSERT INTO tianwen.question (qn_id,title,question_type) VALUES (?,?,?)";
    
    public final static String GET_QUESTION_ID = "SELECT id FROM tianwen.question WHERE title = ? AND question_type = ? AND qn_id = ?";
    
    public final static String SAVE_CHOOSE = "INSERT INTO tianwen.choose (question_id,detail) VALUES (?,?)";
    
    public final static String SAVE_DO_QUESTIONNAIRE_OPERATION_RECORD = "INSERT INTO tianwen.operationrecord (user_id,qn_id,create_date,operation_type) VALUES (?,?,?,'J')";
    
    public final static String SAVE_CREATE_QUESTIONNAIRE_OPERATION_RECORD = "INSERT INTO tianwen.operationrecord (user_id,qn_id,create_date,operation_type) VALUES (?,?,?,'C')";
    
    public final static String SAVE_SM_ANSWER = "INSERT INTO tianwen.answer (choose_id,question_id,user_id) VALUES (?,?,?)";
    
    public final static String SAVE_FILL_ANSWER = "INSERT INTO tianwen.fillanswer (question_id,user_id,content) VALUES (?,?,?)";
    
    public final static String QUESTIONNAIRE_DETAIL = " SELECT id, code, topic FROM tianwen.questionnaire WHERE id = ? ";
    
    public final static String QUESTION_LIST = "SELECT tianwen.`question`.`id`, tianwen.`question`.`title`, tianwen.`question`.`question_type` AS type FROM tianwen.`question` WHERE tianwen.`question`.`qn_id` = ?";
    
    public final static String CHOOSE_LIST = "SELECT tianwen.`choose`.`id`, tianwen.`choose`.`detail` FROM tianwen.`choose` WHERE tianwen.`choose`.`question_id` = ?";
    
    public final static String ID_IS_EXISTS = "SELECT COUNT(1) FROM tianwen.`questionnaire` WHERE id = ?";
    
    public final static String CLOSE_QUESTIONNAIRE = "UPDATE tianwen.`questionnaire` SET tianwen.`questionnaire`.`alive` = 'N'  WHERE tianwen.`questionnaire`.id = ?";
    
    public final static String QNSEARCH_BY_CATEGORY_ID = " SELECT id, code, topic, create_date FROM tianwen.questionnaire WHERE alive = 'Y' AND category_id = ? ORDER BY create_date DESC LIMIT ?, ? ";
    
    public final static String QNSEARCH_COUNT_BY_CATEGORY_ID = " SELECT COUNT(1) FROM tianwen.questionnaire WHERE category_id = ? AND alive = 'Y' ";

	public static String USER_ALREADY_ANSWER = " SELECT COUNT(1) FROM tianwen.operationrecord WHERE user_id = ? AND qn_id = ? AND operation_type = 'J' ";
    
}
