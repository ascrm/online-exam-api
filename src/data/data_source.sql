INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (1, '第1次考试', '描述1', 90, 100.00, 60.00, 0, 'ascrm', '2025-01-28 18:22:25', '2025-01-28 18:24:00', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (2, '第2次考试', '描述2', 120, 100.00, 60.00, 0, 'ascrm', '2025-01-28 18:22:25', '2025-01-28 18:24:00', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (3, '第3次考试', '描述3', 120, 100.00, 60.00, 0, 'ascrm', '2025-01-28 18:22:25', '2025-01-28 18:24:00', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (4, '第4次考试', '描述4', 120, 100.00, 60.00, 0, 'ascrm', '2025-01-28 18:22:25', '2025-01-28 18:24:00', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (5, '第5次考试', '描述5', 90, 100.00, 60.00, 0, 'ascrm', '2025-01-28 18:22:25', '2025-01-28 18:24:00', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (6, 'newExam', 'hhhh', 20, 100.00, 60.00, 0, null, '2025-01-28 23:40:27', '2025-02-05 22:02:19', 0);
INSERT INTO db_online_exam.tb_exam_paper (id, name, description, duration, total_score, passing_score, is_published, created_by, created_at, updated_at, is_delete) VALUES (7, '阿松大', 'w阿松大', 40, 100.00, 60.00, 1, null, '2025-01-29 00:13:02', '2025-02-05 22:01:20', 0);


INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (1, 7, 1, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (2, 7, 2, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (3, 7, 3, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (4, 7, 4, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (5, 7, 5, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (6, 7, 6, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (7, 7, 7, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (8, 7, 8, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (9, 7, 9, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (10, 7, 10, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (11, 7, 11, 0);
INSERT INTO db_online_exam.tb_exam_question (id, exam_paper_id, question_id, is_delete) VALUES (12, 7, 12, 0);


INSERT INTO db_online_exam.tb_history_exam (id, username, exam_paper_id, total_score, created_at, is_delete) VALUES (23, 'user', 7, 70.00, '2025-02-06 17:38:58', 0);

INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (197, 23, 1, 'A', 0, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (198, 23, 2, 'B', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (199, 23, 3, 'C', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (200, 23, 4, 'A,B', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (201, 23, 5, 'A,D', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (202, 23, 6, 'B,C,D', 0, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (203, 23, 7, 'T', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (204, 23, 8, 'T', 0, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (205, 23, 9, 'F', 0, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (206, 23, 10, 'A,B,C,D', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (207, 23, 11, 'F', 1, 0);
INSERT INTO db_online_exam.tb_history_exam_question (id, history_exam_id, question_id, answer, correct, is_delete) VALUES (208, 23, 12, 'T', 0, 0);


INSERT INTO db_online_exam.tb_judge_question (id, question_id, answer, created_by, created_at, updated_at, is_delete) VALUES (1, 11, 'T', 'ascrm', '2025-01-30 00:56:35', '2025-01-30 00:56:35', 0);
INSERT INTO db_online_exam.tb_judge_question (id, question_id, answer, created_by, created_at, updated_at, is_delete) VALUES (2, 12, 'F', 'ascrm', '2025-01-30 00:57:13', '2025-01-30 00:57:13', 0);
INSERT INTO db_online_exam.tb_judge_question (id, question_id, answer, created_by, created_at, updated_at, is_delete) VALUES (3, 7, 'F', 'ascrm', '2025-01-30 00:57:13', '2025-01-30 00:57:13', 0);
INSERT INTO db_online_exam.tb_judge_question (id, question_id, answer, created_by, created_at, updated_at, is_delete) VALUES (4, 8, 'F', 'ascrm', '2025-01-30 00:57:13', '2025-01-30 00:57:13', 0);
INSERT INTO db_online_exam.tb_judge_question (id, question_id, answer, created_by, created_at, updated_at, is_delete) VALUES (5, 9, 'T', 'ascrm', '2025-01-30 00:57:13', '2025-01-30 00:57:13', 0);


INSERT INTO db_online_exam.tb_multiple_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (1, 10, '阿三大苏打', '按时打发', '发生的', '阿松大', 'D,A,C', 'ascrm', '2025-01-30 00:55:51', '2025-01-30 00:55:51', 0);
INSERT INTO db_online_exam.tb_multiple_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (2, 4, '多选第1道选项A多选第1道选项A多选第1道选项A多选第1道选项A多选第1道选项A多选第1道选项A多选第1道选项A多选第1道选项A', '多选第1道选项A', '多选第1道选项A', '多选第1道选项A', 'A,B,C,D', 'ascrm', '2025-01-30 00:55:51', '2025-01-30 00:55:51', 0);
INSERT INTO db_online_exam.tb_multiple_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (3, 5, '多选第2道选项B', '多选第2道选项B', '多选第2道选项B', '多选第2道选项B', 'A,D', 'ascrm', '2025-01-30 00:55:51', '2025-01-30 00:55:51', 0);
INSERT INTO db_online_exam.tb_multiple_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (4, 6, '多选第3道选项C', '多选第3道选项C', '多选第3道选项C', '多选第3道选项C', 'B,C', 'ascrm', '2025-01-30 00:55:51', '2025-01-30 00:55:51', 0);


INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (1, '题目1', 1, 'Hadoop1.0的核心组件(仅指MapReduce和HDFS，不包括Hadoop生态系统内的Pig、Hive、HBase等其他组件)，下列哪项是它的不足？

', 10.00, '对于大多数项目，升级工具将自动执行整个迁移过程，包括更新依赖项、将配置文件迁移到 CSS 以及处理模板文件的任何更改。

升级工具需要 Node.js 20 或更高版本，因此请确保在运行它之前您的环境已更新。

我们建议在新的分支中运行升级工具，然后仔细检查差异并在浏览器中测试您的项目，以确保所有更改都正确无误。在复杂的项目中，您可能需要手动调整一些内容，但无论哪种方式，该工具都会为您节省大量时间。

仔细检查 v4 中的所有重大变化并充分了解变化的内容也是一个好主意，以防您的项目中还有升级工具无法捕获的其他需要更新的内容。', 1, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (2, '题目2', 1, '题干2', 10.00, '解析2', 2, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (3, '题目3', 1, '题干3', 10.00, '解析3', 3, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (4, '题目4', 2, '题干4', 20.00, '解析4', 4, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (5, '题目5', 2, '题干5', 20.00, '解析5', 5, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (6, '题目6', 2, '题干6', 20.00, '解析6', 1, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (7, '题目7', 3, '题干7', 30.00, '解析7', 2, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (8, '题目8', 3, '题干8', 30.00, '解析8', 3, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (9, '题目9', 3, '题干9', 30.00, '解析9', 4, 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (10, '案板', 2, '阿三大苏打', 20.00, '123', 4, 'ascrm', '2025-01-30 00:55:51', '2025-01-30 00:55:51', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (11, '呃呃请问', 3, '12312', 20.00, '23432', 2, 'ascrm', '2025-01-30 00:56:35', '2025-01-30 00:56:35', 0);
INSERT INTO db_online_exam.tb_question (id, name, question_type, description, score, analysis, difficulty, created_by, created_at, updated_at, is_deleted) VALUES (12, '23432', 3, '32423', 20.00, '23432', 3, 'ascrm', '2025-01-30 00:57:13', '2025-01-30 00:57:13', 0);


INSERT INTO db_online_exam.tb_single_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (1, 1, '第1题选项A第1题选项A第1题选项A第1题选项A第1题选项A第1题选项A项A第1题选项A第1题选项A', '第1题选项B第1题选项B', '第1题选项C第1题选项C', '第1题选项D第1题选项D', 'C', 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_single_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (2, 2, '第2题选项A', '第2题选项B', '第2题选项C', '第2题选项D', 'D', 'ascrm', '2025-01-29 14:00:34', '2025-01-29 14:00:34', 0);
INSERT INTO db_online_exam.tb_single_choice_question (id, question_id, option_A, option_B, option_C, option_D, answer, created_by, created_at, updated_at, is_delete) VALUES (3, 3, '第3题选项A', '第3题选项B', '第3题选项C', '第3题选项D', 'C', 'ascrm', '2025-01-29 14:39:53', '2025-01-29 14:39:53', 0);


INSERT INTO db_online_exam.tb_user (id, username, password, role, nick_name, gender, avatar, email, phone, created_by, created_at, is_delete) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, 'ascrm', 1, null, '2339621373@qq.com', '13547780207', 'ascrm', '2025-01-27 23:09:20', 0);
INSERT INTO db_online_exam.tb_user (id, username, password, role, nick_name, gender, avatar, email, phone, created_by, created_at, is_delete) VALUES (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', 2, 'userAscrm', 1, null, '2339621373@qq.com', '13547780207', 'ascrm', '2025-01-27 23:09:20', 0);
INSERT INTO db_online_exam.tb_user (id, username, password, role, nick_name, gender, avatar, email, phone, created_by, created_at, is_delete) VALUES (3, 'newAdmin', 'e10adc3949ba59abbe56e057f20f883e', 1, null, null, null, null, null, null, '2025-01-28 02:39:22', 0);
INSERT INTO db_online_exam.tb_user (id, username, password, role, nick_name, gender, avatar, email, phone, created_by, created_at, is_delete) VALUES (4, 'newAdmin2', 'e10adc3949ba59abbe56e057f20f883e', 1, null, null, null, null, null, null, '2025-01-28 02:47:25', 0);
