package com.tianwen.commons.intranet.analyze.repository;

public final class SqlMapping {

    public static final String FIND_QUESTION_INFO = "SELECT id, title, question_type FROM tianwen.question WHERE qn_id = ?";
    
    public static final String FIND_CHOOSE_INFO = "SELECT id,detail FROM tianwen.choose WHERE question_id = ?";
    
    public static final String FIND_FILL_ANSWER = "SELECT content FROM tianwen.fillanswer WHERE question_id = ?";
    
    public static final String GET_CHOOSE_COUNT = "SELECT COUNT(1) FROM tianwen.answer WHERE choose_id = ? AND question_id = ?";
    
    public static final String GET_FILL_ANSWER = "SELECT content FROM tianwen.fillanswer WHERE question_id = ? LIMIT 0, ?";

	public static final String FILL_ANSWER_DETAIL = "SELECT content FROM tianwen.fillanswer WHERE question_id = ? LIMIT ?, ?";

	public static final String FILL_ANSWER_COUNT = "SELECT COUNT(1) FROM tianwen.fillanswer WHERE question_id = ?";

	public static final String QUESTIONNAIRES_TOPIC = "SELECT topic FROM tianwen.questionnaire WHERE id = ?";
}
