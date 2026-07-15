alter table app_user
    add column if not exists target_language varchar(32),
    add column if not exists current_level varchar(32),
    add column if not exists study_goal varchar(240),
    add column if not exists avatar_url varchar(512);
