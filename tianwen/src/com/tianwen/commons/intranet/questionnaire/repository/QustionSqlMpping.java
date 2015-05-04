package com.tianwen.commons.intranet.questionnaire.repository;

public class QustionSqlMpping {
	
	public static final String QUESTIONNIRE_COUNT = " SELECT COUNT(1) FROM tianwen.operationrecord WHERE qn_id = ? AND operation_type = 'J' ";
	
	public static final String JOIN_COUNT = " SELECT COUNT(1) FROM tianwen.`operationrecord` WHERE operation_type = 'J' AND qn_id = ? ";
	
	public static final String USER_QUSTIONNAIRE = "SELECT t1.id, t1.code, t1.topic, t1.create_date AS createDate, t1.create_by AS createBy, t2.name AS categoryName, t1.alive "
												   + " FROM tianwen.questionnaire AS t1 "
												   + " INNER JOIN tianwen.category AS t2 "
												   + " ON t1.category_id = t2.id " 
												   + " WHERE t1.create_by = ? ORDER BY createDate DESC ";
	
	public static final String QUESTIONNAIRE_CREATE_BY = " SELECT userName FROM tianwen.user WHERE id = ? ";
}
