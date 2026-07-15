create table if not exists app_user (
    id varchar(64) primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    password_hash varchar(255) not null,
    created_at timestamptz not null,
    updated_at timestamptz not null
);

create table if not exists user_session (
    token varchar(255) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    created_at timestamptz not null
);

create table if not exists goal_settings (
    user_id varchar(64) primary key references app_user(id) on delete cascade,
    daily_minutes integer not null,
    weekly_minutes integer not null,
    words_per_day integer not null,
    challenges_per_week integer not null,
    updated_at timestamptz not null
);

create table if not exists learning_activity (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    type varchar(32) not null,
    skill varchar(32) not null,
    score integer not null,
    completed_at timestamptz not null,
    source_id varchar(128)
);

create index if not exists idx_learning_activity_user_completed_at
    on learning_activity(user_id, completed_at desc);

create table if not exists flashcard (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    front_text text not null,
    back_text text not null,
    language varchar(32) not null,
    tags_json text not null,
    difficulty integer not null,
    review_count integer not null,
    created_at timestamptz not null,
    next_review_at timestamptz not null
);

create index if not exists idx_flashcard_user_created_at
    on flashcard(user_id, created_at asc);

create table if not exists flashcard_review_stats (
    user_id varchar(64) primary key references app_user(id) on delete cascade,
    reviewed_count integer not null,
    correct_count integer not null,
    wrong_count integer not null,
    updated_at timestamptz not null
);

create table if not exists community_interest (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    target_type varchar(32) not null,
    target_id varchar(128) not null,
    created_at timestamptz not null,
    constraint uk_community_interest_target unique (user_id, target_type, target_id)
);

create table if not exists writing_submission_metric (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    challenge_id varchar(128) not null,
    language varchar(32) not null,
    level varchar(32) not null,
    status varchar(32) not null,
    score integer not null,
    feedback_summary text not null,
    improvements_json text not null,
    created_at timestamptz not null,
    reviewed_at timestamptz not null
);

create index if not exists idx_writing_metric_user_reviewed_at
    on writing_submission_metric(user_id, reviewed_at desc);

create table if not exists speaking_session_metric (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    language varchar(32) not null,
    level varchar(32) not null,
    topic_id varchar(128) not null,
    status varchar(32) not null,
    current_prompt text not null,
    response_count integer not null,
    total_word_count integer not null,
    total_sentence_count integer not null,
    started_at timestamptz not null,
    finished_at timestamptz,
    duration_seconds bigint,
    score integer,
    pronunciation integer,
    fluency integer,
    clarity integer,
    feedback_summary text
);

create index if not exists idx_speaking_metric_user_finished_at
    on speaking_session_metric(user_id, finished_at desc);
