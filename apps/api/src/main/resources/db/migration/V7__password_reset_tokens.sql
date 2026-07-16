create table password_reset_token (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    token_hash varchar(64) not null unique,
    expires_at timestamptz not null,
    used_at timestamptz,
    created_at timestamptz not null
);

create index idx_password_reset_token_user_active
    on password_reset_token(user_id, used_at);
