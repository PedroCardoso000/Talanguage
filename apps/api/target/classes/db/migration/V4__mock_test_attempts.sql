create table if not exists mock_test_attempt (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    mock_test_id varchar(128) not null,
    score integer not null,
    total_questions integer not null,
    recommendation text not null,
    completed_at timestamptz not null
);

create index if not exists idx_mock_test_attempt_user_completed_at
    on mock_test_attempt(user_id, completed_at desc);

create table if not exists mock_test_attempt_answer (
    id varchar(128) primary key,
    attempt_id varchar(64) not null references mock_test_attempt(id) on delete cascade,
    order_index integer not null,
    question_id varchar(128) not null,
    selected_option text not null,
    correct_option text not null,
    explanation text not null,
    is_correct boolean not null
);

create index if not exists idx_mock_test_attempt_answer_attempt_order
    on mock_test_attempt_answer(attempt_id, order_index asc);
