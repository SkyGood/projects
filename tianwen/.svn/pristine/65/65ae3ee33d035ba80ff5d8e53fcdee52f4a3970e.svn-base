package com.tianwen.commons.init.repository;

public final class SqlMapping {

	public static final String QUESTIONNAIRE_CATEGORY_QUERY = " SELECT id, NAME FROM tianwen.category ";

	// public static final String ORGANIZATION_QUERY_BY_QUESTIONNAIREID =
	// " SELECT id, category_id, NAME FROM tianwen.organization WHERE category_id = ? ";

	public static final String NEWS_QUESTIONNAIRE = "SELECT tianwen.`questionnaire`.`id`, tianwen.`questionnaire`.`CODE`, tianwen.`questionnaire`.`topic`, tianwen.`questionnaire`.`create_date` AS date FROM tianwen.`questionnaire`  WHERE tianwen.`questionnaire`.`alive`='Y' ORDER BY tianwen.`questionnaire`.`create_date`  DESC LIMIT 0,15";

	public static final String OWN_QUSTIONNAIRE = " SELECT t1.id, t1.code, t1.topic, t1.create_date AS createDate, t1.create_by AS createBy, t2.name AS categoryName, t1.alive "
			+ " FROM tianwen.questionnaire AS t1 "
			+ " INNER JOIN tianwen.category AS t2 "
			+ " ON t2.id = t1.category_id " + " WHERE create_by = ? ";

	public static final String CATEGORY_IS_EXISTS = " SELECT COUNT(1) FROM tianwen.category WHERE name = ? ";

	public static final String ADD_CATEGORY = " INSERT INTO tianwen.category(NAME, create_date) VALUES(?, ?) ";

	public final static String QUESTION_ALREADY_COMMIT = "SELECT COUNT(1) FROM tianwen.operationrecord WHERE user_id = ? AND qn_id = ? AND operation_type = 'J' ";
}
