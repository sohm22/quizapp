
drop table if exists question;
CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(25) DEFAULT NULL,
    difficulty_level VARCHAR(25) DEFAULT NULL,
    option1 VARCHAR(255) DEFAULT NULL,
    option2 VARCHAR(255) DEFAULT NULL,
    option3 VARCHAR(255) DEFAULT NULL,
    option4 VARCHAR(255) DEFAULT NULL,
    question_title VARCHAR(255) DEFAULT NULL,
    correct_answer VARCHAR(255) DEFAULT NULL
);

drop table if exists quiz;
CREATE TABLE quiz (
    id BIGSERIAL PRIMARY KEY,
    quiz_title VARCHAR(255) DEFAULT NULL
);

drop table if exists quiz_questions;
CREATE TABLE quiz_questions (
    quiz_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    PRIMARY KEY (quiz_id, question_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
);